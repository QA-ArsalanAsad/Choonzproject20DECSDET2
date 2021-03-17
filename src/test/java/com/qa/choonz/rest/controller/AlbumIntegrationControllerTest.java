package com.qa.choonz.rest.controller;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
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
	private Artist artist = new Artist("Drake");
	private Genre genre = new Genre("TEST1", "txt");
	private Track track = new Track("Final fantasy", 3, "new txt");

	@Test
	void testCreate() throws Exception {
		AlbumDTO testAlbum = mapToDTO(new Album("Final", track, artist, genre, "txt2"));
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
