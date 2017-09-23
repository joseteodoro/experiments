package br.jteodoro.lambdas.main;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;

import br.jteodoro.lambdas.Book;

public class TreeMapTesting {
	
	public static void main(String [] args) {
		
		
		String [] authors = {"Rick Riordan","Rick Riordan","Michael McCandless","Jostein Gaarder"};
		String [] books = {"The Lightning Thief","The Sea of Monsters","Lucene in Action, Second Edition","Sophie's World : The Greek Philosophers"};
		
		Collection<Book> plain = new LinkedList<>();
		for (int i = 0; i < authors.length; i++) {
			plain.add(new Book(books[i], authors[i], i));
		}
		
		TreeMap<String,Book> treeMap = new TreeMap<>(Comparator.naturalOrder());
		Consumer<Book> library = (book) -> treeMap.put(book.name, book) ;
		
		plain.forEach(library);
		
		System.out.println(treeMap.firstKey());
		System.out.println(treeMap.size());
		
		System.out.println(treeMap.pollFirstEntry().getValue().name);
		System.out.println(treeMap.pollLastEntry().getValue().name);
		
		System.out.println(treeMap.size());
		System.out.println(treeMap.firstKey());
		
	}

}
