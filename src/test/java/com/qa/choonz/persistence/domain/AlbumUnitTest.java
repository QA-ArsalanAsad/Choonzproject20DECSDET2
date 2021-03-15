package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AlbumUnitTest {
	
	Album testAlbum;
	final Long id = 1L;
	final String name = "Enter Sandman";
	final String cover = "E.S-Cover";
	final Artist Metallica = new Artist();
	final Genre genre = new Genre();
	List<Track> tracks;
	
	@BeforeEach
	void init() {
		this.tracks = new ArrayList<Track>();
		this.testAlbum = new Album(this.id, this.name, this.tracks, this.genre, cover);
	}
	
	@Test
	void testFirstConstructor() {
		Album blackAlbum = new Album(this.id, this.name, this.tracks, this.genre, this.cover);
		assertTrue(blackAlbum instanceof Album);
	}
	
	@Test
	void testSecondConstructor() {
		Album blackAlbum = new Album(this.name, this.tracks, this.genre, this.cover);
		assertTrue(blackAlbum instanceof Album);
	}
	
	@Test
	void testThirdConstructor() {
		Album blackAlbum = new Album(this.name);
		assertTrue(blackAlbum instanceof Album);
	}
	
	@Test
	void testFourthConstructor() {
		Album blackAlbum = new Album(this.name, this.cover);
		assertTrue(blackAlbum instanceof Album);
	}
	
	@Test
	void testFifthConstructor() {
		Album blackAlbum = new Album(this.id);
		assertTrue(blackAlbum instanceof Album);
	}
	
	@Test
	void testNoArgsConstructor() {
		Album blackAlbum = new Album();
		assertTrue(blackAlbum instanceof Album);
	}
	
}
