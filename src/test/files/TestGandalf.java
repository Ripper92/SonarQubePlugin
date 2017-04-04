package com.juliasoft.sonarqube.plugin;

public class TestClass {
	/*
	 * int foo1() { return 0; } void foo2(int value) { } int foo3(int value) {
	 * return 0; } // Noncompliant Object foo4(int value) { return null; }
	 * TestClass foo5(TestClass value) {return null; } // Noncompliant
	 * 
	 * int foo6(int value, String name) { return 0; } int foo7(int ... values) {
	 * return 0;}
	 */
	double loop1() {
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < 100) {
			while (j < 100) {
				while (k < 100) { // Noncompliant
					i++;
					j++;
					a++;
				}
			}
		}
		return k;
	}

	int for_each_loop(int a, int b) {
		int[] lucky_numbers = { 13, 22, 45, 78, 9, 22, 13, 87, 23 };
		int res = 0;
		for (int a : lucky_numbers) {
			while (i < 30) {
				res += a ^ a;
				do { // Noncompliant
					res++;
				} while (i % 2 == 0);
			}
		}
	}
	
	int loopFor(){
		int dimensions = 900;
		for(int i=0; i < dimensions; i++){
			for (int j =0; j < dimensions; j++){
				for (int k=0; k< dimensions; k++){	// Noncompliant
					for (int l=0; l< dimensions; l++){	// Noncompliant
						System.out.println(i +j + k + l);
					}
				}
			}
		}
	}

	void loop4() {
		int j = 0;
		for (int i = 0; i < 1000; ++i) {
			do {
				j++;
			} while (j < 100);
		}
	}

	void loop5() {
		int[] a = { 1, 2, 3, 4 };
		for (int i : a) {
			for (int j = 10; j > 0; --j) {
				System.out.println(i);
			}
		}
	}

	void loop6() {
		int i = 0;
		while (i < 50) {
			i++;
		}
	}
	
	

}
