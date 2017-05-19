-module(solution).
-export([main/0]).

readInput(0, List) -> List;

readInput(Count, List) ->
    {ok, Int} = io:fread ("", "~d"),
    readInput(Count-1, List ++ Int ).

ahead(_, _,[]) -> 0;

ahead(M, Base, Tail) ->
    [Current | Next ] = Tail,
    %io:fwrite("Ahead M ~B Base ~B Current ~B Tail length ~B ~n",[M,Base,Current,length(Next)]),
    ahead(M, Base, Current,[], Next).

ahead(M, Base, Current, Valid, []) when Current >= Base, Current =< (Base + M) -> length(Valid ++ [Current]);

ahead(_, _, Current, Valid, []) ->
    %io:fwrite("Ahead finished1 with ~B elements. Skipping ~B~n",[length(Valid),Current]),
    length(Valid);

ahead(M, Base, Current, Valid, Tail) when Current >= Base, Current =< (Base + M) ->
    %io:fwrite("Ahead adding Current ~B Base ~B M ~B ~n",[Current,Base,M]),
    [Next | Nexts] = Tail,
    ahead(M, Base, Next, Valid ++ [Current], Nexts);

ahead(_, _, Current, Valid, _) ->
    %io:fwrite("Ahead finished2 with ~B elements. Skipping ~B~n",[length(Valid),Current]),
    length(Valid).

splitFoward(_,_,[],_,[]) -> [];

splitFoward(_,_,[],_,Res) -> Res;

splitFoward(D,M,Input,Index,Res) when Index =< D ->
    [_ | Tail] = Input,
    splitFoward(D,M,Tail,Index+1,Res);

splitFoward(D,M,Input,Index,Res) ->
    [Current | Tail] = Input,
    splitFoward(D,M,Tail,Index+1,Res ++ [Current]).

%empty
countForward(_,_,[],_,_,_,0) -> 0;

%result
countForward(_,_,[],_,_,_,Count) -> Count;

%by pass
countForward(D,M,Input,Base,CurrentIndex,CurrentValue,Count) when CurrentIndex =< D ->
    [Next | Tail] = Input,
    countForward(D,M,Tail,Base,CurrentIndex+1,Next,Count);

%evaluate
countForward(D,M,Input,Base,CurrentIndex,CurrentValue,Count) when CurrentIndex > D, CurrentValue >= Base, CurrentValue =< (Base + M) ->
    [Next | Tail] = Input,
    countForward(D,M,Tail,Base,CurrentIndex+1,Next,Count+1).

splitBackward(D,_,_,Index,Res) when Index == D -> lists:reverse(Res);

splitBackward(_,_,[],_,_) -> [];

splitBackward(D,M,Input,Index,Res) ->
    [Current | Tail] = Input,
    splitBackward(D,M,Tail,Index+1,Res ++ [Current]).

backward(D,M,Base,Input) ->
    Backward = splitBackward(D,M,Input,0,[]),
    %io:fwrite("Backward length ~B~n",[length(Backward)]),
    Ahead = ahead(M, Base, Backward),
    %io:fwrite("Backward ahead length ~B~n",[Ahead]),
    Ahead.

forward(D,M,Base,Input) ->
    Forward = splitFoward(D,M,Input,0,[]),
    %io:fwrite("Forward length ~B~n",[length(Forward)]),
    Ahead = ahead(M, Base, Forward),
    %io:fwrite("Forward ahead length ~B~n",[Ahead]),
    Ahead.

base(D,_,Index,Res) when Index == D -> Res;

base(D,Input,Index,_) ->
    [Current | Tail] = Input,
    base(D,Tail,Index+1,Current).

reduce(B,F) ->
    %io:fwrite("Result ~B~n",[B+F+1]),
    io:fwrite("~B~n",[B+F+1]).

solve_query(0, _) -> ok;

solve_query(Queries, Input) ->
    {ok, [D]} = io:fread ("", "~d"),
    {ok, [M]} = io:fread ("", "~d"),
    %io:fwrite("D ~B~n",[D]),
    %io:fwrite("M ~B~n",[M]),
    Base = base(D,Input,-1,[]),
    %io:fwrite("Base ~B~n",[Base]),
    reduce(backward(D,M,Base,Input),forward(D,M,Base,Input)),
    solve_query(Queries-1,Input).

main() ->
	{ok, [Count]} = io:fread("", "~d"),
    Input = readInput(Count,[]),
    {ok, [Queries]} = io:fread("", "~d"),
    %io:fwrite("Solving ~B queries~n",[Queries]),
    %io:fwrite("Input length ~B~n",[length(Input)]),
    solve_query(Queries, Input).
	
