package com.licenta.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.knapsack.Chromosome;
import com.knapsack.GeneticAlgorithm;
import com.licena.dessagragation.SwitchingTimeExtractor;
import com.licenta.dto.ChartDto;
import com.licenta.entity.Device;
import com.licenta.entity.SwitchingTime;
import com.licenta.entity.TimeSlot;
import com.licenta.test.DeviceGenerator;
import com.licenta.test.TimeSlotGenerator;

@Controller
public class DissagregationController {

	@RequestMapping("/dissagregationDemo")
	public String getDissagregationDemo(Model model) {
		List<Device> devices = DeviceGenerator.generateDevices();

		List<TimeSlot> slots = TimeSlotGenerator.generateRandomDevices(
				DeviceGenerator.generateDevicesWithStartingTimes(), 120);
		for (int i = 0; i < slots.size(); i++) {
			System.out.println(slots.get(i).getTime() + ","
					+ slots.get(i).getPower());
		}
		SwitchingTimeExtractor t = new SwitchingTimeExtractor();
		List<SwitchingTime> sws = t.extractSwitchingTimes(slots);

		Chromosome best = null;
		Chromosome c1 = GeneticAlgorithm.geneticSolution(devices, sws, 2000,
				100, 50);
		c1.calculateError();
		best = c1;
		model.addAttribute("solutie", c1);
		model.addAttribute("switchingTimes", sws);
		model.addAttribute("devices", devices);

		return "dissagregationDemo";
	}

	@RequestMapping("/chartToBeDissagregated")
	public @ResponseBody ChartDto getsth() {
		ChartDto dto  =  new ChartDto();
		List<TimeSlot> slots = TimeSlotGenerator.generateRandomDevices(
				DeviceGenerator.generateDevicesWithStartingTimes(), 120);
		for (int i = 0; i < slots.size(); i++) {
			System.out.println(slots.get(i).getTime() + ","
					+ slots.get(i).getPower());
		}
		SwitchingTimeExtractor t = new SwitchingTimeExtractor();
		List<SwitchingTime> sws = t.extractSwitchingTimes(slots);

		dto.setSwitches(sws);
		dto.setTimeSlots(slots);
		return dto;
	}
	
	 
	 
}
