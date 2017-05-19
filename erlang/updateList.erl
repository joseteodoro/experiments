-module(solution).
-export([main/0]).

print(Sum) -> io:fwrite("~B~n",[Sum]).

solve(eof) -> ok;

solve({ok, [N]}) ->
    print(abs(N)),
    solve(io:fread ("", "~d")).

solve() -> solve(io:fread ("", "~d")).

main() ->
    solve().
