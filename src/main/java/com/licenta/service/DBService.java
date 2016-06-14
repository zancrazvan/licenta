package com.licenta.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.licenta.entity.User;
import com.licenta.repository.UserRepository;

@Service
@Transactional
public class DBService {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {

		User user = new User();
		user.setEmail("email");

		userRepository.save(user);

	}

}
