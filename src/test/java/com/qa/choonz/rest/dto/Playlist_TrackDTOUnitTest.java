package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Playlist_TrackDTOUnitTest {

	private Playlist_TrackDTO testDTO;

	@BeforeEach
	public void beforeTest() {
		testDTO = new Playlist_TrackDTO();
	}

	@Test
	public void testHashCode() {
		testDTO.hashCode();
		assertTrue(testDTO instanceof Playlist_TrackDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testToString() {
		testDTO.toString();
		assertTrue(testDTO instanceof Playlist_TrackDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testConstructor() {
		testDTO = new Playlist_TrackDTO();
		assertTrue(testDTO instanceof Playlist_TrackDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testEquals() {
		Playlist_TrackDTO ptdto1 = new Playlist_TrackDTO();
		Playlist_TrackDTO ptdto2 = new Playlist_TrackDTO();
		assertTrue(ptdto1.equals(ptdto2));
	}

	@Test
	public void testHashNew() {
		Playlist_TrackDTO ptdto1 = new Playlist_TrackDTO();
		Playlist_TrackDTO ptdto2 = new Playlist_TrackDTO();
		assertTrue(ptdto1.hashCode() == ptdto2.hashCode());
	}

}
