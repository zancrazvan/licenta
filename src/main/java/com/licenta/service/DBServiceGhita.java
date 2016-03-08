package com.licenta.service;

import javax.annotation.PostConstruct;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.licenta.component.FileParser;
import com.licenta.mockObject.Device;
import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;

@Service
@Transactional
public class DBServiceGhita {
	@Autowired
	private FileParser fileParser;

	@PostConstruct
	public void init() {

		System.out.println("22");
		// /
		// Licenta/src/main/webapp/csvData/Freezer/device_4BA15B_2012.01.12_cleaned_12.01.2012.csv

		/*
		 * public Vector call(String line) { String[] tok = SPACE.split(line);
		 * double[] point = new double[tok.length]; for (int i = 0; i <
		 * tok.length; ++i) { point[i] = Double.parseDouble(tok[i]); } return
		 * Vectors.dense(point); }
		 */

		DeviceMock d = fileParser
				.readFile("csvData/Freezer/device_4BA15B_2012.01.12_cleaned_12.01.2012.csv");
		double[] point = new double[d.getCurba().size()];
		for (int i = 0; i < point.length; i++) {
			point[i] = d.getCurba().get(i).getValue();
		}
		String inputFile = "";
		int k = 1;
		int iterations = 1;
		int runs = 1;
		System.out.println("HERE");
		SparkConf sparkConf = new SparkConf().setAppName("JavaKMeans");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);
		JavaRDD<String> lines = sc.textFile(inputFile);

		JavaRDD<Vector> points = lines.map((Function<String, Vector>) Vectors
				.dense(point));

		KMeansModel model = KMeans.train(points.rdd(), k, iterations, runs,
				KMeans.K_MEANS_PARALLEL());

		System.out.println("Cluster centers:");
		for (Vector center : model.clusterCenters()) {
			System.out.println(" " + center);
		}
		double cost = model.computeCost(points.rdd());
		System.out.println("Cost: " + cost);

		sc.stop();

	}

	/*
	 * private static class ParsePoint implements Function<String, Vector> {
	 * 
	 * 
	 * @Override public Vector call(double[] points) { String[] tok =
	 * SPACE.split(line); double[] point = new double[tok.length]; for (int i =
	 * 0; i < tok.length; ++i) { point[i] = Double.parseDouble(tok[i]); } return
	 * Vectors.dense(point); }
	 * 
	 * @Override public Vector call(String v1) throws Exception { // TODO
	 * Auto-generated method stub return null; } }
	 */
}
