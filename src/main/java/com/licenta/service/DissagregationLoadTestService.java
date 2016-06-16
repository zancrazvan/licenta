package com.licenta.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knapsack.DeviceGenerator;
import com.knapsack.GeneticAlgorithm;
import com.knapsack.SwitchingTimeGenerator;
import com.licenta.entity.Device;
import com.licenta.entity.DissagregationLoadTest;
import com.licenta.entity.SwitchingTime;
import com.licenta.repository.DissagregationLoadTestRepository;

@Service
public class DissagregationLoadTestService {

	@Autowired
	private DissagregationLoadTestRepository dissagregationLoadTestRepository;

	public void incrementNumberOfDevices(int from, int to, int step) {
		int swNumber = 50;
		for (int i = from; i < to; i += step) {
			List<Device> devices = DeviceGenerator.generateDevices(i);
			List<SwitchingTime> sws = SwitchingTimeGenerator
					.generateSwitchingTimes(swNumber);
			long start = System.currentTimeMillis();
			GeneticAlgorithm.geneticSolution(devices, sws, 200, 100, 0);
			long end = System.currentTimeMillis();
			DissagregationLoadTest test = new DissagregationLoadTest();
			test.setNumberOfDevices(i);
			test.setNumberOfSwitchingTimes(swNumber);
			test.setTime(end-start);
			dissagregationLoadTestRepository.save(test);
		}
	}
	
	public void incrementNumberOfSwitchingTimes(int from, int to, int step) {
		int nodevices = 1000;
		for (int i = from; i < to; i += step) {
			List<Device> devices = DeviceGenerator.generateDevices(nodevices);
			List<SwitchingTime> sws = SwitchingTimeGenerator
					.generateSwitchingTimes(i);
			long start = System.currentTimeMillis();
			GeneticAlgorithm.geneticSolution(devices, sws, 200, 100, 0);
			long end = System.currentTimeMillis();
			DissagregationLoadTest test = new DissagregationLoadTest();
			test.setNumberOfDevices(nodevices);
			test.setNumberOfSwitchingTimes(i);
			test.setTime(end-start);
			dissagregationLoadTestRepository.save(test);
		}
	}
	
	public void incrementPopulationSize(int from, int to, int step) {
		int nodevices = 1000;
		for (int i = from; i < to; i += step) {
			List<Device> devices = DeviceGenerator.generateDevices(100);
			List<SwitchingTime> sws = SwitchingTimeGenerator
					.generateSwitchingTimes(30);
			long start = System.currentTimeMillis();
			GeneticAlgorithm.geneticSolution(devices, sws, i, 100, 0);
			long end = System.currentTimeMillis();
			DissagregationLoadTest test = new DissagregationLoadTest();
			test.setNumberOfDevices(nodevices);
			test.setNumberOfSwitchingTimes(i);
			test.setTime(end-start);
			dissagregationLoadTestRepository.save(test);
		}
	}
	public void incrementGenerations(int from, int to, int step) {
		int nodevices = 1000;
		for (int i = from; i < to; i += step) {
			List<Device> devices = DeviceGenerator.generateDevices(100);
			List<SwitchingTime> sws = SwitchingTimeGenerator
					.generateSwitchingTimes(30);
			long start = System.currentTimeMillis();
			GeneticAlgorithm.geneticSolution(devices, sws, 2000, i, 0);
			long end = System.currentTimeMillis();
			DissagregationLoadTest test = new DissagregationLoadTest();
			test.setNumberOfDevices(2000);
			test.setNumberOfSwitchingTimes(i);
			test.setTime(end-start);
			dissagregationLoadTestRepository.save(test);
		}
	}
}
