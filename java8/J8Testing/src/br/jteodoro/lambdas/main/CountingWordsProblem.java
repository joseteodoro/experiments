package br.jteodoro.lambdas.main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountingWordsProblem {

	public static void main (String [] args) {
		String str = "coco banana coco banana coco";
		new Assertion().assertThat("[coco=3, banana=2]", new WordCounter().count(str));
		
		str = "coco banana coco banana coco bacia bacia amazon";
		new Assertion().assertThat("[coco=3, bacia=2, banana=2, amazon=1]", new WordCounter().count(str));
	}

}

class WordCounter {
	public String count(String str) {
		Map<String, Long> grouped = Arrays.stream(str.split(" "))
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting() ));
		
		List<Entry<String,Long>> sortedEntries = grouped.entrySet().stream().collect(Collectors.toList());
		sortedEntries.sort( (left, right) -> left.getKey().compareTo(right.getKey()) );
		
		Comparator<Entry<String,Long>> compareByKey = ((left, right) -> left.getKey().compareTo(right.getKey()));
		Comparator<Entry<String,Long>> compareByValue = ((left, right) -> right.getValue().compareTo(left.getValue()));
		
		Comparator<Entry<String, Long>> compoundComparing = compareByValue.thenComparing(compareByKey);
		sortedEntries.sort( compoundComparing );
		
		
		return String.format("%s", sortedEntries);
	}
}