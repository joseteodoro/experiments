package br.jteodoro.lambdas.main;

public class AssertionClass {

	public static void main(String [] args) {
		assertThat("my string", null);
		assertThat("my string", "my string");
		assertThat(false, Integer.MIN_VALUE);
		
		new Assertion().assertThat(1,  1);
		new Assertion().assertThat(null,  "null");
	}
	
	static <T> void assertThat(T expected, T got) {
		if (expected == null || got == null  || !expected.equals(got)) {
			System.out.println(String.format("Failed! Expected: { %s } but got { %s }.", expected, got));
			return;
		}
		System.out.println("Succeed.");
	}
	
	
	
}

class Assertion {

    public <T> void assertThat(T expected, T got) {
        if (expected == null || got == null || !expected.equals(got) ) {
            System.out.println(String.format("Failed! Expected: { %s } but got { %s }.", expected, got));
            return;
        }
        System.out.println("Succeed.");
    }

}







class ReAssertion {
	public <T> boolean assertThat(T expected, T got) {
		if ( expected == null || got == null || !expected.equals(got) ) {
			System.out.println(String.format("Failed. Expected { %s } but got { %s } ", expected, got));
			return false;
		}
		System.out.println("Succeed.");
		return true;
	}
}
