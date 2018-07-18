package com.karsmaras.management.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.karsmaras.management.dto.UserDto;
import com.karsmaras.management.entity.User;
import com.karsmaras.management.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	@Qualifier("UserService")
	UserService userService;

	@PostMapping("/createUser")
	public ResponseEntity<User> addUser(@RequestBody(required = true) final UserDto dto) {

		try {
			User user = userService.createUser(dto);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUser(@PathParam(value = "id") final int id) {

		try {
			User user = userService.getUser(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
