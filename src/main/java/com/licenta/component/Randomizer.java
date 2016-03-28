package com.licenta.component;

import org.springframework.stereotype.Component;

@Component
public class Randomizer {

	public static int generate(int min, int max) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

}
