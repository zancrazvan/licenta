package com.licenta.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.licenta.component.FileParser;
import com.licenta.mockObject.DeviceMock;

@Controller
public class TestController {

	@Autowired
	private FileParser fileParser;

	@RequestMapping("/chart-example")
	public String chartExample() {
		return "chart-example";
	}

	@RequestMapping("get-demo-data")
	public @ResponseBody DeviceMock getDemoData() {
		System.out.println(System.getProperty("user.dir"));
		return fileParser
				.readFile(System.getProperty("user.dir")+"/src/main/resources/csvData/Freezer/device_4BA15B_2012.01.12_cleaned_12.01.2012.csv");

	}

}
