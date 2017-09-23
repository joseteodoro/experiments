package br.jteodoro.lambdas;

import java.util.Map.Entry;
import java.util.function.Consumer;

class MapToJson implements Consumer<Entry<String, String>> {
	
	StringBuilder data = new StringBuilder();

	public MapToJson() {
		data = new StringBuilder();
	}
	
	@Override
	public void accept(Entry<String, String> t) {
		data.append(String.format("'%s': '%s', ", t.getKey(), t.getValue()));
	}
	
	@Override
	public String toString() {
		return "{ " + data.toString() + " }";
	}
	
}