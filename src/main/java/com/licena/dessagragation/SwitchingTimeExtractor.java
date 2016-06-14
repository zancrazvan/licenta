package com.licena.dessagragation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hsqldb.lib.HashMap;

import com.licenta.entity.SwitchingTime;
import com.licenta.entity.TimeSlot;
import com.licenta.mockObject.TimeSeriesBit;

public class SwitchingTimeExtractor {

	public List<TimeSlot> convertToTimeSlots(List<TimeSeriesBit> train,
			int timeSlotSize) {
		Collections.sort(train);

		int counter = 0;
		int time = 0;
		TimeSlot currentTimeSlot = new TimeSlot();
		List<TimeSlot> toRet = new ArrayList<TimeSlot>();
		int maxPower = 0;
		int medPower = 0;
		int powerCounts = 0;
		Date crtDate = train.get(counter).getDate();
		System.out.println(crtDate.toString());
		currentTimeSlot.setReferenceDate(crtDate);
		while (counter < train.size()) {
			TimeSeriesBit t = train.get(counter);

			if (t.getDate().getTime() - crtDate.getTime() > 1000 * 60 * timeSlotSize) {
				crtDate = t.getDate();
				currentTimeSlot.setTime(time);
				time++;
				medPower = medPower / powerCounts;
				if (((double) medPower / (double) maxPower) > 0.9) {
					currentTimeSlot.setPower(medPower);
				} else if (((double) medPower / (double) maxPower) > 0.6) {
					currentTimeSlot.setPower(maxPower);
				} else {
					currentTimeSlot.setPower(0);
				}

				maxPower = 0;
				medPower = 0;
				powerCounts = 0;

				toRet.add(currentTimeSlot);
				currentTimeSlot = new TimeSlot();
				currentTimeSlot.setReferenceDate(crtDate);

			} else {
				if (maxPower < t.getValue()) {
					maxPower = t.getValue();
				}
				medPower += t.getValue();
				powerCounts++;
			}

			counter++;
		}

		return toRet;
	}

	public List<SwitchingTime> extractSwitchingTimes(List<TimeSlot> timeSlots) {
		int treshold = 10;
		List<SwitchingTime> sws = new ArrayList<>();
		Collections.sort(timeSlots);
		TimeSlot t0 = new TimeSlot();
		 
		t0.setPower(0);
		t0.setTime(-1);
		SwitchingTime current = null;
		for (int i = 0; i < timeSlots.size(); i++) {
			if (i == 0) {
				if (timeSlots.get(0).getPower() - t0.getPower() > treshold) {
					SwitchingTime s = new SwitchingTime();
					s.setAbsolutePower(timeSlots.get(0).getPower());
					s.setTime(i);
					s.setRelativePower(timeSlots.get(0).getPower()
							- t0.getPower());
					current = s;
					sws.add(s);
				}
			} else {
				if (timeSlots.get(i).getPower()
						- timeSlots.get(i - 1).getPower() > treshold) {
					SwitchingTime s = new SwitchingTime();
					s.setAbsolutePower(timeSlots.get(i).getPower());
					s.setTime(i);
					s.setRelativePower(timeSlots.get(i).getPower()
							- timeSlots.get(i - 1).getPower());
					current = s;
					sws.add(s);
				}
			}
		}

		Map<Integer, Integer> fallingEdges = new TreeMap<Integer, Integer>();
		for (int i = 1; i < timeSlots.size(); i++) {
			TimeSlot t1 = timeSlots.get(i);
			TimeSlot prev = timeSlots.get(i - 1);

			if (prev.getPower() - t1.getPower() > 0) {
				fallingEdges.put(i, prev.getPower() - t1.getPower());
				
			}
		}

		 
		for (Integer tm : fallingEdges.keySet()) {
			
			for (SwitchingTime s : sws) {
				if (s.getRelativePower() == fallingEdges.get(tm)) {
					s.setRunningTime(timeSlots.get(tm).getTime()- s.getTime());
					System.out.println("fall: "+s.getRelativePower()+" @"+timeSlots.get(tm).getTime());
					 
				}
			}
		}
		return sws;
	}
}
