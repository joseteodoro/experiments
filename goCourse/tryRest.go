package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"time"

	"github.com/gorilla/context"
	"github.com/julienschmidt/httprouter"
	"github.com/justinas/alice"
)

func recoverHandler(next http.Handler) http.Handler {
	fn := func(w http.ResponseWriter, r *http.Request) {
		defer func() {
			if err := recover(); err != nil {
				log.Printf("panic: %+v", err)
				http.Error(w, http.StatusText(500), 500)
			}
		}()

		next.ServeHTTP(w, r)
	}

	return http.HandlerFunc(fn)
}

func loggingHandler(next http.Handler) http.Handler {
	fn := func(w http.ResponseWriter, r *http.Request) {
		t1 := time.Now()
		next.ServeHTTP(w, r)
		t2 := time.Now()
		calledURL := r.URL.String()
		log.Printf("[%s] %q %v\n", r.Method, calledURL, t2.Sub(t1))
	}

	return http.HandlerFunc(fn)
}

func aboutHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "{'message' : 'You are on the about page.'}")
}

func indexHandler(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "{'message' : 'Welcome!'}")
}

type appContext struct{}

func (c *appContext) authHandler(next http.Handler) http.Handler {
	fn := func(w http.ResponseWriter, r *http.Request) {
		authToken := r.Header.Get("Authorization")

		if authToken == "" {
			http.Error(w, http.StatusText(401), 401)
			return
		}

		fmt.Fprintf(w, "{'message' : 'You are logged as %v.'}\n", authToken)
		context.Set(r, "authToken", authToken)
		next.ServeHTTP(w, r)
	}

	return http.HandlerFunc(fn)
}

func (c *appContext) adminHandler(w http.ResponseWriter, r *http.Request) {
	user := context.Get(r, "authToken")
	// Maybe other operations on the database
	json.NewEncoder(w).Encode(user)
}

func (c *appContext) teaHandler(w http.ResponseWriter, r *http.Request) {
	params := context.Get(r, "params").(httprouter.Params)
	log.Println(params.ByName("id"))
	// tea := getTea(c.db, params.ByName("id"))
	json.NewEncoder(w).Encode(nil)
}

type router struct {
	*httprouter.Router
}

func (r *router) Get(path string, handler http.Handler) {
	r.GET(path, wrapHandler(handler))
}

func NewRouter() *router {
	return &router{httprouter.New()}
}

func wrapHandler(h http.Handler) httprouter.Handle {
	return func(w http.ResponseWriter, r *http.Request, ps httprouter.Params) {
		context.Set(r, "params", ps)
		h.ServeHTTP(w, r)
	}
}

func main() {
	appC := appContext{}
	commonHandlers := alice.New(context.ClearHandler, loggingHandler, recoverHandler)
	router := NewRouter()
	router.Get("/admin", commonHandlers.Append(appC.authHandler).ThenFunc(appC.adminHandler))
	router.Get("/about", commonHandlers.ThenFunc(aboutHandler))
	router.Get("/", commonHandlers.ThenFunc(indexHandler))
	router.Get("/teas/:id", commonHandlers.ThenFunc(appC.teaHandler))
	fmt.Print("Listening on 8080 port ...")
	http.ListenAndServe(":8080", router)
}