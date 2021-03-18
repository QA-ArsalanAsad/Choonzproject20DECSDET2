package com.qa.choonz.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.UserDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = "classpath:test-drop-all.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class UserIntegrationControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ModelMapper mapper;

	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}

	@Autowired
	private ObjectMapper jsonifier;

	private final String URI = "/user";

	private final User A_TEST_1 = new User(2L, "Nick", "password");

	private final List<User> listOfUser = List.of(A_TEST_1);

	@Test
	void testCreate() throws Exception {
		User testDomain = new User("Nick", "password");
		testDomain.setAuth("none");
		UserDTO testDto = mapToDTO(testDomain);
		testDto.setId(1L);
		RequestBuilder request = post(URI + "/create").contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testDto));

		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(testDto));

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testReadAll() throws Exception {
		RequestBuilder request = get(URI + "/read", List.of(A_TEST_1));

		ResultMatcher checkStatus = status().isOk();

		this.mvc.perform(request).andExpect(checkStatus);
	}

	@Test
	void testReadByID() throws Exception {
		User testDomain = new User("Nick", "password");
		Playlist pList = new Playlist(1L, "sad", "when youre sad", "unknown");
		testDomain.setAuth("none");
		pList.setTracks(List.of());
		testDomain.setPlaylists(List.of(pList));
		UserDTO testDto = mapToDTO(testDomain);
		testDto.setId(1L);
		RequestBuilder request = get(URI + "/read" + "/1");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(testDto));

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdate() throws Exception {
		User testDomain = new User("Nick", "password");
		Playlist pList = new Playlist(1L, "sad", "when youre sad", "unknown");
		testDomain.setAuth("none");
		pList.setTracks(List.of());
		testDomain.setPlaylists(List.of(pList));
		UserDTO testDto = mapToDTO(testDomain);
		testDto.setId(1L);
		RequestBuilder request = put(URI + "/update" + "/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testDomain));

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(testDto));

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
		RequestBuilder request = delete(URI + "/delete" + "/1");

		ResultMatcher checkStatus = status().isNoContent();

		this.mvc.perform(request).andExpect(checkStatus);
	}

	@Test
	void testLogin() throws Exception {
		User testDomain = new User(1L, "Nick", "password");
		RequestBuilder request = post(URI + "/login").contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testDomain));

		ResultMatcher checkStatus = status().isOk();

		this.mvc.perform(request).andExpect(checkStatus);
	}

	@Test
	void testLogout() throws Exception {
		User testDomain = new User(1L, "Nick", "password");
		testDomain.setAuth("none");
		RequestBuilder request = post(URI + "/logout" + "/none");

		ResultMatcher checkStatus = status().isNotFound();

		this.mvc.perform(request).andExpect(checkStatus);
	}

	@Test
	void testFindByAuth() throws Exception {
		RequestBuilder request = get(URI + "/read/auth" + "/none");

		ResultMatcher checkStatus = status().isAccepted();

		this.mvc.perform(request).andExpect(checkStatus);
	}

}
