package com.licenta.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binpacking.bin.Bin;
import com.binpacking.chromosome.Chromosome;
import com.binpacking.element.Element;
import com.binpacking.generation.Generation;
import com.binpacking.generation.GenerationDAO;
import com.binpacking.generation.GenerationFitness;
import com.binpacking.util.DeviceGenerator;
import com.binpacking.util.FileHandler;
import com.licenta.entity.DeviceDTO;
import com.licenta.repository.RazvanDTORepository;

@Service
@Transactional
public class OptimizationService {

	@Autowired
	private RazvanDTORepository dtoRepository;

	public List<Generation> binPackingOptimization(int nrOfIndividuals, int nrOfGenerations, List<Bin> bins) {

		DeviceGenerator deviceGenerator = new DeviceGenerator();
		List<Element> list = new ArrayList<>();
		// list = deviceGenerator.generateDevices();

		List<DeviceDTO> deviceDTOs = new ArrayList<>();
		deviceDTOs = dtoRepository.findAll().get(0).getDevices();

		int nr = 0;
		for (int i = 0; i < deviceDTOs.size(); i++) {
			Element element = new Element();
			element.setId(nr);
			element.setValue(deviceDTOs.get(i).getPutere());
			element.setName(deviceDTOs.get(i).getNume());
			nr++;
			list.add(element);

			for (int j = 0; j < deviceDTOs.get(i).getNumarBeanuri(); j++) {
				Element element2 = new Element();
				element2.setId(nr);
				element2.setValue(deviceDTOs.get(i).getPutere());
				element2.setName(deviceDTOs.get(i).getNume()); 
				nr++;
				list.add(element2);

			}
		}

		GenerationFitness generationFitness = new GenerationFitness();
		List<Generation> generations = new ArrayList<>();
		GenerationDAO generationDAO = new GenerationDAO();

		Generation generation = generationDAO.initializeFirstGeneration(list, nrOfIndividuals, bins);
		// generations.add(generation);

		String info = new String();
		String info2 = new String();
		List<Double> fitness = new ArrayList<>();

		info = info + "0 " + "," + generationFitness.computeGenerationFitness(generation) / nrOfIndividuals + ","
				+ "\r\n";
		info2 = info2 + generation.toString() + "\r\n";

		fitness.add(generationFitness.computeGenerationFitness(generation) / nrOfIndividuals);

		for (int i = 1; i <= nrOfGenerations; i++) {

			generation.setId(i);
			generation = generationDAO.selection(generation);
			generation = generationDAO.generateNextGeneration(generation, list);

			generation = generationDAO.mutate(generation);

			info = info + generation.getId() + ","
					+ generationFitness.computeGenerationFitness(generation) / nrOfIndividuals + "," + "\r\n";
			info2 = info2 + generation.toString() + "\r\n";

			fitness.add(generationFitness.computeGenerationFitness(generation) / nrOfIndividuals);

			Generation generation2 = new Generation();
			generation2.setId(i);
			List<Chromosome> chromosomes = new ArrayList<>();

			for (Chromosome chromosome : generation.getPopulation()) {
				Chromosome chromosome2 = new Chromosome();
				chromosome2.setBins(chromosome.getBins());
				chromosomes.add(chromosome2);
			}

			generation2.setPopulation(chromosomes);

			generations.add(generation2);
			System.out.println(generation.toString());

		}

		FileHandler fileHandler = new FileHandler();
		fileHandler.write(info, "binPackingResSPRING.csv");

		FileHandler fileHandler2 = new FileHandler();
		fileHandler2.write(info2, "binPackingLogSPRING.txt");

		return generations;

	}

}
