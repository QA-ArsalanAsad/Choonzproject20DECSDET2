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

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;

@SpringBootTest
@ActiveProfiles("dev")
public class AlbumServiceUnitTest {

	@MockBean
	private AlbumRepository repo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private AlbumService service;

	private AlbumDTO mapToDTO(Album album) {
		return this.mapper.map(album, AlbumDTO.class);
	}

	private AlbumDTO mapToDTO2(List<AlbumDTO> list) {
		return this.mapper.map(list, AlbumDTO.class);
	}

	private AlbumDTO mapToDTO3(List<Album> listAlbum2) {
		return this.mapper.map(listAlbum2, AlbumDTO.class);
	}

	private List<Track> listTrack;
	private final Album A_TEST_1 = new Album(1L, "TestAlbum1", listTrack, new Artist(1L), new Genre(1L),
			"NOT IMPLEMENTED YET");
	private final Album A_TEST_2 = new Album(2L, "TestAlbum2", listTrack, new Artist(1L), new Genre(1L),
			"NOT IMPLEMENTED YET");

	private final List<Album> listAlbum = List.of(A_TEST_1, A_TEST_2);

	@Test
	void testCreate() throws Exception {
		// GIVEN
		AlbumDTO testAlbumDto = mapToDTO(A_TEST_1);

		// AND
		when(repo.save(A_TEST_1)).thenReturn(A_TEST_1);

		// WHEN
		AlbumDTO result = service.create(1L, 1L, testAlbumDto);
		assertEquals(testAlbumDto, result);

		// THEN
		verify(this.repo, atLeastOnce()).save(A_TEST_1);

	}

	@Test
	void testReadAll() throws Exception {
		// GIVEN

		// AND
		when(repo.findAll()).thenReturn(listAlbum);

		// WHEN
		AlbumDTO result = mapToDTO2(service.read());
		assertEquals(mapToDTO3(listAlbum), result);

		// THEN
		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void testReadByID() throws Exception {
		// GIVEN
		Optional<Album> testAlbum = Optional.of(A_TEST_1);
		AlbumDTO testAlbumDto = mapToDTO(A_TEST_1);

		// AND
		when(repo.findById(1L)).thenReturn(testAlbum);

		// WHEN
		AlbumDTO result = service.read(A_TEST_1.getId());
		assertEquals(testAlbumDto, result);
		assertEquals(true, testAlbum.isPresent());

		// THEN
		verify(this.repo, atLeastOnce()).findById(A_TEST_1.getId());

	}

	@Test
	void testUpdate() throws Exception {
		// GIVEN
		Album testAlbum = new Album(1L, "TestAlbum1 Updated", listTrack, new Artist(1L), new Genre(1L),
				"NOT IMPLEMENTED YET UPDATED");
		AlbumDTO testAlbumDto = mapToDTO(testAlbum);
		Optional<Album> testAlbumOp = Optional.of(testAlbum);

		// AND
		when(repo.findById(1L)).thenReturn(testAlbumOp);
		when(repo.save(testAlbum)).thenReturn(testAlbum);

		// WHEN
		AlbumDTO result = service.update(testAlbum, 1L);
		assertEquals(testAlbumDto, result);
		assertEquals(true, testAlbumOp.isPresent());

		// THEN
		verify(this.repo, atLeastOnce()).save(testAlbum);

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
