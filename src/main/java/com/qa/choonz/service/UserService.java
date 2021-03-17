package com.qa.choonz.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.qa.choonz.utils.Token;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepo;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.utils.BeanUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

	private final UserRepo repo;
	private final ModelMapper mapper;

	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}

	public UserDTO create(User user) {
		User created = this.repo.save(user);
		return this.mapToDTO(created);
	}

	public List<UserDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public UserDTO read(long id) {
		User found = this.repo.findById(id).orElseThrow();
		return this.mapToDTO(found);
	}

	public UserDTO update(User user, long id) {

		User toUpdate = this.repo.findById(id).orElseThrow();
		toUpdate.setUserName(toUpdate.getUserName());
		BeanUtils.mergeNotNull(user, toUpdate);
		User updated = this.repo.save(toUpdate);
		return this.mapToDTO(updated);

	}

	// Returns true if the ID is NOT found
	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public HashMap<String, String> login(User user) {
		User actual = this.repo.userLogin(user.getUserName());
		Boolean isSuccessful = actual.getUserName().equals(user.getUserName()) && actual.getPassword().equals(user.getPassword());
		String authToken = "";
		if (isSuccessful) {
			authToken = Token.generateToken(user);
			this.repo.insertAuth(authToken, user.getUserName());
		}
		HashMap<String, String> returnHashMap = new HashMap<>();
		returnHashMap.put("successful", isSuccessful.toString());
		returnHashMap.put("auth", authToken);
		return returnHashMap;
	}

	// This function takes in an auth token, finds that user associated with
	// that token, then wipes that token from the user (to signify logging out)
	// it then finds that user by username (using the login function) and checks
	// that the token is empty. If it is empty then the function returns true,
	// if it finds a token it will return false
	public Boolean logout(String authToken) {
		String userName = this.repo.searchByAuth(authToken).getUserName();
		this.repo.removeAuth(authToken);
		User actual = this.repo.userLogin(userName);
		 if (actual.getAuth().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	public UserDTO findByAuth(String authToken) {
		User user = this.repo.searchByAuth(authToken);
		return this.mapToDTO(user);
	}

}
