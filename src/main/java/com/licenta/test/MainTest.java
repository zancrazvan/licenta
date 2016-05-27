package com.licenta.test;

import java.util.List;

import com.licena.dessagragation.FeatureExtractor;
import com.licena.dessagragation.TrainSharpener;
import com.licenta.component.CSVGenerator;
import com.licenta.component.DSOGenerator;
import com.licenta.document.Aparat;
import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;
import com.licenta.writer.CSVWriter;

public class MainTest {

	public static void main(String[] args) {

		/*DSOGenerator gen = new DSOGenerator();
		DeviceMock m = gen.getConsumptionPattern(8, 200, 2000);

		CSVGenerator g = new CSVGenerator();
		g.writeToCsv("demo.csv", m);

		FeatureExtractor feat = new FeatureExtractor();
		List<Aparat> aparate = feat.extractFeatures(m);
		System.out.println("aparate: "+aparate.size());*/
		
		FileParser f = new FileParser();
		DeviceMock m = f.readFile("E:\\Licenta\\incomplete\\Washingmachine\\dev_B8198B_2011.12.11_cleaned_11.12.2011.csv");
		TrainSharpener t = new TrainSharpener();
		List<TimeSeriesBit> zzz = t.sharpen(m.getCurba());
		
		CSVWriter c = new CSVWriter();
		c.trainToCsv("E:\\Licenta\\incomplete\\Washingmachine\\demo.csv", zzz);
	}

}
