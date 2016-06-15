package com.licenta.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knapsack.Chromosome;
import com.knapsack.GeneticAlgorithm;
import com.licena.dessagragation.SwitchingTimeExtractor;
import com.licenta.component.DSOGenerator;
import com.licenta.component.FileParser;
import com.licenta.document.Aparat;
import com.licenta.document.User;
import com.licenta.entity.Device;
import com.licenta.entity.DeviceDTO;
import com.licenta.entity.SwitchingTime;
import com.licenta.entity.TimeSlot;
import com.licenta.repository.AparatRepository;
import com.licenta.repository.DeviceRepository;
import com.licenta.repository.RazvanDTORepository;
import com.licenta.repository.UserRepository;
import com.licenta.test.TimeSlotGenerator;
import com.licenta.utilityData.DeviceGenerator;

@Service
@Transactional
public class DBServiceGhita {

	@Autowired
	private DSOGenerator dsoGen;

	@Autowired
	private AparatRepository aparatRepository;
	@Autowired
	private DeviceGenerator deviceGenerator;
	@Autowired
	private DeviceRepository deviceRepository;
	@Autowired
	private RazvanDTOService razvanDTOService;
	@Autowired
	private RazvanDTORepository razvanDTORepository;

	@PostConstruct
	public void init() {
		deviceGenerator.populateDB();

		List<Device> devices = deviceRepository.findAll();

		List<TimeSlot> slots = TimeSlotGenerator.generateRandomDevices(
				DeviceGenerator.generateDevicesWithStartingTimes(), 120);
		 
		SwitchingTimeExtractor t = new SwitchingTimeExtractor();
		List<SwitchingTime> sws = t.extractSwitchingTimes(slots);

		Chromosome best = null;
		Chromosome c1 = GeneticAlgorithm.geneticSolution(devices, sws, 2000,
				100, 50);
		
		razvanDTOService.convertDissagregationSolutionToOprimizationImputs(c1);
		
		for(DeviceDTO d: razvanDTORepository.findAll().get(0).getDevices()){
			System.out.println(d.toString());
		}
		
		
	}

}
