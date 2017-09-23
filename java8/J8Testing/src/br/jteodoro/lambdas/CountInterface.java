package br.jteodoro.lambdas;

import java.util.Collection;

@FunctionalInterface
public interface CountInterface<T> {

	public Integer count(Collection<T> elements);
	
}
