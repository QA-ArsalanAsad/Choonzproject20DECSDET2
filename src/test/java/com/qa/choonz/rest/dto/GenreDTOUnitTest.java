package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenreDTOUnitTest {

	private GenreDTO testDTO;

	@BeforeEach
	public void beforeTest() {
		testDTO = new GenreDTO();
	}

	@Test
	public void testHashCode() {
		testDTO.hashCode();
		assertTrue(testDTO instanceof GenreDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testToString() {
		testDTO.toString();
		assertTrue(testDTO instanceof GenreDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testConstructor() {
		testDTO = new GenreDTO();
		assertTrue(testDTO instanceof GenreDTO);
		assertNotNull(testDTO);
	}

	@Test
	public void testEquals() {
		GenreDTO genredto1 = new GenreDTO();
		GenreDTO genredto2 = new GenreDTO();
		assertTrue(genredto1.equals(genredto2));
	}

	@Test
	public void testHashNew() {
		GenreDTO genredto1 = new GenreDTO();
		GenreDTO genredto2 = new GenreDTO();
		assertTrue(genredto1.hashCode() == genredto2.hashCode());
	}

}
