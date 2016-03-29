package com.licenta.component;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.licenta.document.Aparat;
import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;

@Component
public class Converter {

	public Aparat convert(String deviceName,DeviceMock d) {
		Aparat a = new Aparat();
		a.setName(deviceName);
		List<TimeSeriesBit> train = d.getCurba();
		Collections.sort(train);
		TimeSeriesBit[] window = new TimeSeriesBit[10];
		for (int i = 0; i < 10; i++) {
			window[i] = train.get(i);
		}
		if (constantTrain(train)) {
			return alwaysRunner(train,deviceName);
		}

		boolean deviceRunning = false;
		Date start = null, end;
		int count = 0;
		int power = 0;
		for (int i = 10; i < train.size(); i++) {
			pushInto(window, train.get(i));
			if (!deviceRunning) {
				if (rising(window)) {
					deviceRunning = true;
					start = train.get(i).getDate();
					System.out.print(a.getName()+": "+start + "      ");
				}
			} else {
				if (falling(window)) {
					deviceRunning = false;
					end = train.get(i).getDate();
					
					try {
						System.out.println(end + "     pwr: " + (power / count));
						a.setRunningTime((int) ((end.getTime() - start.getTime()) / 60000));
						a.setPutere(power / count);
					} catch (Exception e) {
						System.out.println(end + "     pwr: 0" );
						a.setRunningTime((int) ((end.getTime() - start.getTime()) / 60000));
						a.setPutere(0);
					}
					a.setAlways(false);
					return a;
				} else {
					power += train.get(i).getValue();
					count++;
				}
			}
		}
		return null;
	}

	private Aparat alwaysRunner(List<TimeSeriesBit> train,String deviceName) {
		int power = 0;
		for (int i = 0; i < train.size(); i++) {
			power += train.get(i).getValue();
		}
		Aparat a = new Aparat();
		a.setAlways(true);
		a.setPutere(power / train.size());
		a.setName(deviceName);
		System.out.println(a.getName()+": device runns constantly(power: " + a.getPutere()
				+ ")");
		return a;
	}

	private boolean constantTrain(List<TimeSeriesBit> train) {

		boolean zeroes = false;
		int counter = 0;
		for (int i = 0; i < train.size(); i++) {
			if (train.get(i).getValue() == 0) {
				if (!zeroes) {
					zeroes = true;
					counter = 1;
				} else {
					counter++;
				}
			}
			if (counter > 60) {
				return false;
			}
		}

		return true;
	}

	private boolean falling(TimeSeriesBit[] window) {
		for (int i = 0; i < 9; i++) {
			if (window[i].getValue() > window[i + 1].getValue()
					&& window[i + 1].getValue() <= 1) {
				return true;
			}
		}
		return false;
	}

	private boolean rising(TimeSeriesBit[] window) {

		for (int i = 0; i < 9; i++) {
			if (window[i].getValue() < window[i + 1].getValue()
					&& window[i + 1].getValue() > 0) {
				return true;
			}
		}
		return false;
	}

	private void pushInto(TimeSeriesBit[] window, TimeSeriesBit timeSeriesBit) {
		for (int i = 0; i < 9; i++) {
			window[i] = window[i + 1];
		}
		window[9] = timeSeriesBit;
	}

}
