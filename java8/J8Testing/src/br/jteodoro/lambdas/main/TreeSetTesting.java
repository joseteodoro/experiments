package br.jteodoro.lambdas.main;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.function.Consumer;

public class TreeSetTesting {

	public static void main(String[] args) {
		String [] authors = {"Rick Riordan","Rick Riordan","Michael McCandless","Jostein Gaarder"};
		String [] books = {"The Lightning Thief","The Sea of Monsters","Lucene in Action, Second Edition","Sophie's World : The Greek Philosophers"};
		
		Collection<String> names = new HashSet<>();
		Consumer<String> taker = (String) -> names.add(String);
		
		Arrays.asList(authors).stream().forEach(taker);
		Arrays.asList(books).stream().forEach(taker);
		
		TreeSet<String> treeSet = new TreeSet<>(names);
		System.out.println(treeSet.first());
		System.out.println(treeSet.last());
		
		System.out.println(treeSet.floor("T"));
		System.out.println(treeSet.ceiling("T"));
	}

}
