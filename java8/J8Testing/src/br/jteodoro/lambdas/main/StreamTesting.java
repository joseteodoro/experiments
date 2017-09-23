package br.jteodoro.lambdas.main;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import br.jteodoro.lambdas.Book;
import br.jteodoro.lambdas.Library;
import br.jteodoro.lambdas.Stringfy;

public class StreamTesting {

	public static void main(String[] args) {
		String [] authors = {"Rick Riordan","Rick Riordan","Michael McCandless","Jostein Gaarder"};
		String [] books = {"The Lightning Thief","The Sea of Monsters","Lucene in Action, Second Edition","Sophie's World : The Greek Philosophers"};
		
		Collection<Book> plain = new LinkedList<>();
		for (int i = 0; i < authors.length; i++) {
			plain.add(new Book(books[i], authors[i], i));
		}
		
		Library library = new Library();
		Library duplicated = new Library(new LinkedList<>());
		plain.forEach(library.andThen(duplicated).andThen(duplicated));
		plain.forEach(library);
		
		System.out.println(Stringfy.jsonfy(Book.class, library.
				filter( (book) -> book.name.equals("The Lightning Thief")).get()));
		
		System.out.println("################\n\n");
		
		duplicated.books.stream().forEach( (book) -> System.out.println(Stringfy.jsonfy(Book.class, book)));
		
		Supplier<Book> supplier = () -> new Book();
		
		library.books.stream().forEach( (book) -> System.out.println(Stringfy.jsonfy(Book.class, book)));
		
		System.out.println( Arrays.asList(books).stream().collect(Collectors.joining("';'","'", "'")) );
		
	}

}
