package com.knapsack;

public class Range {
	public static double mapRange(double a1, double a2, double b1, double b2,
			double s) {
		return b1 + ((s - a1) * (b2 - b1)) / (a2 - a1);
	}
}
