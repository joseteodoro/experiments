package br.jteodoro.lambdas.main;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapAndComparatorTesting {
	
	public static void main (String [] args) {
		Comparator<String> bySize = (left, right) -> left.length() - right.length();
		Comparator<String> byNaturalOrder = Comparator.naturalOrder();
		Comparator<String> compoundComparator = bySize.thenComparing(byNaturalOrder);
		
		String str = "coco banana coco banana coco bacia bacia amazon cocobongo";
		
		PriorityQueue<String> minHeap = new PriorityQueue<>(compoundComparator);
		Arrays.stream(str.split(" ")).forEach( (word) -> minHeap.add(word) );
		System.out.println(minHeap.poll());
		
		PriorityQueue<String> maxHeap = new PriorityQueue<>(compoundComparator.reversed());
		Arrays.stream(str.split(" ")).forEach( (word) -> maxHeap.add(word) );
		System.out.println(maxHeap.poll());
		
		maxHeap.clear();
		minHeap.clear();
		
		Arrays.stream(str.split(" ")).forEach( (word) -> maxHeap.add(word) );
		Arrays.stream(str.split(" ")).forEach( (word) -> minHeap.add(word) );
		System.out.println("");
		maxHeap.stream().forEach( (word) -> System.out.println(word) );
		
		
		
		
		
	}
	
}
