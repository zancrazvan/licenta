package com.licenta.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import com.licenta.mockObject.Device;
import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;

public class FileParser {

	private DateConverter dateConverter = new DateConverter();

	public DeviceMock readFile(String file) {
		Device device = new Device();
		Map<Date, Integer> curba = new TreeMap<Date, Integer>();
		DeviceMock dev = new DeviceMock();
		ArrayList<TimeSeriesBit> bits = new ArrayList<TimeSeriesBit>();
		String csvFile = file;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				if (!line.contains("sep")) {
					String[] values = line.split(cvsSplitBy);

					TimeSeriesBit bt = new TimeSeriesBit();
					bt.setDate(dateConverter.convert(values[0]));
					bt.setValue(Integer.parseInt(values[1]));
					bits.add(bt);

					curba.put(dateConverter.convert(values[0]),
							Integer.parseInt(values[1]));
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		device.setCurba(curba);
		dev.setCurba(bits);
		return dev;
	}
}
