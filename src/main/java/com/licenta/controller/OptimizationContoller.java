package com.licenta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OptimizationContoller {

	@RequestMapping(value = "/optimizationData", method = RequestMethod.GET)
	public String getOptimization() {

		return "optimizationData";
	}

	@RequestMapping(value = "/optimizationData", method = RequestMethod.POST)
	public String postOptimizationData(Model model,
			@RequestParam(value = "nrOfGenerations", required = false) String nrOfGenerations,
			@RequestParam(value = "nrOfIndividuals", required = false) String nrOfIndividuals) {

		System.out.println(nrOfGenerations + " " + nrOfIndividuals);
		return "optimizationData";
	}

}
