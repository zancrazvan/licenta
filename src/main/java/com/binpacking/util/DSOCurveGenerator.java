package com.binpacking.util;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.bin.Bin;

public class DSOCurveGenerator {

	public static List<Bin> generateBinCurve() {

		List<Bin> bins = new ArrayList<>();

		for (int i = 0; i < 144; i++) {

			Bin bin = new Bin();
			bin.setId(i);
			bin.setCapacity(Randomizer.generate(500, 3000));
			bins.add(bin);
		}

		return bins;

	}

}
