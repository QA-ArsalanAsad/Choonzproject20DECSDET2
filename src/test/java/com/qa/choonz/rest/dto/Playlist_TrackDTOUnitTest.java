package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Playlist_Track;

public class Playlist_TrackDTOUnitTest {
	
	private Playlist_Track testDTO;
	
	@BeforeEach
	public void beforeTest() {
		testDTO = new Playlist_Track();
	}
	
	@Test
	public void testHashCode() {
		testDTO.hashCode();
		assertTrue(testDTO instanceof Playlist_Track);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testToString() {
		testDTO.toString();
		assertTrue(testDTO instanceof Playlist_Track);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testConstructor() {
		testDTO = new Playlist_Track();
		assertTrue(testDTO instanceof Playlist_Track);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testEquals() {
		testDTO.equals(testDTO);
		assertTrue(testDTO instanceof Playlist_Track);
		assertNotNull(testDTO);
	}

}
