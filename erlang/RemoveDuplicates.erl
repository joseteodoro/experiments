-module(solution).
-export([main/0]).

print(Word) ->
    io:fwrite("~s",[Word]).

%do not add existent letter
scanUnique(A,A,Res,_) -> Res;

%add nex letter
scanUnique(A,B,Res,[]) -> Res ++ [A];

%got to nex letter when has
scanUnique(A,_,Res,[H|T]) ->
    %[H|T] = Stream,
    scanUnique(A,H,Res,T);

%last letter
scanUnique(A,_,Res,T) ->
    scanUnique(A,T,Res,[]).

%end of the word
scanWord(Unique,[]) -> Unique;

%start the scan for duplication
scanWord([],Word) ->
    [A|T] = Word,
    scanWord([A],T);

%start the scan for duplication
scanWord(Unique,Word) ->
    [A|T] = Word,
    [B|TailUnique] = Unique,
    NextUnique = scanUnique(A,B,Unique,Unique),
    scanWord(NextUnique,T).

scanWord(Word) ->
    scanWord([],Word).

dedulicate(Word) when length(Word) < 2 ->
    print(Word);

dedulicate(Word) ->
    print(scanWord(Word)).

main() ->
    {ok, [Word]} = io:fread("", "~s"),
    dedulicate(Word).
