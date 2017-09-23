import os
from bottle import route, run
import json

@route('/')
def index():
    return "{'message' : 'index of bottle boilerplate.'}\n"

@route('/name/<name>')
def name(name):
    return name

if __name__ == '__main__':
    port = int(os.environ.get('PORT', 8080))
    run(host='0.0.0.0', port=port, debug=True)
