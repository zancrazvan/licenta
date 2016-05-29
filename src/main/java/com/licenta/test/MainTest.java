package com.licenta.test;

import java.util.List;

import com.licena.dessagragation.SwitchingTimeExtractor;
import com.licenta.entity.TimeSlot;
import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;
import com.licenta.writer.CSVWriter;

public class MainTest {

	public static void main(String[] args) {

		/*
		 * DSOGenerator gen = new DSOGenerator(); DeviceMock m =
		 * gen.getConsumptionPattern(8, 200, 2000);
		 * 
		 * CSVGenerator g = new CSVGenerator(); g.writeToCsv("demo.csv", m);
		 * 
		 * FeatureExtractor feat = new FeatureExtractor(); List<Aparat> aparate
		 * = feat.extractFeatures(m);
		 * System.out.println("aparate: "+aparate.size());
		 */

		FileParser f = new FileParser();
		DeviceMock m = f
				.readFile("E:\\Licenta\\incomplete\\Washingmachine\\dev_B8198B_2011.12.11_cleaned_11.12.2011.csv");
		SwitchingTimeExtractor extr = new SwitchingTimeExtractor();
		List<TimeSlot> newTrain = extr.convertToTimeSlots(m.getCurba(), 10);

		for (TimeSlot t : newTrain) {
			CSVWriter.appendString("E:/Licenta/tests/demo.csv", t
					.getReferenceDate().toString() + "," + t.getPower());
		}
 
	}
}
