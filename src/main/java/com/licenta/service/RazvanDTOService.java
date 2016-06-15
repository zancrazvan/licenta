package com.licenta.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knapsack.Chromosome;
import com.licenta.entity.Device;
import com.licenta.entity.DeviceDTO;
import com.licenta.entity.RazvanDTO;
import com.licenta.entity.SwitchingTime;
import com.licenta.repository.DeviceDTORepository;
import com.licenta.repository.RazvanDTORepository;

@Service
public class RazvanDTOService {

	@Autowired
	private DeviceDTORepository deviceDTORepository;

	@Autowired
	private RazvanDTORepository razvanDTORepository;

	public void convertDissagregationSolutionToOprimizationImputs(Chromosome c) {

		Map<SwitchingTime, Device> solution = c.getSolution();
		List<DeviceDTO> dtos = new ArrayList<>();
		for (SwitchingTime s : solution.keySet()) {
			Device d = solution.get(s);
			DeviceDTO dto = new DeviceDTO();
			dto.createDTO(d, s);
			deviceDTORepository.save(dto);
			dtos.add(dto);

		}
		RazvanDTO razvan = new RazvanDTO();
		razvan.setDevices(dtos);
		razvanDTORepository.save(razvan);

	}
}
