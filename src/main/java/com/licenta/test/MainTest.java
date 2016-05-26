package com.licenta.test;

import java.util.List;

import com.licena.dessagragation.FeatureExtractor;
import com.licenta.component.CSVGenerator;
import com.licenta.component.DSOGenerator;
import com.licenta.document.Aparat;
import com.licenta.mockObject.DeviceMock;

public class MainTest {

	public static void main(String[] args) {

		DSOGenerator gen = new DSOGenerator();
		DeviceMock m = gen.getConsumptionPattern(8, 200, 2000);

		CSVGenerator g = new CSVGenerator();
		g.writeToCsv("demo.csv", m);

		FeatureExtractor feat = new FeatureExtractor();
		List<Aparat> aparate = feat.extractFeatures(m);
		System.out.println("aparate: "+aparate.size());
	}

}
