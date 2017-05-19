-module(solution).
-export([main/0]).

printPartial(Element, []) -> io:fwrite("~s~n",[Element]);

printPartial([],Next) ->
    [H|T] = Next,
    printPartial(H,T);

printPartial(Element, Next) ->
    io:fwrite("~s ",[Element]),
    [H|T] = Next,
    printPartial(H,T).

print(Res) ->
    [H|T] = Res,
    printPartial(H,T).

rotate(_,0,Res) -> Res;

rotate(Word,NRotate,Res) ->
    %io:fwrite("Current word ~s~n",[Word]),
    [H|T] = Word,
    Rotated = T++[H],
    %io:fwrite("Rotated word ~s~n",[Rotated]),
    rotate(Rotated,NRotate-1,Res ++ [Rotated]).

rotate(Word) ->
    NRotate = length(Word),
    Rotated = rotate(Word,NRotate,[]),
    %io:fwrite("Rotated words ~w~n",[Rotated]),
    print(Rotated).

solve(0) -> ok;

solve(Count) ->
    {ok, [Word]} = io:fread("", "~s"),
    rotate(Word),
    %io:fwrite("~n"),
    solve(Count-1).

main() ->
    {ok, [Integer]} = io:fread ("", "~d"),
    solve(Integer).
