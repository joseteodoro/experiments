package br.jteodoro.lambdas.main;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamPartitioning {

	public static void main(String[] args) {
		Integer [] arr = {1,2,3,4,5,1,1,2,3,3,5,6,1,2,3,4,4,43,2,6,7,5,9,8,7};
		
		Map<Boolean, List<Integer>> isEven = Arrays.asList(arr).stream().collect( Collectors.partitioningBy((integer) -> integer % 3 == 0 ) );
		
		Function<Entry<Boolean, List<Integer>>, Boolean> printing = (entry) -> {
			System.out.println(String.format("%s, %s", entry.getKey(), entry.getValue().size()));
			return Boolean.TRUE;
		};
		
		isEven.entrySet().stream().forEach( (x) -> printing.apply(x)  );
	}

}
