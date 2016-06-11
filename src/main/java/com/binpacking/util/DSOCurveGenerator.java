package com.binpacking.util;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.bin.Bin;

public class DSOCurveGenerator {

	public List<Bin> generateBinCurve() {

		System.out.println("--- GENERATING DSO CURVE ---");

		List<Bin> bins = new ArrayList<>();

		for (int i = 0; i < 10; i++) { 

			Bin bin = new Bin();
			bin.setId(i);
			bin.setCapacity(Randomizer.generate(300, 2000));
			bins.add(bin);
		}

		return bins;

	}

}
