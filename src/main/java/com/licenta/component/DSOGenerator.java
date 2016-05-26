package com.licenta.component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;

@Component
public class DSOGenerator {

	@Autowired
	private Randomizer randomizer;

	public DeviceMock generate(int min) {
		Date d = new Date();
		List<TimeSeriesBit> bits = new ArrayList<TimeSeriesBit>();
		d.setHours(0);
		d.setSeconds(0);
		for (int i = 0; i < 60 * 60 * 24; i++) {
			TimeSeriesBit t = new TimeSeriesBit();
			t.setDate(d);
			t.setValue(min);
			d.setSeconds(d.getSeconds() + 1);
			bits.add(t);
		}
		DeviceMock toRet = new DeviceMock();
		toRet.setCurba(bits);
		return toRet;
	}

	public void generateDSOCsv(String filename, int minConsumption) {

		File file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file.getName(), false);
			BufferedWriter writer = new BufferedWriter(fileWritter);

			Date d = new Date();

			d.setHours(0);
			d.setSeconds(0);
			for (int i = 0; i < 60 * 60 * 24; i++) {
				TimeSeriesBit t = new TimeSeriesBit();
				t.setDate(d);
				t.setValue(minConsumption);
				d.setSeconds(d.getSeconds() + 1);
				int val = minConsumption + ((i / 3600) % 12) * 50;
				System.out.println(((i / 3600) % 12) * 50);
				writer.write(d.toGMTString() + "," + val);
				writer.write("\r\n");

			}

			writer.close();
		} catch (IOException ex) {
			// report
		}
	}

	public Map<Date, Float> generateDSOCsv(int minConsumption, int max) {

		Map<Date, Float> device = new TreeMap<Date, Float>();
		Date d = new Date();

		d.setHours(0);
		d.setSeconds(0);
		float current = 0;
		for (int i = 0; i < 60 * 60 * 24; i++) {

			if (i % 12000 == 0) {

				current = minConsumption
						+ randomizer.generate(minConsumption, max);
			}
			if (i % 12000 < 9000) {
				device.put(d, current);
			} else {
				device.put(d, .0f);
			}
			System.out.println(device.get(d));
			d.setSeconds(d.getSeconds() + 1);

		}

		return device;

	}

	public void generateDSOCsv(String filename, int minConsumption, int max) {

		File file = new File(filename);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWritter = new FileWriter(file.getName(), false);
			BufferedWriter writer = new BufferedWriter(fileWritter);

			Date d = new Date();

			d.setHours(0);
			d.setSeconds(0);
			float current = 0;
			float val;
			for (int i = 0; i < 60 * 60 * 24; i++) {
				if (i % 12000 == 0) {

					current = minConsumption
							+ randomizer.generate(minConsumption, max);

				}
				if (i % 12000 < 9000) {
					val = current;
				} else {
					val = 100;
				}

				writer.write(d.toGMTString() + "," + val);
				d.setSeconds(d.getSeconds() + 1);
				writer.write("\r\n");

			}

			writer.close();
		} catch (IOException ex) {
			// report
		}
	}

	public DeviceMock getConsumptionPattern(int noOfFeatures, int minPwr,
			int maxPwr) {
		DeviceMock device = new DeviceMock();

		List<TimeSeriesBit> train = new ArrayList<TimeSeriesBit>();
		Date d = new Date();
		d.setHours(0);
		d.setMinutes(0);
		d.setSeconds(1);
		int val;
		int currentPwr = randomizer.generate(minPwr, maxPwr);
		for (int i = 0; i < 24 * 60 * 60; i++) {
			if (i % 12000 == 0) {

				currentPwr = randomizer.generate(minPwr, maxPwr);

			}
			if (i % 12000 < 9000) {
				val = currentPwr;
			} else {
				val = 0;
			}
			TimeSeriesBit t = new TimeSeriesBit();
			t.setDate(d);
			t.setValue(val);
			train.add(t);

		}
		device.setCurba(train);
		return device;
	}

}
