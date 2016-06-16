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
	@Autowired
	private DissagregationLoadTestService loadTestService;
	
	@PostConstruct
	public void init() {
		 
		//loadTestService.incrementNumberOfDevices(50, 5000, 50);
		//loadTestService.incrementNumberOfSwitchingTimes(5, 995, 5);
		//loadTestService.incrementGenerations(50, 1000, 50);
		//loadTestService.incrementPopulationSize(100, 10000, 100);
		System.out.println("end");
		
	}

}
