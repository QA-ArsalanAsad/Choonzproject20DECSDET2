package com.qa.choonz.rest.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.qa.choonz.rest.dto.PlaylistDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = "classpath:test-drop-all.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PlaylistIntegrationController {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ModelMapper mapper;

	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.mapper.map(playlist, PlaylistDTO.class);
	}

	@Autowired
	private ObjectMapper jsonifier;

	private final String URI = "/playlists";
	private final User testUser = new User(1L);

	@Test
	void testCreate() throws Exception {
		PlaylistDTO testPlaylist = mapToDTO(new Playlist("vibes", "for vibing", "unknown"));
		testPlaylist.setId(2L);
		String playlistToJSON = this.jsonifier.writeValueAsString(testPlaylist);
		RequestBuilder rB = post(URI + "/create/" + 1).contentType(MediaType.APPLICATION_JSON).content(playlistToJSON)
				.accept(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(playlistToJSON);
		this.mvc.perform(rB).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testReadAll() throws Exception {

	}

	@Test
	void testReadByID() throws Exception {

	}

	@Test
	void testUpdate() throws Exception {

	}

	@Test
	void testDelete() throws Exception {

	}

}
