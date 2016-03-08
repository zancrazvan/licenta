package com.licenta.test;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

import com.licenta.mockObject.DeviceMock;

public class MainTest {

	public static void main(String[] args) {

		FileParser fileParser = new FileParser();
		DeviceMock d = fileParser
				.readFile("E:\\STS\\GitRepo\\licenta\\src\\main\\resources\\csvData/Freezer/device_4BA15B_2012.01.12_cleaned_12.01.2012.csv");
		double[] point = new double[d.getCurba().size()];
		for (int i = 0; i < point.length; i++) {
			point[i] = d.getCurba().get(i).getValue();
		}
		FileWorker f = new FileWorker();
		//f.write("input.txt", point);
		String inputFile = "input.txt";
		int k = 2;
		int iterations = 2;
		int runs = 2;
		System.out.println("HERE");
		SparkConf sparkConf = new SparkConf().setAppName("JavaKMeans")
				.setMaster("local");
		JavaSparkContext sc = new JavaSparkContext(sparkConf);

		/*
		 * JavaRDD<Vector> points = lines.map((Function<String, Vector>) Vectors
		 * .dense(point));
		 */

		JavaRDD<String> data = sc.textFile(inputFile);
		JavaRDD<Vector> points = data.map(new Function<String, Vector>() {
			public Vector call(String s) {
				String[] sarray = s.split(" ");
				double[] values = new double[sarray.length];
				for (int i = 0; i < sarray.length; i++)
					values[i] = Double.parseDouble(sarray[i]);
				return Vectors.dense(values);
			}
		});
		points.cache();

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

}
