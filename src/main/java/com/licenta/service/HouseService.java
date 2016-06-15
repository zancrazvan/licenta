package com.licenta.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.licenta.entity.House;
import com.licenta.repository.HouseRepository;

@Service
@Transactional
public class HouseService {

	@Autowired
	private HouseRepository houseRepository;

	public void save(House house) {

		houseRepository.save(house);
	}

	public House findOne(int id) {

		return houseRepository.findOne(id);
	}

	public List<House> findAll() {

		return houseRepository.findAll();
	}
}
