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

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;

@SpringBootTest
@ActiveProfiles("dev")
public class PlaylistControllerUnitMockTest {

	@Autowired
	private PlaylistController controller;

	@MockBean
	private PlaylistService service;

	@Autowired
	private ModelMapper mapper;

	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.mapper.map(playlist, PlaylistDTO.class);
	}

	private final Playlist testPlaylist = new Playlist(1L, "TestPlaylist1", "description", "artwork");
	private final Playlist testPlaylist2 = new Playlist(2L, "TestPlaylist2", "description", "artwork");
	private final List<Playlist> listOfPlaylist = List.of(testPlaylist, testPlaylist2);

	@Test
	public void testCreate() throws Exception {
		PlaylistDTO testPlaylistDto = mapToDTO(testPlaylist);

		when(this.service.create(testPlaylistDto, 1L)).thenReturn(this.mapToDTO(testPlaylist));

		assertEquals(new ResponseEntity<PlaylistDTO>(this.mapToDTO(testPlaylist), HttpStatus.CREATED),
				(this.controller.create(testPlaylistDto, 1L)));

		verify(this.service, atLeastOnce()).create(testPlaylistDto, 1L);
	}

	@Test
	public void testReadAll() throws Exception {
		when(this.service.read()).thenReturn(listOfPlaylist.stream().map(this::mapToDTO).collect(Collectors.toList()));

		ResponseEntity<List<PlaylistDTO>> expected = new ResponseEntity<List<PlaylistDTO>>(
				listOfPlaylist.stream().map(this::mapToDTO).collect(Collectors.toList()), HttpStatus.OK);

		assertEquals((expected), (this.controller.read()));

		verify(this.service, atLeastOnce()).read();
	}

	@Test
	public void testReadByID() throws Exception {
		when(this.service.read(1L)).thenReturn(this.mapToDTO(testPlaylist));

		assertEquals(new ResponseEntity<PlaylistDTO>(this.mapToDTO(testPlaylist), HttpStatus.OK),
				(this.controller.read(1L)));

		verify(this.service, atLeastOnce()).read(1L);
	}

	@Test
	public void testUpdate() throws Exception {
		PlaylistDTO testPlaylistDto = mapToDTO(testPlaylist);

		when(this.service.update(testPlaylistDto, 1L)).thenReturn(this.mapToDTO(testPlaylist));

		assertEquals(new ResponseEntity<PlaylistDTO>(this.mapToDTO(testPlaylist), HttpStatus.ACCEPTED),
				(this.controller.update(testPlaylistDto, 1L)));

		verify(this.service, atLeastOnce()).update(testPlaylistDto, 1L);
	}

	@Test
	public void testDelete() throws Exception {
		when(this.service.delete(1L)).thenReturn(true);

		assertEquals(new ResponseEntity<>(null, HttpStatus.NO_CONTENT), (this.controller.delete(1L)));
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), (this.controller.delete(99L)));

		verify(this.service, atLeastOnce()).delete(1L);
	}

	@Test
	void testAddTrack() throws Exception {
		PlaylistDTO testPlaylistDto = mapToDTO(testPlaylist);

		when(this.service.addTrack(testPlaylistDto, 1L)).thenReturn(this.mapToDTO(testPlaylist));

		assertEquals(new ResponseEntity<PlaylistDTO>(this.mapToDTO(testPlaylist), HttpStatus.ACCEPTED),
				(this.controller.addTrack(testPlaylistDto, 1L)));

		verify(this.service, atLeastOnce()).addTrack(testPlaylistDto, 1L);
	}

	@Test
	void testRemoveTrack() throws Exception {
		PlaylistDTO testPlaylistDto = mapToDTO(testPlaylist);

		when(this.service.removeTrack(testPlaylistDto, 1L)).thenReturn(this.mapToDTO(testPlaylist));

		assertEquals(new ResponseEntity<PlaylistDTO>(this.mapToDTO(testPlaylist), HttpStatus.ACCEPTED),
				(this.controller.removeTrack(testPlaylistDto, 1L)));

		verify(this.service, atLeastOnce()).removeTrack(testPlaylistDto, 1L);
	}

}
