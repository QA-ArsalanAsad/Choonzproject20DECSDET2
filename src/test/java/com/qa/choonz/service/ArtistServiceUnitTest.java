package com.qa.choonz.service;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;

@SpringBootTest
public class ArtistServiceUnitTest {

	@MockBean
	private ArtistRepository repo;
	@MockBean
	private ModelMapper mapper;

	@Autowired
	private ArtistService service;

	private ArtistDTO mapToDTO(Artist artist) {
		return this.mapper.map(artist, ArtistDTO.class);
	}
// I have labelled some variables here just in case we may be needing them later on
//	private final Artist A_TEST_1 = new Artist("Giveon");
//	private final Artist A_TEST_2 = new Artist("Drake");
//	private final Artist A_TEST_1N = new Artist("Nelly");
//	private final Artist A_TEST_1U = new Artist(1L, A_TEST_1N.getName());
//	private final List<Artist> listArtist = List.of(A_TEST_1, A_TEST_2);

	@Test
	void testCreate() throws Exception {
		// GIVEN
		// we create some variables either in the method or above as a variable
		// AND
		// repo.save our variable and then we return our new created variable
		// WHEN
		// assert that our expected value is equal to our result with a created Artist
		// THEN
		// verify that the testCreate method has ran at least once

	}

	@Test
	void testReadAll() throws Exception {
		// GIVEN
		// we create some variables either in this method or above as a private value
		// AND
		// repo.findAll of our variables and we return all our Artists
		// WHEN
		// assert that the expected value is equal to our actual result
		// THEN
		// verify that the readAll test has ran at least once
	}

	@Test
	void testReadByID() throws Exception {
		// GIVEN
		// we have created our variables similarly
		// AND
		// we have created a variable of Long id
		// AND
		// we input repo.findByID and we return an Artist with that specified id
		// WHEN
		// assert that the expected value is equal to our actual result
		// THEN
		// verify that the readByID test has ran at least once
	}

	@Test
	void testUpdate() throws Exception {
		// GIVEN
		// we can create a variable of Long with an id
		// AND
		// we input repo.findByID and return an optional.of one of our variables with
		// the same id
		// AND
		// we input repo.save and return the expected updated variable which has been
		// created
		// WHEN
		// assert that the expected value is equal to our actual result
		// THEN
		// verify that the update test has ran at least once
	}

	@Test
	void testDelete() throws Exception {
		// GIVEN
		// we can create a variable of Long with an ID
		// AND
		// we can input repo.existsByID and then return a value of fals (this needs to
		// be checked)
		// WHEN
		// assert that the expected value is equal to true when the id is deleted
		// THEN
		// verify that the delete test has ran at least once
	}

}
