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

import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.TrackService;

@SpringBootTest
@ActiveProfiles("dev")
public class TrackControllerUnitMockTest {

	@Autowired
	private TrackController controller;

	@MockBean
	private TrackService service;

	@Autowired
	private ModelMapper mapper;

	private TrackDTO mapToDTO(Track track) {
		return this.mapper.map(track, TrackDTO.class);
	}

	private final Track testTrack = new Track(1L, "TestTrack1", 111, "Lyrics");
	private final Track testTrack2 = new Track(2L, "TestTrack2", 222, "Lyrics");
	private final List<Track> listOfTrack = List.of(testTrack, testTrack2);

	@Test
	public void testCreate() throws Exception {
		TrackDTO testTrackDto = mapToDTO(testTrack);

		when(this.service.create(testTrackDto, 1L)).thenReturn(this.mapToDTO(testTrack));

		assertEquals(new ResponseEntity<TrackDTO>(this.mapToDTO(testTrack), HttpStatus.CREATED),
				(this.controller.create(testTrackDto, 1L)));

		verify(this.service, atLeastOnce()).create(testTrackDto, 1L);
	}

	@Test
	public void testReadAll() throws Exception {
		when(this.service.read()).thenReturn(listOfTrack.stream().map(this::mapToDTO).collect(Collectors.toList()));

		ResponseEntity<List<TrackDTO>> expected = new ResponseEntity<List<TrackDTO>>(
				listOfTrack.stream().map(this::mapToDTO).collect(Collectors.toList()), HttpStatus.OK);

		assertEquals((expected), (this.controller.read()));

		verify(this.service, atLeastOnce()).read();
	}

	@Test
	public void testReadByID() throws Exception {
		when(this.service.read(1L)).thenReturn(this.mapToDTO(testTrack));

		assertEquals(new ResponseEntity<TrackDTO>(this.mapToDTO(testTrack), HttpStatus.OK), (this.controller.read(1L)));

		verify(this.service, atLeastOnce()).read(1L);
	}

	@Test
	public void testUpdate() throws Exception {
		TrackDTO testTrackDto = mapToDTO(testTrack);

		when(this.service.update(testTrackDto, 1L)).thenReturn(this.mapToDTO(testTrack));

		assertEquals(new ResponseEntity<TrackDTO>(this.mapToDTO(testTrack), HttpStatus.ACCEPTED),
				(this.controller.update(testTrackDto, 1L)));

		verify(this.service, atLeastOnce()).update(testTrackDto, 1L);
	}

	@Test
	public void testDelete() throws Exception {
		when(this.service.delete(1L)).thenReturn(true);

		assertEquals(new ResponseEntity<>(null, HttpStatus.NO_CONTENT), (this.controller.delete(1L)));
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), (this.controller.delete(99L)));

		verify(this.service, atLeastOnce()).delete(1L);
	}

}
