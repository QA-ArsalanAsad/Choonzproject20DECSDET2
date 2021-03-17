package com.qa.choonz.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;

@SpringBootTest
@ActiveProfiles("dev")
public class AlbumControllerUnitMockTest {

	@Autowired
	private AlbumController controller;

	@MockBean
	private AlbumService service;

	@Autowired
	private ModelMapper mapper;

	private AlbumDTO mapToDTO(Album album) {
		return this.mapper.map(album, AlbumDTO.class);
	}

	private List<Track> listTrack;
	private final Album testAlbum = new Album(1L, "TestAlbum1", listTrack, new Artist(1L), new Genre(1L),
			"NOT IMPLEMENTED YET");
	private final List<Album> listOfAlbum = List.of(testAlbum);

	@Test
	public void testCreate() throws Exception {
		AlbumDTO testAlbumDto = mapToDTO(testAlbum);

		when(this.service.create(1L, 1L, testAlbumDto)).thenReturn(this.mapToDTO(testAlbum));

		assertEquals(new ResponseEntity<AlbumDTO>(this.mapToDTO(testAlbum), HttpStatus.CREATED),
				(this.controller.create(testAlbumDto, 1L, 1L)));

		verify(this.service, atLeastOnce()).create(1L, 1L, testAlbumDto);
	}

	@Test
	public void testReadAll() throws Exception {
		when(this.service.read()).thenReturn(listOfAlbum.stream().map(this::mapToDTO).collect(Collectors.toList()));

		ResponseEntity<List<AlbumDTO>> expected = new ResponseEntity<List<AlbumDTO>>(
				listOfAlbum.stream().map(this::mapToDTO).collect(Collectors.toList()), HttpStatus.OK);

		assertEquals((expected), (this.controller.read()));

		verify(this.service, atLeastOnce()).read();
	}

	@Test
	public void testReadByID() throws Exception {
		when(this.service.read(1L)).thenReturn(this.mapToDTO(testAlbum));

		assertEquals(new ResponseEntity<AlbumDTO>(this.mapToDTO(testAlbum), HttpStatus.OK), (this.controller.read(1L)));

		verify(this.service, atLeastOnce()).read(1L);
	}

	@Test
	public void testUpdate() throws Exception {
		when(this.service.update(testAlbum, 1L)).thenReturn(this.mapToDTO(testAlbum));

		assertEquals(new ResponseEntity<AlbumDTO>(this.mapToDTO(testAlbum), HttpStatus.ACCEPTED),
				(this.controller.update(testAlbum, 1L)));

		verify(this.service, atLeastOnce()).update(testAlbum, 1L);
	}

	@Test
	public void testDelete() throws Exception {
		when(this.service.delete(1L)).thenReturn(true);

		assertEquals(new ResponseEntity<>(null, HttpStatus.NO_CONTENT), (this.controller.delete(1L)));
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), (this.controller.delete(99L)));

		verify(this.service, atLeastOnce()).delete(1L);
	}

}
