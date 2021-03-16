package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Playlist_TrackUnitTest {

	final long id = 1L;
	final Track track = new Track();
	final Playlist playlist = new Playlist();
	Playlist_Track playlistTrack;

	@BeforeEach
	void init() {
		this.playlistTrack = new Playlist_Track(this.track, this.playlist);
	}

	@Test
	void testFirstConstructor() {
		Playlist_Track newTrack = new Playlist_Track(this.track, this.playlist);
		assertTrue(newTrack instanceof Playlist_Track);
	}

	@Test
	void testEmptyConstructor() {
		Playlist_Track newTrack = new Playlist_Track();
		assertTrue(newTrack instanceof Playlist_Track);
	}

	@Test
	void testAllArgsConstructor() {
		Playlist_Track newTrack = new Playlist_Track(this.id, this.track, this.playlist);
		assertTrue(newTrack instanceof Playlist_Track);
	}

}
