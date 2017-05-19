-module(solution).
-export([main/0]).

hello(N) when N > 0 ->
    io:fwrite("Hello World~n"),
    hello(N - 1);

hello(N) -> ok.

main() ->
	{ok, [Count]} = io:fread("","~d"),
    hello(Count).
