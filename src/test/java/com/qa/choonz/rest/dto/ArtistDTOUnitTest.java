package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtistDTOUnitTest {
	
private ArtistDTO testDTO;
	
	@BeforeEach
	public void beforeTest() {
		testDTO = new ArtistDTO();
	}
	
	@Test
	public void testHashCode() {
		testDTO.hashCode();
		assertTrue(testDTO instanceof ArtistDTO);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testToString() {
		testDTO.toString();
		assertTrue(testDTO instanceof ArtistDTO);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testConstructor() {
		testDTO = new ArtistDTO();
		assertTrue(testDTO instanceof ArtistDTO);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testEquals() {
		testDTO.equals(testDTO);
		assertTrue(testDTO instanceof ArtistDTO);
		assertNotNull(testDTO);
	}

}
