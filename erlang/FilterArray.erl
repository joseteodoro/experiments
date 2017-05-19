-module(solution).
-export([main/0]).

write(Delimiter, Data) when Data < Delimiter ->
    io:format("~B~n", [Data]);

write(_, Data) -> ok.

do(Delimiter) ->
    case io:fread ("", "~d") of
        eof -> init:stop();
        Data ->
            {ok, [Value]} = Data,
            write(Delimiter, Value),
            do(Delimiter)
    end.

main() ->
	{ok, [Delimiter]} = io:fread("", "~d"),
    do(Delimiter).
