package com.licenta.test;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.mllib.clustering.KMeans;
import org.apache.spark.mllib.clustering.KMeansModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;

import com.licenta.component.Converter;
import com.licenta.component.DSOGenerator;
import com.licenta.mockObject.DeviceMock;

public class MainTest {

	public static void main(String[] args) {
		DSOGenerator g = new DSOGenerator();
		g.generateDSOCsv("demo.csv", 500, 1000);

		FileParser f = new FileParser();
		 

		ListFilesUtil fd = new ListFilesUtil();
		fd.listFilesAndFilesSubDirectories("E:\\Licenta\\incomplete");
		System.out.println("Done");
	}

}
