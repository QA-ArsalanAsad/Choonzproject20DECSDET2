package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

	@Test
	public void testHashCode() {
		testGenre.hashCode();
		assertTrue(testGenre instanceof Genre);
		assertNotNull(testGenre);
	}

	@Test
	public void testToString() {
		testGenre.toString();
		assertTrue(testGenre instanceof Genre);
		assertNotNull(testGenre);
	}

	@Test
	public void testEquals() {
		Genre genre1 = new Genre(this.id, this.name, this.description, this.albums);
		Genre genre2 = new Genre(this.id, this.name, this.description, this.albums);
		assertTrue(genre1.equals(genre2));
	}

	@Test
	public void testEquals2() {
		Genre genre1 = new Genre(this.name, this.description);
		Genre genre2 = new Genre(this.name, this.description);
		assertTrue(genre1.equals(genre2));
	}

	@Test
	public void testEquals3() {
		Genre genre1 = new Genre(2L);
		Genre genre2 = new Genre(2L);
		assertTrue(genre1.equals(genre2));
	}

	@Test
	public void testEquals4() {
		Genre genre1 = new Genre();
		Genre genre2 = new Genre();
		assertTrue(genre1.equals(genre2));
	}

	@Test
	public void testHashNew() {
		Genre genre1 = new Genre();
		Genre genre2 = new Genre();
		assertTrue(genre1.hashCode() == genre2.hashCode());
	}

}
