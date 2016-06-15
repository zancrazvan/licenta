package com.licenta.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.licenta.entity.User;
import com.licenta.enumeration.Role;
import com.licenta.repository.UserRepository;

@Service
@Transactional
public class DBService {

	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {

		User user = new User();
		user.setEmail("razvan@licenta.com");
		user.setPassword("licenta");
		user.setFirstName("Razvan");
		user.setLastName("Zanc");
		user.setRole(Role.USER);

		userRepository.save(user);

	}

}
