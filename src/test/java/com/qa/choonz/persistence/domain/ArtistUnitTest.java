package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArtistUnitTest {

	Artist testArtist;
	final Long id = 1L;
	final String name = "Metallica";
	List<Album> albums;

	@BeforeEach
	void init() {
		this.albums = new ArrayList<Album>();
		this.testArtist = new Artist(this.id, this.name);
	}

	@Test
	void testFirstConstructor() {
		Artist Metallica = new Artist(this.id);
		assertTrue(Metallica instanceof Artist);
	}

	@Test
	void testSecondConstructor() {
		Artist Metallica = new Artist(this.id, this.name);
		assertTrue(Metallica instanceof Artist);
	}

	@Test
	void testThirdConstructor() {
		Artist Metallica = new Artist(this.name);
		assertTrue(Metallica instanceof Artist);
	}

	@Test
	void testFourthConstructor() {
		Artist Metallica = new Artist(1);
		assertTrue(Metallica instanceof Artist);
	}

	@Test
	void testEmptyConstructor() {
		Artist Metallica = new Artist();
		assertTrue(Metallica instanceof Artist);
	}
}
