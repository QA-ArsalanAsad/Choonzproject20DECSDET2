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

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Playlist_Track;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.Playlist_TrackRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;

@SpringBootTest
@ActiveProfiles("dev")
public class PlaylistServiceUnitTest {

	@MockBean
	private PlaylistRepository repo;

	@MockBean
	private TrackRepository trackRepo;

	@MockBean
	private Playlist_TrackRepository pTRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PlaylistService service;

	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.mapper.map(playlist, PlaylistDTO.class);
	}

	private PlaylistDTO mapToDTO2(List<PlaylistDTO> list) {
		return this.mapper.map(list, PlaylistDTO.class);
	}

	private PlaylistDTO mapToDTO3(List<Playlist> listPlaylist2) {
		return this.mapper.map(listPlaylist2, PlaylistDTO.class);
	}

	private final Playlist A_TEST_1 = new Playlist(1L, "TestPlaylist1", "description", "artwork");
	private final Playlist A_TEST_2 = new Playlist(2L, "TestPlaylist2", "description", "artwork");
	private final List<Playlist> listPlaylist = List.of(A_TEST_1, A_TEST_2);

	@Test
	void testCreate() throws Exception {
		A_TEST_1.setUser(new User(1L));
		A_TEST_2.setUser(new User(1L));
		PlaylistDTO testPlaylistDto = mapToDTO(A_TEST_1);

		when(repo.save(A_TEST_1)).thenReturn(A_TEST_1);

		PlaylistDTO result = service.create(testPlaylistDto, 1L);
		assertEquals(testPlaylistDto, result);

		verify(this.repo, atLeastOnce()).save(A_TEST_1);

	}

	@Test
	void testReadAll() throws Exception {
		when(repo.findAll()).thenReturn(listPlaylist);

		PlaylistDTO result = mapToDTO2(service.read());
		assertEquals(mapToDTO3(listPlaylist), result);

		verify(this.repo, atLeastOnce()).findAll();
	}

	@Test
	void testReadByID() throws Exception {
		Optional<Playlist> testPlaylist = Optional.of(A_TEST_1);
		PlaylistDTO testPlaylistDto = mapToDTO(A_TEST_1);

		when(repo.findById(1L)).thenReturn(testPlaylist);

		PlaylistDTO result = service.read(A_TEST_1.getId());
		assertEquals(testPlaylistDto, result);
		assertEquals(true, testPlaylist.isPresent());

		verify(this.repo, atLeastOnce()).findById(A_TEST_1.getId());

	}

	@Test
	void testUpdate() throws Exception {
		Playlist testPlaylist = new Playlist(1L, "TestPlaylist1 Updated", "description", "artwork");
		PlaylistDTO testPlaylistDto = mapToDTO(testPlaylist);
		Optional<Playlist> testPlaylistOp = Optional.of(testPlaylist);

		when(repo.findById(1L)).thenReturn(testPlaylistOp);
		when(repo.save(testPlaylist)).thenReturn(testPlaylist);

		PlaylistDTO result = service.update(testPlaylistDto, 1L);
		assertEquals(testPlaylistDto, result);
		assertEquals(true, testPlaylistOp.isPresent());

		verify(this.repo, atLeastOnce()).save(testPlaylist);

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

	@Test
	void testAddTrack() throws Exception {
		Track trackOne = new Track(1L, "TestTrack1", 111, "Lyrics");
		PlaylistDTO testPlaylistDto = mapToDTO(A_TEST_1);
		Optional<Track> testTrack = Optional.of(trackOne);
		Optional<Playlist> testPlaylist = Optional.of(A_TEST_1);
		Playlist_Track pTrack = new Playlist_Track(trackOne, A_TEST_1);

		when(repo.findById(1L)).thenReturn(testPlaylist);
		when(pTRepo.save(pTrack)).thenReturn(pTrack);
		when(trackRepo.findById(1L)).thenReturn(testTrack);

		PlaylistDTO result = service.addTrack(testPlaylistDto, 1L);
		assertEquals(testPlaylistDto, result);

		verify(this.repo, atLeastOnce()).findById(1L);
		verify(this.trackRepo, atLeastOnce()).findById(1L);
		verify(this.pTRepo, atLeastOnce()).save(pTrack);

	}

	@Test
	void testRemoveTrack() throws Exception {
		PlaylistDTO testPlaylistDto = mapToDTO(A_TEST_1);
		Optional<Playlist> testPlaylist = Optional.of(A_TEST_1);

		when(repo.findById(1L)).thenReturn(testPlaylist);

		PlaylistDTO result = service.removeTrack(testPlaylistDto, 1L);
		assertEquals(testPlaylistDto, result);

		verify(this.repo, atLeastOnce()).findById(1L);
		verify(this.pTRepo, atLeastOnce()).deleteById(1L);
	}

}
