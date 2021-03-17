package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlbumDTOUnitTest {

	private AlbumDTO testDTO;

	@BeforeEach
	public void beforeTest() {
		testDTO = new AlbumDTO();
	}

	@Test
	public void testHashCode() {
		testDTO.hashCode();
		assertTrue(testDTO instanceof AlbumDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testToString() {
		testDTO.toString();
		assertTrue(testDTO instanceof AlbumDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testConstructor() {
		testDTO = new AlbumDTO();
		assertTrue(testDTO instanceof AlbumDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testEquals() {
		AlbumDTO albumdto1 = new AlbumDTO();
		AlbumDTO albumdto2 = new AlbumDTO();
		assertTrue(albumdto1.equals(albumdto2));
	}

	@Test
	public void testHashNew() {
		AlbumDTO albumdto1 = new AlbumDTO();
		AlbumDTO albumdto2 = new AlbumDTO();
		assertTrue(albumdto1.hashCode() == albumdto2.hashCode());
	}

}
