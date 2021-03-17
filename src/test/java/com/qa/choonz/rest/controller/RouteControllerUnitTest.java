package com.qa.choonz.rest.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
public class RouteControllerUnitTest {

	@Autowired
	private RouteContoller controller;

	@Test
	public void testIndex() throws Exception {
		String result = controller.index();
		assertEquals("index.html", result);
	}

	@Test
	public void testHome() throws Exception {
		String result = controller.home();
		assertEquals("index.html", result);
	}

	@Test
	public void testTracks() throws Exception {
		String result = controller.tracks();
		assertEquals("tracks.html", result);
	}

}
