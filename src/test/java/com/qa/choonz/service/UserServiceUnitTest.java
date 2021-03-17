package com.qa.choonz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepo;
import com.qa.choonz.rest.dto.UserDTO;

@SpringBootTest
@ActiveProfiles("dev")
public class UserServiceUnitTest {

	@MockBean
	private UserRepo repo;
	@MockBean
	private ModelMapper mapper;

	@Autowired
	private UserService service;

	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}

	private UserDTO mapToDTO2(List<UserDTO> list) {
		return this.mapper.map(list, UserDTO.class);
	}

	private UserDTO mapToDTO3(List<User> listUser2) {
		return this.mapper.map(listUser2, UserDTO.class);
	}

	private final User A_TEST_1 = new User(1L, "testUser1", "pass");
	private final User A_TEST_2 = new User(2L, "testUser2", "pass");

	private final List<User> listUser = List.of(A_TEST_1, A_TEST_2);

	@Test
	void testCreate() throws Exception {
		UserDTO testUserDto = mapToDTO(A_TEST_1);

		when(repo.save(A_TEST_1)).thenReturn(A_TEST_1);
		when(mapper.map(A_TEST_1, UserDTO.class)).thenReturn(testUserDto);

		UserDTO result = service.create(A_TEST_1);
		assertEquals(testUserDto, result);

		verify(this.repo, atLeastOnce()).save(A_TEST_1);
		verify(this.mapper, atLeastOnce()).map(A_TEST_1, UserDTO.class);

	}

	@Test
	void testReadAll() throws Exception {
		when(repo.findAll()).thenReturn(listUser);

		UserDTO result = mapToDTO2(service.read());
		assertEquals(mapToDTO3(listUser), result);

		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void testReadByID() throws Exception {
		Optional<User> testUser = Optional.of(A_TEST_1);
		UserDTO testUserDto = mapToDTO(A_TEST_1);

		when(repo.findById(1L)).thenReturn(testUser);
		when(mapper.map(A_TEST_1, UserDTO.class)).thenReturn(testUserDto);

		UserDTO result = service.read(A_TEST_1.getId());
		assertEquals(testUserDto, result);
		assertEquals(true, testUser.isPresent());

		verify(this.repo, atLeastOnce()).findById(A_TEST_1.getId());
		verify(this.mapper, atLeastOnce()).map(A_TEST_1, UserDTO.class);

	}

	@Test
	void testUpdate() throws Exception {
		User testUser = new User(1L, "testUser1", "pass");
		UserDTO testUserDto = mapToDTO(A_TEST_1);
		Optional<User> testUserOp = Optional.of(A_TEST_1);

		when(repo.findById(1L)).thenReturn(testUserOp);
		when(repo.save(testUser)).thenReturn(testUser);
		when(mapper.map(A_TEST_1, UserDTO.class)).thenReturn(testUserDto);

		UserDTO result = service.update(testUser, 1L);
		assertEquals(testUserDto, result);
		assertEquals(true, testUserOp.isPresent());

		verify(this.repo, atLeastOnce()).save(testUser);
		verify(this.mapper, atLeastOnce()).map(testUser, UserDTO.class);

	}

	@Test
	void testDelete() throws Exception {
		Boolean result = service.delete(1L);
		assertEquals(true, result);

		verify(this.repo, atLeastOnce()).deleteById(A_TEST_1.getId());

	}

	@Test
	void testDeleteFalse() throws Exception {
		when(repo.existsById(999L)).thenReturn(true);

		Boolean result = service.delete(999L);
		assertEquals(false, result);

		verify(this.repo, atLeastOnce()).deleteById(999L);
		verify(this.repo, atLeastOnce()).existsById(999L);

	}

	@Test
	void testLogin() throws Exception {
		User testUser = new User(1L, "testUser1", "pass");

		when(repo.userLogin("testUser1")).thenReturn(testUser);

		HashMap<String, String> result = service.login(testUser);
		assertNotNull(result);

		verify(this.repo, atLeastOnce()).userLogin("testUser1");

	}

	@Test
	void testLoginFail() throws Exception {
		User testUser = new User(1L, "testUser1", "pass");
		User testUserFail = new User(2L, "testUser1", "passF");
		HashMap<String, String> returnHashMap = new HashMap<>();
		returnHashMap.put("successful", "false");
		returnHashMap.put("auth", "");

		when(repo.userLogin("testUser1")).thenReturn(testUserFail);

		HashMap<String, String> result = service.login(testUser);
		assertEquals(returnHashMap, result);

		verify(this.repo, atLeastOnce()).userLogin("testUser1");

	}

	@Test
	void testLogout() throws Exception {
		User testUser = new User(1L, "testUser1", "pass");
		testUser.setAuth("");
		when(repo.searchByAuth("token")).thenReturn(testUser);
		when(repo.userLogin("testUser1")).thenReturn(testUser);

		Boolean result = service.logout("token");
		assertEquals(true, result);

		verify(this.repo, atLeastOnce()).searchByAuth("token");
		verify(this.repo, atLeastOnce()).userLogin("testUser1");

	}

	@Test
	void testLogoutFail() throws Exception {
		User testUser = new User(1L, "testUser1", "pass");
		testUser.setAuth("token");
		when(repo.searchByAuth("token")).thenReturn(testUser);
		when(repo.userLogin("testUser1")).thenReturn(testUser);

		Boolean result = service.logout("token");

		assertEquals(false, result);

		verify(this.repo, atLeastOnce()).searchByAuth("token");
		verify(this.repo, atLeastOnce()).userLogin("testUser1");

	}

	@Test
	void testFindByAuth() throws Exception {
		User testUser = new User(1L, "testUser1", "pass");
		UserDTO testUserDto = mapToDTO(testUser);

		when(repo.searchByAuth("token")).thenReturn(testUser);

		UserDTO result = service.findByAuth("token");
		assertEquals(testUserDto, result);

		verify(this.repo, atLeastOnce()).searchByAuth("token");

	}

}
