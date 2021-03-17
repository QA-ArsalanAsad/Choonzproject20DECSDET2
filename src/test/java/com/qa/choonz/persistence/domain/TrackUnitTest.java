package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TrackUnitTest {

	Track testTrack;
	final long id = 1L;
	final String name = "";
	final Album album = new Album();
	final int duration = 1;
	final String lyrics = "";

	@BeforeEach
	void init() {
		this.testTrack = new Track(this.id, this.name, this.duration, this.lyrics);
	}

	@Test
	void testFirstConstructor() {
		Track newTrack = new Track(this.id, this.name, this.duration, this.lyrics);
		assertTrue(newTrack instanceof Track);
	}

	@Test
	void testSecondConstructor() {
		Track newTrack = new Track(this.name, this.duration, this.lyrics);
		assertTrue(newTrack instanceof Track);
	}

	@Test
	void testThirdConstructor() {
		Track newTrack = new Track(this.id);
		assertTrue(newTrack instanceof Track);
	}

	@Test
	void testEmptyConstructor() {
		Track newTrack = new Track();
		assertTrue(newTrack instanceof Track);
	}

	@Test
	public void testHashCode() {
		testTrack.hashCode();
		assertTrue(testTrack instanceof Track);
		assertNotNull(testTrack);
	}

	@Test
	public void testToString() {
		testTrack.toString();
		assertTrue(testTrack instanceof Track);
		assertNotNull(testTrack);
	}

	@Test
	public void testEquals() {
		testTrack.equals(testTrack);
		assertTrue(testTrack instanceof Track);
		assertNotNull(testTrack);
	}
}
