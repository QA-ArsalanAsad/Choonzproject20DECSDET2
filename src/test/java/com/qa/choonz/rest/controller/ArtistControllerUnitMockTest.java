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

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.service.ArtistService;

@SpringBootTest
@ActiveProfiles("dev")
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

	private final Artist testArtist = new Artist(1L, "drake");
	private final List<Artist> listOfArtist = List.of(testArtist);

	@Test
	public void testCreate() throws Exception {
		when(this.service.create(testArtist)).thenReturn(this.mapToDTO(testArtist));

		assertEquals(new ResponseEntity<ArtistDTO>(this.mapToDTO(testArtist), HttpStatus.CREATED),
				(this.controller.create(testArtist)));

		verify(this.service, atLeastOnce()).create(testArtist);
	}

	@Test
	public void testReadAll() throws Exception {
		when(this.service.read()).thenReturn(listOfArtist.stream().map(this::mapToDTO).collect(Collectors.toList()));

		ResponseEntity<List<ArtistDTO>> expected = new ResponseEntity<List<ArtistDTO>>(
				listOfArtist.stream().map(this::mapToDTO).collect(Collectors.toList()), HttpStatus.OK);

		assertEquals((expected), (this.controller.read()));

		verify(this.service, atLeastOnce()).read();
	}

	@Test
	public void testReadByID() throws Exception {
		when(this.service.read(1L)).thenReturn(this.mapToDTO(testArtist));

		assertEquals(new ResponseEntity<ArtistDTO>(this.mapToDTO(testArtist), HttpStatus.OK),
				(this.controller.read(1L)));

		verify(this.service, atLeastOnce()).read(1L);
	}

	@Test
	public void testUpdate() throws Exception {
		when(this.service.update(testArtist, 1L)).thenReturn(this.mapToDTO(testArtist));

		assertEquals(new ResponseEntity<ArtistDTO>(this.mapToDTO(testArtist), HttpStatus.ACCEPTED),
				(this.controller.update(testArtist, 1L)));

		verify(this.service, atLeastOnce()).update(testArtist, 1L);
	}

	@Test
	public void testDelete() throws Exception {
		when(this.service.delete(1L)).thenReturn(true);

		assertEquals(new ResponseEntity<>(null, HttpStatus.NO_CONTENT), (this.controller.delete(1L)));
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), (this.controller.delete(99L)));

		verify(this.service, atLeastOnce()).delete(1L);
	}

}
