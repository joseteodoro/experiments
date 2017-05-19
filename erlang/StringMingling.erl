-module(solution).
-export([main/0]).


print(A,B) ->
    io:fwrite([A,B]).

mingle([A|[]],[B|[]]) ->
    print(A,B);

mingle([A|TA],[B|TB]) ->
    print(A,B),
    mingle(TA,TB).

mingle() ->
    {ok, [A]} = io:fread ("", "~s"),
    {ok, [B]} = io:fread ("", "~s"),
    mingle(A,B).

main() ->
    mingle().
