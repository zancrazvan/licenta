package com.licenta.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.licenta.component.DSOGenerator;
import com.licenta.component.FileParser;
import com.licenta.document.Aparat;
import com.licenta.document.User;
import com.licenta.repository.AparatRepository;
import com.licenta.repository.UserRepository;

@Service
@Transactional
public class DBServiceGhita {
	 

	@Autowired
	private DSOGenerator dsoGen;
	
	 @Autowired
	 private AparatRepository aparatRepository;
	
	@PostConstruct
	public void init() {
		 
		Aparat a = new Aparat();
	}

 
}
