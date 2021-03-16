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

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;

@SpringBootTest
@ActiveProfiles("dev")
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

	private ArtistDTO mapToDTO2(List<ArtistDTO> list) {
		return this.mapper.map(list, ArtistDTO.class);
	}

	private ArtistDTO mapToDTO3(List<Artist> listArtist2) {
		return this.mapper.map(listArtist2, ArtistDTO.class);
	}

// I have labelled some variables here just in case we may be needing them later on
	private final Artist A_TEST_1 = new Artist(1L, "Giveon");
	private final Artist A_TEST_2 = new Artist(1L, "Drake");
//	private final Artist A_TEST_1N = new Artist("Nelly");
//	private final Artist A_TEST_1U = new Artist(1L, A_TEST_1N.getName());
	private final List<Artist> listArtist = List.of(A_TEST_1, A_TEST_2);

	@Test
	void testCreate() throws Exception {
		// GIVEN
		ArtistDTO testArtistDto = mapToDTO(A_TEST_1);

		// AND
		when(repo.save(A_TEST_1)).thenReturn(A_TEST_1);
		when(mapper.map(A_TEST_1, ArtistDTO.class)).thenReturn(testArtistDto);

		// WHEN
		ArtistDTO result = service.create(A_TEST_1);
		assertEquals(testArtistDto, result);

		// THEN
		verify(this.repo, atLeastOnce()).save(A_TEST_1);
		verify(this.mapper, atLeastOnce()).map(A_TEST_1, ArtistDTO.class);

	}

	@Test
	void testReadAll() throws Exception {
		// GIVEN

		// AND
		when(repo.findAll()).thenReturn(listArtist);

		// WHEN
		ArtistDTO result = mapToDTO2(service.read());
		assertEquals(mapToDTO3(listArtist), result);

		// THEN
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void testReadByID() throws Exception {
		// GIVEN
		Optional<Artist> testArtist = Optional.of(A_TEST_1);
		ArtistDTO testArtistDto = mapToDTO(A_TEST_1);

		// AND
		when(repo.findById(1L)).thenReturn(testArtist);
		when(mapper.map(A_TEST_1, ArtistDTO.class)).thenReturn(testArtistDto);

		// WHEN
		ArtistDTO result = service.read(A_TEST_1.getId());
		assertEquals(testArtistDto, result);
		assertEquals(true, testArtist.isPresent());

		// THEN
		verify(this.repo, atLeastOnce()).findById(A_TEST_1.getId());
		verify(this.mapper, atLeastOnce()).map(A_TEST_1, ArtistDTO.class);

	}

	@Test
	void testUpdate() throws Exception {
		// GIVEN
		Artist testArtist = new Artist(1L, "Giveon Updated");
		ArtistDTO testArtistDto = mapToDTO(A_TEST_1);
		Optional<Artist> testArtistOp = Optional.of(A_TEST_1);

		// AND
		when(repo.findById(1L)).thenReturn(testArtistOp);
		when(repo.save(testArtist)).thenReturn(testArtist);
		when(mapper.map(A_TEST_1, ArtistDTO.class)).thenReturn(testArtistDto);

		// WHEN
		ArtistDTO result = service.update(testArtist, 1L);
		assertEquals(testArtistDto, result);
		assertEquals(true, testArtistOp.isPresent());

		// THEN
		verify(this.repo, atLeastOnce()).save(testArtist);
		verify(this.mapper, atLeastOnce()).map(testArtist, ArtistDTO.class);

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