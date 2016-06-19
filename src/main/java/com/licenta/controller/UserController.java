package com.licenta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.licenta.entity.User;
import com.licenta.enumeration.Role;
import com.licenta.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/register")
	public String getregister(Model model) {
		model.addAttribute("newUser", new User());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String getregister(@ModelAttribute("newUser") User user) {
		user.setRole(Role.USER);
		userRepository.save(user);
		return "redirect:/login";
	}
}
