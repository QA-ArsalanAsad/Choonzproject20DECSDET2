package com.qa.choonz.rest.controller;

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

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@CrossOrigin
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/create")
	public ResponseEntity<UserDTO> create(@RequestBody User user) {
		return new ResponseEntity<UserDTO>(this.userService.create(user), HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<UserDTO>> readAll() {
		return new ResponseEntity<List<UserDTO>>(this.userService.read(), HttpStatus.OK);
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<UserDTO> readById(@PathVariable long id) {
		return new ResponseEntity<UserDTO>(this.userService.read(id), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO> update(@RequestBody User user, @PathVariable long id) {
		return new ResponseEntity<UserDTO>(this.userService.update(user, id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<UserDTO> delete(@PathVariable long id) {
		return this.userService.delete(id) ? new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<UserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
