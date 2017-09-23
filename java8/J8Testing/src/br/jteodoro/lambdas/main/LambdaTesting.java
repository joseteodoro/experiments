package br.jteodoro.lambdas.main;

import java.util.Collection;
import java.util.LinkedList;

import br.jteodoro.lambdas.Book;
import br.jteodoro.lambdas.CountInterface;
import br.jteodoro.lambdas.Library;
import br.jteodoro.lambdas.Stringfy;

public class LambdaTesting {

	public static void main(String [] args) {
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
		
		CountInterface<Book> counter = (items) -> items.size(); 
		System.out.println(library.count(counter));
		library.books.stream().forEach( (book) -> System.out.println(Stringfy.jsonfy(Book.class, book)));
		
	}
	
}
