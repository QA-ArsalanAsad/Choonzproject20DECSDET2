package com.qa.choonz.rest.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<UserDTO> create(@RequestBody User user) {
		return new ResponseEntity<>(this.userService.create(user), HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<UserDTO>> readAll() {
		return ResponseEntity.ok(this.userService.read());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<UserDTO> readById(@PathVariable long id) {
		return ResponseEntity.ok(this.userService.read(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody User user, @PathVariable long id) {
		return new ResponseEntity<>(this.userService.update(user, id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable long id) {
		return this.userService.delete(id) ? new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user) {
		HashMap<String, String> loginDetails = this.userService.login(user);
		if (loginDetails.get("successful").equals("true")) {
			return new ResponseEntity<>(loginDetails.get("auth"), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	// This endpoint takes in a auth-token, and attempts to log it out
	// if the logout succeeds then the auth-token is removed from the db
	// and ("true", OK) are returned, on fail ("false", NOT_FOUND) are
	// returned
	@PostMapping("/logout/{authToken}")
	public ResponseEntity<String> logout(@PathVariable String authToken) {
		Boolean isSuccessful = this.userService.logout(authToken);
		if (isSuccessful) {
			return new ResponseEntity<>(isSuccessful.toString(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(isSuccessful.toString(), HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/read/auth/{authToken}")
	public ResponseEntity<UserDTO> readByAuth(@PathVariable String authToken) {
		UserDTO userDTO = this.userService.findByAuth(authToken);
		return new ResponseEntity<>(userDTO, HttpStatus.ACCEPTED);
	}
}
