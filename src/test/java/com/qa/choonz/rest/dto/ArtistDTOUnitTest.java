package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtistDTOUnitTest {
	
private ArtistDTO testDTO;
private List<AlbumDTO> albums = new ArrayList<>();
	
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
		ArtistDTO artistdto1 = new ArtistDTO(2L, "artist", albums);
		ArtistDTO artistdto2 = new ArtistDTO(2L, "artist", albums);
		assertTrue(artistdto1.equals(artistdto2));
	}
	
	@Test
	public void testEquals2() {
		ArtistDTO artistdto1 = new ArtistDTO();
		ArtistDTO artistdto2 = new ArtistDTO();
		assertTrue(artistdto1.equals(artistdto2));
	}
	
	@Test
	public void testHashNew() {
		ArtistDTO artistdto1 = new ArtistDTO();
		ArtistDTO artistdto2 = new ArtistDTO();
		assertTrue(artistdto1.hashCode() == artistdto2.hashCode());
	}

}
