package com.karsmaras.management.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karsmaras.management.dto.ChangePassDto;
import com.karsmaras.management.dto.UserDto;
import com.karsmaras.management.entity.User;
import com.karsmaras.management.services.UserService;

/**
 * 
 * @author Maxi
 *
 */
@RestController
public class UserController {

	@Autowired
	@Qualifier("UserService")
	UserService userService;

	private static final Log LOGGER = LogFactory.getLog(UserController.class);

	/**
	 * 
	 * @param dto
	 * @return
	 */
	@PostMapping("/createUser")
	public ResponseEntity<User> addUser(@RequestBody(required = true) final UserDto dto) {

		try {
			User user = userService.createUser(dto);
			return new ResponseEntity<>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getUser(@PathVariable(value = "id") final Integer id) {

		try {
			User user = userService.getUser(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@PutMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody(required=true) final User user) {

		try {
			User result = userService.updateUser(user);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Boolean> deleteUser(@PathVariable(value = "id") final int id) {

		try {
			Boolean result = userService.deleteUser(id);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/validateUser")
	public ResponseEntity<Boolean> validateUser(@RequestBody(required = true) final UserDto dto) {
		try {
			Boolean result = userService.validateUser(dto);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getListUsers() {
		try {
			List<User> result = userService.getUsers();
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @return
	 */
	@GetMapping("/getFriends/{id}")
	public ResponseEntity<List<User>> getListFriends(@PathVariable(value = "id") final int id) {
		try {
			List<User> result = userService.getFriends(id);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @return
	 */
	@PostMapping("/addFriend/{id}")
	public ResponseEntity<Boolean> addFriend(@PathVariable(value = "id") final int id,
			@RequestBody(required = true) User user) {
		try {
			Boolean result = userService.addFriend(id, user);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @return
	 */
	@DeleteMapping("/removeFriend/{id}")
	public ResponseEntity<Boolean> removeFriend(@PathVariable(value = "id") final int id,
			@RequestBody(required = true) User user) {
		try {
			Boolean result = userService.removeFriend(id, user);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 
	 * @return
	 */
	@PutMapping("/updatePass/{id}")
	public ResponseEntity<Boolean> updatePassword(@PathVariable(value = "id") final int id,
			@RequestBody(required = true) ChangePassDto data) {
		try {
			Boolean result = userService.updatePassword(id, data.getOldPass(), data.getNewPass());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
