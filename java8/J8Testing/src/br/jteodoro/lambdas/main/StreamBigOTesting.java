package br.jteodoro.lambdas.main;

import java.util.stream.IntStream;

public class StreamBigOTesting {

	public static void main (String [] args) {
		IntStream.rangeClosed(0, 100).parallel().map( (i) -> {
			System.out.println(i);
			return i;
		}).forEach( (i) -> System.out.println(i) );
		
		String [] books = {"The Lightning Thief","The Sea of Monsters","Lucene in Action, Second Edition","Sophie's World : The Greek Philosophers"};
		IntStream.iterate(0, (i) -> i+2 ).limit(books.length).forEach( (str) -> System.out.println(str) );;
		
	}
	
}
