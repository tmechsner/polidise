package org.polidise.controller;

import org.polidise.domain.User;
import org.polidise.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class UserController {

	private UserRepository repo;

	@Autowired
	public UserController(UserRepository repo) {
		this.repo = repo;
	}

	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		model.put("user", repo.findAll().iterator().next());
		return "test";
	}

}
