package com.qa.choonz.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;

@SpringBootTest
@ActiveProfiles("dev")
public class UserControllerUnitMockTest {

	@Autowired
	private UserController controller;

	@MockBean
	private UserService service;

	@Autowired
	private ModelMapper mapper;

	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}

	private final User testUser = new User(1L, "testUser1", "pass");
	private final User testUser2 = new User(2L, "testUser2", "pass");

	private final List<User> listOfUser = List.of(testUser, testUser2);

	@Test
	public void testCreate() throws Exception {
		when(this.service.create(testUser)).thenReturn(this.mapToDTO(testUser));

		assertEquals(new ResponseEntity<UserDTO>(this.mapToDTO(testUser), HttpStatus.CREATED),
				(this.controller.create(testUser)));

		verify(this.service, atLeastOnce()).create(testUser);
	}

	@Test
	public void testReadAll() throws Exception {
		when(this.service.read()).thenReturn(listOfUser.stream().map(this::mapToDTO).collect(Collectors.toList()));

		ResponseEntity<List<UserDTO>> expected = new ResponseEntity<List<UserDTO>>(
				listOfUser.stream().map(this::mapToDTO).collect(Collectors.toList()), HttpStatus.OK);

		assertEquals((expected), (this.controller.readAll()));

		verify(this.service, atLeastOnce()).read();
	}

	@Test
	public void testReadByID() throws Exception {
		when(this.service.read(1L)).thenReturn(this.mapToDTO(testUser));

		assertEquals(new ResponseEntity<UserDTO>(this.mapToDTO(testUser), HttpStatus.OK),
				(this.controller.readById(1L)));

		verify(this.service, atLeastOnce()).read(1L);
	}

	@Test
	public void testUpdate() throws Exception {
		when(this.service.update(testUser, 1L)).thenReturn(this.mapToDTO(testUser));

		assertEquals(new ResponseEntity<UserDTO>(this.mapToDTO(testUser), HttpStatus.ACCEPTED),
				(this.controller.update(testUser, 1L)));

		verify(this.service, atLeastOnce()).update(testUser, 1L);
	}

	@Test
	public void testDelete() throws Exception {
		when(this.service.delete(1L)).thenReturn(true);

		assertEquals(new ResponseEntity<>(null, HttpStatus.NO_CONTENT), (this.controller.delete(1L)));
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), (this.controller.delete(99L)));

		verify(this.service, atLeastOnce()).delete(1L);
	}

	@Test
	void testLogin() throws Exception {
		HashMap<String, String> returnHashMap = new HashMap<>();
		returnHashMap.put("successful", "true");
		returnHashMap.put("auth", "");

		when(this.service.login(testUser)).thenReturn(returnHashMap);

		assertEquals(new ResponseEntity<String>(returnHashMap.get("auth"), HttpStatus.OK),
				(this.controller.login(testUser)));

		verify(this.service, atLeastOnce()).login(testUser);
	}

	@Test
	void testLoginFail() throws Exception {
		HashMap<String, String> returnHashMap = new HashMap<>();
		returnHashMap.put("successful", "false");
		returnHashMap.put("auth", "");

		when(this.service.login(testUser)).thenReturn(returnHashMap);

		assertEquals(new ResponseEntity<String>(returnHashMap.get(""), HttpStatus.NO_CONTENT),
				(this.controller.login(testUser)));

		verify(this.service, atLeastOnce()).login(testUser);
	}

	@Test
	void testLogout() throws Exception {
		HashMap<String, String> returnHashMap = new HashMap<>();
		returnHashMap.put("auth", "");

		when(this.service.logout("")).thenReturn(true);

		assertEquals(new ResponseEntity<String>("true", HttpStatus.OK), (this.controller.logout("")));

		verify(this.service, atLeastOnce()).logout("");
	}

	@Test
	void testLogoutFail() throws Exception {
		HashMap<String, String> returnHashMap = new HashMap<>();
		returnHashMap.put("auth", "");

		when(this.service.logout("")).thenReturn(false);

		assertEquals(new ResponseEntity<String>("false", HttpStatus.NOT_FOUND), (this.controller.logout("")));

		verify(this.service, atLeastOnce()).logout("");
	}

	@Test
	void testFindByAuth() throws Exception {
		when(this.service.findByAuth("")).thenReturn(this.mapToDTO(testUser));

		assertEquals(new ResponseEntity<UserDTO>(this.mapToDTO(testUser), HttpStatus.ACCEPTED),
				(this.controller.readByAuth("")));

		verify(this.service, atLeastOnce()).findByAuth("");
	}

}
