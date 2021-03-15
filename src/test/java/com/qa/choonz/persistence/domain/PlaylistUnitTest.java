package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlaylistUnitTest {
	
	Playlist testPlaylist;
	final long id = 1L;
	final String name = "Metallica-Playlist";
	final String description = "Metal Tracks";
	final String artwork = "artwork";
	List<Playlist_Track> tracks;
	
	@BeforeEach
	void init() {
		this.testPlaylist = new Playlist(this.id, this.name, this.description, this.artwork);
	}
	
	@Test
	void testFirstConstructor() {
		Playlist newPlaylist = new Playlist(this.id, this.name, this.description, this.artwork);
		assertTrue(newPlaylist instanceof Playlist);
	}
	
	@Test
	void testSecondConstructor() {
		Playlist newPlaylist = new Playlist(this.name, this.description, this.artwork);
		assertTrue(newPlaylist instanceof Playlist);
	}
	
	@Test
	void testThirdConstructor() {
		Playlist newPlaylist = new Playlist(this.id);
		assertTrue(newPlaylist instanceof Playlist);
	}
	
	@Test
	void testEmptyConstructor() {
		Playlist newPlaylist = new Playlist();
		assertTrue(newPlaylist instanceof Playlist);
	}

}
