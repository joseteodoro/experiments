package br.jteodoro.lambdas;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Library implements Consumer<Book>, Iterable<Book>{

	public Collection<Book> books;
	
	public Library(Collection<Book> _books) {
		books = _books;
	}
	
	public Library() {
		books = new HashSet<>();
	}
	
	public boolean add(Book book) {
		return books.add(book);
	}
	
	public boolean add(Collection<Book> _books) {
		return books.addAll(_books);
	}
	
	public Optional<Book> filterByName(String name) {
		return this.filter((book) -> book.name.equals(name));
	}
	
	public Optional<Book> filter(Predicate<Book> predicate) {
		return this.books.stream().filter( predicate ).findAny();
	}
	
	public int count(CountInterface<Book> counter) {
		return counter.count(books);
	}

	@Override
	public void accept(Book book) {
		books.add(book);
	}

	@Override
	public Iterator<Book> iterator() {
		return books.iterator();
	}
	
	public Collection<Book> sorted() {
		Comparator<Book> comparing = Comparator.comparing( Book::getName );
		List<Book> list = books.stream().collect(Collectors.toList());
		list.sort(comparing);
		return list;
	}
	
}
