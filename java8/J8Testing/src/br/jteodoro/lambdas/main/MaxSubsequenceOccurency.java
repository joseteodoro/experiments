package br.jteodoro.lambdas.main;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MaxSubsequenceOccurency {

	public static void main(String [] args) {
		new Assertion_().assertThat("asd: 3", new Finder().find("asdasdasd", 3));
		new Assertion_().assertThat("asd: 1", new Finder().find("asd", 3));
		new Assertion_().assertThat("meu: 3", new Finder().find("cocmeumasnjasndjfnasameummasdfmasdmmeucoc", 3));
	}
	
}

class Finder {
	
	public String find(String str, int size) {
		IntStream range = IntStream.rangeClosed(0, str.length() - size);
		Map<String, List<String>> subsequences = range.mapToObj((integer) -> str.substring(integer, integer+size) )
				.collect(Collectors.groupingByConcurrent( Function.identity() ));
		
		List<Map.Entry<String, List<String>>> sorted = new LinkedList<>(subsequences.entrySet());
		sorted.sort( (left, right) -> right.getValue().size() - left.getValue().size() );
		
		return String.format("%s: %s", sorted.get(0).getKey(), sorted.get(0).getValue().size());
		
//		for (int i = 0; i+size <= str.length(); i++) {
//			String subsequence = str.substring(i, i+size);
//			if (!subsequences.containsKey(subsequence)) {
//				subsequences.put(subsequence, 0);
//			}
//			subsequences.put(subsequence, subsequences.get(subsequence) + 1);
//		}
//		List<Map.Entry<String, Integer>> sorted = subsequences.entrySet()
//			.stream()
//			.collect(Collectors.toList());
//		
//		sorted.sort( (left, right) -> right.getValue() - left.getValue() );
//		return String.format("%s: %s", sorted.get(0).getKey(), sorted.get(0).getValue());
		
	}
	
}


class Assertion_ {
	
	public <T> void assertThat(T expected, T got) {
		if (expected == null || got == null || !expected.equals(got)) {
			System.out.println(String.format("Failed. Expected { %s } but got { %s }.", expected, got));
			return;
		}
		System.out.println("Succeed.");
	}
	
}
