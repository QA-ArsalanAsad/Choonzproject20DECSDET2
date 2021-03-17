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
import com.qa.choonz.persistence.domain.Playlist_Track;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.PlaylistDTO;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
@Sql(scripts = "classpath:test-drop-all.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = { "classpath:test-schema.sql",
		"classpath:test-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PlaylistIntegrationControllerTest {

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

	private final Playlist A_TEST_1 = new Playlist(1L, "TestPlaylist1", "description", "artwork");

	@Test
	void testCreate() throws Exception {
		Playlist testDomain = new Playlist(1L, "TestPlaylist1", "description", "artwork");
		PlaylistDTO testDto = mapToDTO(testDomain);
		testDto.setId(1L);
		RequestBuilder request = post(URI + "/create/1").contentType(MediaType.APPLICATION_JSON)
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
		Playlist testDomain = new Playlist(1L, "sad", "when youre sad", "unknown");
		testDomain.setTracks(List.of());
		PlaylistDTO testDto = mapToDTO(testDomain);
		testDto.setId(1L);
		RequestBuilder request = get(URI + "/read" + "/1");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(testDto));

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testUpdate() throws Exception {
		Playlist testDomain = new Playlist(1L, "sad Updated", "when youre sad", "unknown");
		testDomain.setTracks(List.of());
		PlaylistDTO testDto = mapToDTO(testDomain);
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
	void testAddTrack() throws Exception {
		Playlist testDomain = new Playlist(1L, "sad", "when youre sad", "unknown");
		Playlist_Track pTrack = new Playlist_Track();
		pTrack.setId(2L);
		Track track = new Track("marvins room", 3, "lyrics");
		track.setId(1L);
		pTrack.setTrack(track);
		testDomain.setTracks(List.of(pTrack));
		PlaylistDTO testDto = mapToDTO(testDomain);
		testDto.setId(1L);
		RequestBuilder request = post(URI + "/addtrack" + "/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testDomain));

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(testDto));

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testRemoveTrack() throws Exception {
		Playlist testDomain = new Playlist(1L, "sad", "when youre sad", "unknown");
		testDomain.setTracks(List.of());
		PlaylistDTO testDto = mapToDTO(testDomain);
		testDto.setId(1L);
		RequestBuilder request = delete(URI + "/removetrack" + "/1").contentType(MediaType.APPLICATION_JSON)
				.content(this.jsonifier.writeValueAsString(testDomain));

		ResultMatcher checkStatus = status().isAccepted();
		ResultMatcher checkBody = content().json(this.jsonifier.writeValueAsString(testDto));

		this.mvc.perform(request).andExpect(checkStatus).andExpect(checkBody);
	}

}
