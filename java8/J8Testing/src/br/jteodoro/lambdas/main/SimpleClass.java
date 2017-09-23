package br.jteodoro.lambdas.main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class SimpleClass {

	public static void main (String [] args) {
		
		String [] strs = {"14-1", "11-2","12-3","04-4","01-5", "02-6", "03-7",  "10-8",};
		TreeMap<String, String> treeMap = new TreeMap<>();
		Arrays.stream(strs).forEach( (str) -> treeMap.put(str, str) );
		
		
		TreeSet<String> treeSet = new TreeSet<>();
		Arrays.stream(strs).forEach( (str) -> treeSet.add(str) );
		
		PriorityQueue<String> minHeap = new PriorityQueue<>(Comparator.naturalOrder());
		Arrays.stream(strs).forEach( (str) -> minHeap.add(str) );
		
		System.out.println("############\n\nTreeset:");
		treeSet.stream().forEach( (str) -> System.out.println(str)  );
		
		System.out.println("############\n\nMinHeap:");
		minHeap.stream().forEach( (str) -> System.out.println(str)  );
		
		String polled = minHeap.poll();
		
		System.out.println("############\n\nMinHeap: after " + polled);
		minHeap.stream().forEach( (str) -> System.out.println(str)  );
		
		
	}
	
}
