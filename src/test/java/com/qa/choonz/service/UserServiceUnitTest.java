package com.qa.choonz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
		// GIVEN
		UserDTO testUserDto = mapToDTO(A_TEST_1);

		// AND
		when(repo.save(A_TEST_1)).thenReturn(A_TEST_1);
		when(mapper.map(A_TEST_1, UserDTO.class)).thenReturn(testUserDto);

		// WHEN
		UserDTO result = service.create(A_TEST_1);
		assertEquals(testUserDto, result);

		// THEN
		verify(this.repo, atLeastOnce()).save(A_TEST_1);
		verify(this.mapper, atLeastOnce()).map(A_TEST_1, UserDTO.class);

	}

	@Test
	void testReadAll() throws Exception {
		// GIVEN

		// AND
		when(repo.findAll()).thenReturn(listUser);

		// WHEN
		UserDTO result = mapToDTO2(service.read());
		assertEquals(mapToDTO3(listUser), result);

		// THEN
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void testReadByID() throws Exception {
		// GIVEN
		Optional<User> testUser = Optional.of(A_TEST_1);
		UserDTO testUserDto = mapToDTO(A_TEST_1);

		// AND
		when(repo.findById(1L)).thenReturn(testUser);
		when(mapper.map(A_TEST_1, UserDTO.class)).thenReturn(testUserDto);

		// WHEN
		UserDTO result = service.read(A_TEST_1.getId());
		assertEquals(testUserDto, result);
		assertEquals(true, testUser.isPresent());

		// THEN
		verify(this.repo, atLeastOnce()).findById(A_TEST_1.getId());
		verify(this.mapper, atLeastOnce()).map(A_TEST_1, UserDTO.class);

	}

	@Test
	void testUpdate() throws Exception {
		// GIVEN
		User testUser = new User(1L, "testUser1", "pass");
		UserDTO testUserDto = mapToDTO(A_TEST_1);
		Optional<User> testUserOp = Optional.of(A_TEST_1);

		// AND
		when(repo.findById(1L)).thenReturn(testUserOp);
		when(repo.save(testUser)).thenReturn(testUser);
		when(mapper.map(A_TEST_1, UserDTO.class)).thenReturn(testUserDto);

		// WHEN
		UserDTO result = service.update(testUser, 1L);
		assertEquals(testUserDto, result);
		assertEquals(true, testUserOp.isPresent());

		// THEN
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

}
