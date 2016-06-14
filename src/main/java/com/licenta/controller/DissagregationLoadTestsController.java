package com.licenta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DissagregationLoadTestsController {

	@RequestMapping("/dissagregationLoadTests")
	public String getPage(Model model){
		
		return "dissagregationLoadTests";
	}
}
