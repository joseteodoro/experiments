package br.jteodoro.lambdas.main;

import java.util.function.Function;

public class FunctionApplyTesting {

	public static void main(String[] args) {
		Function<String, Integer> sizer = (str) -> str.length();
		String text = "how many chars do I have?";
		System.out.println(text + ": " + sizer.apply(text));
		
		
		Function<String, Boolean> printer = (str) -> { 
			System.err.println(str);
			return Boolean.TRUE;
		};
		
		Function<String, String> upper = (str) -> str.toUpperCase();
		Function<String, Boolean> composed = upper.andThen(printer);
		
		System.out.println(text + ": " + composed.apply(text));
		
		Function<Integer, Boolean> printerInteger = (number) -> {
			System.err.println(number);
			return Boolean.TRUE;
		};
		
		composed = upper.andThen(sizer).andThen(printerInteger);
		composed.apply(text);
	}

}
