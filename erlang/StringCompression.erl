-module(solution).
-export([main/0]).

print(A, 1) ->
    io:fwrite([A]);

print(A, Count) ->
    io:fwrite([A]),
    io:fwrite("~B",[Count]).

crypt(A,[H|T],Count) when A == H ->
    %io:fwrite("Increment ~s by ~B~n",[[A],Count]),
    crypt(H,T,Count+1);

crypt(A,[H|[]],Count) ->
    %io:fwrite("Increment ~s with ~B~n",[[A],Count]),
    print(A, Count),
    print(H, 1);

crypt(A,[],Count) ->
    %io:fwrite("Increment ~s with ~B~n",[[A],Count]),
    print(A, Count);

crypt(A,[H|T],Count) ->
    print(A, Count),
    %io:fwrite("Printing ~s~n",[[A]]),
    crypt(H,T,1).

crypt() ->
    {ok, [[H|T]]} = io:fread ("", "~s"),
    crypt(H,T,1).

main() ->
    crypt().
