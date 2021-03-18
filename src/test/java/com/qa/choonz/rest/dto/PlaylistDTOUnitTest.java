package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaylistDTOUnitTest {

	private PlaylistDTO testDTO;

	@BeforeEach
	public void beforeTest() {
		testDTO = new PlaylistDTO();
	}

	@Test
	public void testHashCode() {
		testDTO.hashCode();
		assertTrue(testDTO instanceof PlaylistDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testToString() {
		testDTO.toString();
		assertTrue(testDTO instanceof PlaylistDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testConstructor() {
		testDTO = new PlaylistDTO();
		assertTrue(testDTO instanceof PlaylistDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testEquals() {
		PlaylistDTO playlistdto1 = new PlaylistDTO();
		PlaylistDTO playlistdto2 = new PlaylistDTO();
		assertTrue(playlistdto1.equals(playlistdto2));
	}

	@Test
	public void testHashNew() {
		PlaylistDTO playlistdto1 = new PlaylistDTO();
		PlaylistDTO playlistdto2 = new PlaylistDTO();
		assertTrue(playlistdto1.hashCode() == playlistdto2.hashCode());
	}

}
