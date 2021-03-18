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
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.rest.dto.AlbumDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = "classpath:test-drop-all.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AlbumIntegrationControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ModelMapper mapper;

	private AlbumDTO mapToDTO(Album album) {
		return this.mapper.map(album, AlbumDTO.class);
	}

	@Autowired
	private ObjectMapper jsonifier;

	private String URI = "/albums";
	private Album Album_TEST_1 = new Album(2L, "TEST2", "generic txt");
	private Artist artist;
	private Genre genre;

	@Test
	void testCreate() throws Exception {
		AlbumDTO testAlbum = mapToDTO(new Album(1L, "Scorpion", "txt"));
		testAlbum.setId(1L);
		String albumToJSON = this.jsonifier.writeValueAsString(testAlbum);
		RequestBuilder rB = post(URI + "/create/1/1").contentType(MediaType.APPLICATION_JSON).content(albumToJSON)
				.accept(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isCreated();
		ResultMatcher checkBody = content().json(albumToJSON);
		this.mvc.perform(rB).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testReadAll() throws Exception {
		RequestBuilder rB = get(URI + "/read", List.of(Album_TEST_1));
		ResultMatcher checkStatus = status().isOk();
		this.mvc.perform(rB).andExpect(checkStatus);

	}

	@Test
	void testReadByID() throws Exception {

		Album testAlbum = new Album(1L, "Scorpion", null, artist, genre, "none");
		testAlbum.setTracks(List.of());
		AlbumDTO mapAlbum = mapToDTO(testAlbum);
		mapAlbum.setId(1L);
		RequestBuilder rB = get(URI + "/read/1");
		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(mapAlbum));

		this.mvc.perform(rB).andExpect(checkStatus).andExpect(checkBody);

	}

	@Test
	void testUpdate() throws Exception {
		Album testAlbum = new Album(1L, "Scorpion updated", null, artist, genre, "none");
		testAlbum.setTracks(List.of());
		AlbumDTO mapAlbum = mapToDTO(testAlbum);
		mapAlbum.setId(1L);
		RequestBuilder rB = put(URI + "/update" + "/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testAlbum));
		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(mapAlbum));

		this.mvc.perform(rB).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testDelete() throws Exception {
		this.mvc.perform(delete(URI + "/delete/1")).andExpect(status().isNoContent());

	}

}
