package br.jteodoro.lambdas.main;

public class MaxSumSubarrayProblem {
	
	public static void main (String [] args) {
		int [] array = {-2, -3, -4, 4, -1, -2, 1, 5, -3, 0};
		new Assertion().assertThat(7, new MaxContinguosSubarrayFinder().find(array));
	}
}

class MaxContinguosSubarrayFinder {
	
	//Kadane's algorithm
	public String find(int[] array) {
		int size = array.length;
        int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
        int start = 0;
        int end = 0;
        for (int i = 0; i < size; i++) {
            max_ending_here = max_ending_here + array[i];
            if (max_so_far < max_ending_here) {
                max_so_far = max_ending_here;
            		end = i;
            }
            if (max_ending_here < 0) {
                max_ending_here = 0;
                start = i+1;
            }
        }
        return String.format("Sum from %s to %s: %s", start, end, max_so_far);
	}
	
}