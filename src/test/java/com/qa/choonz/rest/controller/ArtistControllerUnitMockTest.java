package com.qa.choonz.rest.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.service.ArtistService;

@SpringBootTest
public class ArtistControllerUnitMockTest {

	@Autowired
	private ArtistController controller;

	@MockBean
	private ArtistService service;

	@Autowired
	private ModelMapper mapper;

	private ArtistDTO mapToDTO(Artist artist) {
		return this.mapper.map(artist, ArtistDTO.class);
	}

	private final Artist testArtist = new Artist(2L, "drake");
	private final List<Artist> listOfArtist = List.of(testArtist);

	public void testCreate() throws Exception {
		// GIVEN
		// create a mapToDTO for a service.create
		// WHEN
		// assert that the expected is equal to the result
		// THEN
		// verify the amount of times that the test has run
	}

	public void testReadAll() throws Exception {
		// GIVEN
		// another mapToDTO for the service.read
		// WHEN
		// assert that the expected is equal to the result
		// tHEN
		// verify that the readAll test has ran at least once
	}

	public void testReadByID() throws Exception {
		// GIVEN
		// again mapToDTO our variable for service.readsByID
		// WHEN
		// assert that the expected is equal to the result
		// THEN
		// verify that the readByID test has ran at least once
	}

	public void testUpdate() throws Exception {
		// GIVEN
		// mapToDTO for our variable for service.update
		// WHEN
		// assert that the expected is equal to the result
		// THEN
		// verify the update test has ran at least once
	}

	public void testDelete() throws Exception {
		// GIVEN
		// that I can use an ID e.g 1L and you can use service.delete
		// WHEN
		// assert that the expected value is deleted and gets the expected result
		// THEN
		// verify that the delete test has ran at least once
	}

}
