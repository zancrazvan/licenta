package com.licenta.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.binpacking.bin.Bin;
import com.binpacking.bin.BinDAO;
import com.binpacking.chromosome.Chromosome;
import com.binpacking.chromosome.ChromosomeDAO;
import com.binpacking.generation.Generation;
import com.binpacking.generation.GenerationDAO;
import com.binpacking.generation.GenerationFitness;
import com.binpacking.util.DSOCurveGenerator;
import com.licenta.service.OptimizationService;

@Controller
public class OptimizationContoller {

	@Autowired
	private OptimizationService optimizationService;

	@RequestMapping(value = "/optimizationData", method = RequestMethod.GET)
	public String getOptimization() {

		return "optimizationData";
	}

	@RequestMapping(value = "/optimizationData", method = RequestMethod.POST)
	public String postOptimizationData(Model model,
			@RequestParam(value = "nrOfGenerations", required = false) String nrOfGenerations,
			@RequestParam(value = "nrOfIndividuals", required = false) String nrOfIndividuals) {

		GenerationDAO generationDAO = new GenerationDAO();
		BinDAO binDAO = new BinDAO();

		DSOCurveGenerator curveGenerator = new DSOCurveGenerator();
		List<Bin> bins = curveGenerator.generateBinCurve();

		List<Generation> generatios = optimizationService.binPackingOptimization(Integer.parseInt(nrOfIndividuals),
				Integer.parseInt(nrOfGenerations), bins);

		List<Double> fitnessEvolution = new ArrayList<>();
		GenerationFitness generationFitness = new GenerationFitness();

		for (Generation generation : generatios) {

			fitnessEvolution
					.add(generationFitness.computeGenerationFitness(generation) / Integer.parseInt(nrOfIndividuals));

			System.out.println(
					generationFitness.computeGenerationFitness(generation) / Integer.parseInt(nrOfIndividuals));
		}

		model.addAttribute("fitnessEvolution", fitnessEvolution);

		Collections.sort(generatios, Collections.reverseOrder());

		List<Double> solution = new ArrayList<>();
		Chromosome chromosome = generationDAO.getBestChromosome(generatios.get(0));

		for (Bin bin : chromosome.getBins()) {
			solution.add(binDAO.getFilled(bin));
		}

		model.addAttribute("solution", solution);

		List<Double> dso = new ArrayList<>();
		for (Bin bin : bins) {
			dso.add(bin.getCapacity());
		}

		model.addAttribute("dso", dso);

		System.out.println(dso.size() + " " + solution.size()); 

		return "optimizationGraph";
	}

}
