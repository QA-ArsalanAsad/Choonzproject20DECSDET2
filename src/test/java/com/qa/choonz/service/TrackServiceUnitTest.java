package com.qa.choonz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;

@SpringBootTest
@ActiveProfiles("dev")
public class TrackServiceUnitTest {
	@MockBean
	private TrackRepository repo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TrackService service;

	private TrackDTO mapToDTO(Track track) {
		return this.mapper.map(track, TrackDTO.class);
	}

	private TrackDTO mapToDTO2(List<TrackDTO> list) {
		return this.mapper.map(list, TrackDTO.class);
	}

	private TrackDTO mapToDTO3(List<Track> listTrack2) {
		return this.mapper.map(listTrack2, TrackDTO.class);
	}

	private final Track A_TEST_1 = new Track(1L, "TestTrack1", 111, "Lyrics");
	private final Track A_TEST_2 = new Track(2L, "TestTrack2", 222, "Lyrics");
	private final List<Track> listTrack = List.of(A_TEST_1, A_TEST_2);

	@BeforeEach
	void beforeEach() {

		A_TEST_1.setAlbum(new Album(1L));
		A_TEST_2.setAlbum(new Album(1L));

	}

	@Test
	void testCreate() throws Exception {
		TrackDTO testTrackDto = mapToDTO(A_TEST_1);

		when(repo.save(A_TEST_1)).thenReturn(A_TEST_1);

		TrackDTO result = service.create(testTrackDto, 1L);
		assertEquals(testTrackDto, result);

		verify(this.repo, atLeastOnce()).save(A_TEST_1);

	}

	@Test
	void testReadAll() throws Exception {
		when(repo.findAll()).thenReturn(listTrack);

		TrackDTO result = mapToDTO2(service.read());
		assertEquals(mapToDTO3(listTrack), result);

		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void testReadByID() throws Exception {
		Optional<Track> testTrack = Optional.of(A_TEST_1);
		TrackDTO testTrackDto = mapToDTO(A_TEST_1);

		when(repo.findById(1L)).thenReturn(testTrack);

		TrackDTO result = service.read(A_TEST_1.getId());
		assertEquals(testTrackDto, result);
		assertEquals(true, testTrack.isPresent());

		verify(this.repo, atLeastOnce()).findById(A_TEST_1.getId());

	}

	@Test
	void testUpdate() throws Exception {
		Track testTrack = new Track(1L, "TestTrack1 Updated", 111, "Lyrics Updated");
		TrackDTO testTrackDto = mapToDTO(testTrack);
		Optional<Track> testTrackOp = Optional.of(testTrack);

		when(repo.findById(1L)).thenReturn(testTrackOp);
		when(repo.save(testTrack)).thenReturn(testTrack);

		TrackDTO result = service.update(testTrackDto, 1L);
		assertEquals(testTrackDto, result);
		assertEquals(true, testTrackOp.isPresent());

		verify(this.repo, atLeastOnce()).save(testTrack);

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
