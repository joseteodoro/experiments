-module(solution).
-export([main/0]).

solve(X, X) ->
    io:fwrite("~B~n",[X]);

solve(X, Y) when X > Y ->
    solve(X-Y,Y);

solve(Y, X) ->
    solve(X,Y).

main() ->
	{ok, [Head]} = io:fread("", "~d"),
    {ok, [Tail]} = io:fread("", "~d"),
    solve(Head, Tail).
