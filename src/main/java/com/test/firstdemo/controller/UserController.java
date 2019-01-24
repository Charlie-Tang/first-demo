package com.test.firstdemo.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.firstdemo.entity.User;
import com.test.firstdemo.repository.UserRepository;

@RestController
public class UserController {
	
	private final UserRepository userRepository;
	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@PostMapping("/person/save")
	public User save(@RequestParam String name)
	{
		User user = new User();
		user.setName(name);
		if (userRepository.save(user)) {
			System.out.println("用户对象保存成功");
		}
		
		return user;
	}
	
	@PostMapping("/person/findAll")
	public Collection<User> getAll()
	{
		Collection<User> users = userRepository.findAll();
		return users;
	}
	
}
