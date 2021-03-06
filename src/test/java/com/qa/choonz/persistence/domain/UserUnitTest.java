package com.qa.choonz.persistence.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserUnitTest {

	User testUser;
	final Long id = 1L;
	final String userName = "username";
	final String password = "password";
	List<Playlist> playlists;

	@BeforeEach
	void init() {
		this.testUser = new User(this.id, this.userName, this.password);
	}

	@Test
	void testFirstConstrutor() {
		User newUser = new User(this.id, this.userName, this.password);
		assertTrue(newUser instanceof User);
	}

	@Test
	void testSecondConstrutor() {
		User newUser = new User(this.userName, this.password);
		assertTrue(newUser instanceof User);
	}

	@Test
	void testThirdConstrutor() {
		User newUser = new User(this.id);
		assertTrue(newUser instanceof User);
	}

	@Test
	void testEmptyConstrutor() {
		User newUser = new User();
		assertTrue(newUser instanceof User);
	}

	@Test
	public void testHashCode() {
		testUser.hashCode();
		assertTrue(testUser instanceof User);
		assertNotNull(testUser);
	}

	@Test
	public void testToString() {
		testUser.toString();
		assertTrue(testUser instanceof User);
		assertNotNull(testUser);
	}

	@Test
	public void testEquals() {
		User user1 = new User(this.id, this.userName, this.password);
		User user2 = new User(this.id, this.userName, this.password);
		assertTrue(user1.equals(user2));
	}

	@Test
	public void testEquals2() {
		User user1 = new User(this.userName, this.password);
		User user2 = new User(this.userName, this.password);
		assertTrue(user1.equals(user2));
	}

	@Test
	public void testEquals3() {
		User user1 = new User(this.id);
		User user2 = new User(this.id);
		assertTrue(user1.equals(user2));
	}

	@Test
	public void testEquals4() {
		User user1 = new User();
		User user2 = new User();
		assertTrue(user1.equals(user2));
	}

	@Test
	public void testHashNew() {
		User user1 = new User();
		User user2 = new User();
		assertTrue(user1.hashCode() == user2.hashCode());
	}

}
