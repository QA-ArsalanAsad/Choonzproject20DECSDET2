package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrackDTOUnitTest {
	
private TrackDTO testDTO;
	
	@BeforeEach
	public void beforeTest() {
		testDTO = new TrackDTO();
	}
	
	@Test
	public void testHashCode() {
		testDTO.hashCode();
		assertTrue(testDTO instanceof TrackDTO);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testToString() {
		testDTO.toString();
		assertTrue(testDTO instanceof TrackDTO);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testConstructor() {
		testDTO = new TrackDTO();
		assertTrue(testDTO instanceof TrackDTO);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testEquals() {
		testDTO.equals(testDTO);
		assertTrue(testDTO instanceof TrackDTO);
		assertNotNull(testDTO);
	}

}
