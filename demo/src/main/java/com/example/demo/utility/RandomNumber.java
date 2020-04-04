package com.example.demo.utility;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumber {

	/*
	 * public static void main(String[] args) { random(); }
	 */
	public static long random() {
	    /* return a random long of 16 length */
	    long smallest = 1000_0000_0000_0000L;
	    long biggest =  9999_9999_9999_9999L;

	    // return a long between smallest and biggest (+1 to include biggest as well with the upper bound)
	    long random = ThreadLocalRandom.current().nextLong(smallest, biggest+1);
	    System.out.println(random);
	    return random;
	}
}
