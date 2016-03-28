package com.licenta.component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;

@Component
public class DSOGenerator {

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
	public void generateDSOCsv(String filename, int minConsumption,int max) {

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
			int current;
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
}
