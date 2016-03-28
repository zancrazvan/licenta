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

import com.licenta.component.DSOGenerator;
import com.licenta.component.FileParser;
import com.licenta.document.User;
import com.licenta.mockObject.Device;
import com.licenta.mockObject.DeviceMock;
import com.licenta.mockObject.TimeSeriesBit;
import com.licenta.repository.UserRepository;

@Service
@Transactional
public class DBServiceGhita {
	@Autowired
	private FileParser fileParser;

	@Autowired
	private DSOGenerator dsoGen;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void init() {
		dsoGen.generateDSOCsv("demo.csv", 750);
		User user = new User();
		user.setId(1);
		user.setName("Nume");

		userRepository.save(user);
	}

 
}
