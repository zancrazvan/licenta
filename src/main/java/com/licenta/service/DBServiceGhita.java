package com.licenta.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.licenta.component.FileParser;
import com.licenta.mockObject.Device;

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

		 
	}

}
