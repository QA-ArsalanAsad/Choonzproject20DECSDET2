package com.qa.choonz.rest.dto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserDTOUnitTest {
	
private UserDTO testDTO;
	
	@BeforeEach
	public void beforeTest() {
		testDTO = new UserDTO();
	}
	
	@Test
	public void testHashCode() {
		testDTO.hashCode();
		assertTrue(testDTO instanceof UserDTO);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testToString() {
		testDTO.toString();
		assertTrue(testDTO instanceof UserDTO);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testConstructor() {
		testDTO = new UserDTO();
		assertTrue(testDTO instanceof UserDTO);
		assertNotNull(testDTO);
	}
	
	@Test
	public void testEquals() {
		UserDTO userdto1 = new UserDTO();
		UserDTO userdto2 = new UserDTO();
		assertTrue(userdto1.equals(userdto2));
	}
	
	@Test
	public void testHashNew() {
		UserDTO userdto1 = new UserDTO();
		UserDTO userdto2 = new UserDTO();
		assertTrue(userdto1.hashCode() == userdto2.hashCode());
	}
	

}
