package br.jteodoro.lambdas;

public class Book {

	public String name;
	
	public String author;

	public int id;
	
	public Book(String _name, String _author) {
		this(_name, _author, 0);
	}
	
	public Book(String _name, String _author, int id) {
		this.name = _name;
		this.author  = _author;
		this.id = id;
	}
	
	public Book() {}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}
}
