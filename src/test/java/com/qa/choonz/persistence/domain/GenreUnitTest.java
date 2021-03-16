package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenreUnitTest {

	Genre testGenre;
	final long id = 1L;
	final String name = "Metal";
	private String description = "heavy guitars";
	List<Album> albums;

	@BeforeEach
	void init() {
		this.albums = new ArrayList<Album>();
		this.testGenre = new Genre(this.id, this.name, this.description, this.albums);
	}

	@Test
	void testFirstConstructor() {
		Genre metal = new Genre(this.id, this.name, this.description, this.albums);
		assertTrue(metal instanceof Genre);
	}

	@Test
	void testSecondConstructor() {
		Genre metal = new Genre(this.name, this.description);
		assertTrue(metal instanceof Genre);
	}

	@Test
	void testThirdConstructor() {
		Genre metal = new Genre(this.id);
		assertTrue(metal instanceof Genre);
	}

	@Test
	void testEmptyConstructor() {
		Genre metal = new Genre();
		assertTrue(metal instanceof Genre);
	}

}
