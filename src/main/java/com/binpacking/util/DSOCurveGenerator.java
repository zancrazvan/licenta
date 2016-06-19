package com.binpacking.util;

import java.util.ArrayList;
import java.util.List;

import com.binpacking.bin.Bin;

public class DSOCurveGenerator {

	public List<Bin> generateBinCurve(int curveType) {

		System.out.println("--- GENERATING DSO CURVE ---");

		List<Bin> bins = new ArrayList<>();

		int scheduling1[] = { 3000, 3000, 3000, 3000, 3000, 3100, 3200, 3300, 3400, 3500, 3550, 3600, 3650, 3700, 3750,
				3750, 3700, 3650, 3600, 3550, 3500, 3400, 3300, 3200, 3100, 3000, 3000, 3000, 3000, 3000 };
		int scheduling2[] = { 4000, 4000, 4000, 4000, 4000, 3900, 3800, 3700, 3600, 3500, 3450, 3400, 3350, 3300, 3250,
				3250, 3300, 3350, 3400, 3450, 3500, 3600, 3700, 3800, 3900, 4000, 4000, 4000, 4000, 4000 };
		int loadFollowing[] = { 3000, 3000, 3000, 3050, 3100, 3125, 3150, 3175, 3150, 3200, 3275, 3375, 3500, 3600,
				3700, 3750, 3800, 3850, 3875, 3900, 3925, 3950, 4000, 4000, 4000 };

		if (curveType == 0) {

			for (int i = 0; i < scheduling1.length; i++) {

				Bin bin = new Bin();
				bin.setId(i);
				bin.setCapacity(scheduling1[i]);
				bins.add(bin);
			}
		}

		if (curveType == 1) {

			for (int i = 0; i < scheduling2.length; i++) {

				Bin bin = new Bin();
				bin.setId(i);
				bin.setCapacity(scheduling2[i]);
				bins.add(bin);
			}

		}

		if (curveType == 2) {

			for (int i = 0; i < loadFollowing.length; i++) {

				Bin bin = new Bin();
				bin.setId(i);
				bin.setCapacity(loadFollowing[i]);
				bins.add(bin);
			}

		}

		return bins;

	}

}
