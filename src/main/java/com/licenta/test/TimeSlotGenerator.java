package com.licenta.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.binpacking.util.Randomizer;
import com.licenta.entity.Device;
import com.licenta.entity.TimeSlot;

public class TimeSlotGenerator {

	public static List<TimeSlot> generateDemo() {
		List<TimeSlot> times = new ArrayList<TimeSlot>();
		Date d = new Date();
		for (int i = 0; i < 30; i++) {

			times.add(new TimeSlot(i, i, 0, new Date(d.getTime() + i * 1000
					* 60 * 10)));

		}

		times.add(new TimeSlot(30, 30, 200, new Date(d.getTime() + 30 * 1000
				* 60 * 10)));
		times.add(new TimeSlot(31, 31, 500, new Date(d.getTime() + 31 * 1000
				* 60 * 10)));
		times.add(new TimeSlot(32, 32, 300, new Date(d.getTime() + 32 * 1000
				* 60 * 10)));
		for (int i = 33; i < 120; i++) {
			times.add(new TimeSlot(i, i, 0, new Date(d.getTime() + i * 1000
					* 60 * 10)));
		}

		return times;
	}

	public static List<TimeSlot> generateRandomDevices(
			Map<Device, Integer> devicesWithStartingTimes, int maxTime) {
		TimeSlot[] times = new TimeSlot[maxTime];
		List<TimeSlot> slots = new ArrayList<>();
		for (int i = 0; i < maxTime; i++) {
			times[i] = new TimeSlot(i, i, 0);

		}

		for (Device d : devicesWithStartingTimes.keySet()) {

			int runningTime = Randomizer.generate(15, 15);
			int start = devicesWithStartingTimes.get(d);
		//	System.out.println(d.getName() + " " + runningTime);
			for (int i = 0; i < runningTime; i++) {
				int crt = times[i + start].getPower();
				times[i + start].setPower(crt + d.getPower());
			}
		}

		for (int i = 0; i < times.length; i++) {
			slots.add(times[i]);
		}
		return slots;
	}
}
