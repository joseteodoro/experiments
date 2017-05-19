-module(solution).
-export([main/0]).

print([]) -> ok;

print(List) ->
    [Head | Tail] = List,
    io:fwrite("~B~n",[Head]),
    print(Tail).

reverse(List) ->
    case io:fread ("", "~d") of
        eof -> print(List);
        Data ->
            {ok, New} = Data,
            reverse(New ++ List)
    end.

main() ->
    reverse([]).
