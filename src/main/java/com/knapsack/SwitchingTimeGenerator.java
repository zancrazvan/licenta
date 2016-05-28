package com.knapsack;

import java.util.ArrayList;
import java.util.List;

public class SwitchingTimeGenerator {

	public static List<SwitchingTime> generateSwitchingTimes() {
		List<SwitchingTime> sws = new ArrayList<>();
		sws.add(new SwitchingTime(1, 125, 130, 3));
		sws.add(new SwitchingTime(3, 3000, 3125, 3));
		sws.add(new SwitchingTime(7, 2300, 2300, 3));
		sws.add(new SwitchingTime(8, 700, 2300, 3));
		sws.add(new SwitchingTime(9, 1812, 2300, 3));
		sws.add(new SwitchingTime(10, 514, 2300, 3));
		sws.add(new SwitchingTime(11, 523, 2300, 3));
		sws.add(new SwitchingTime(12, 543, 2300, 3));
		sws.add(new SwitchingTime(14, 650, 2300, 3));
		sws.add(new SwitchingTime(15, 721, 2300, 3));
		sws.add(new SwitchingTime(16, 789, 2300, 3));
		for (int i = 17; i < 75; i++) {
			sws.add(new SwitchingTime(i, i * 7, 7, 3));
		}

		return sws;
	}
}
