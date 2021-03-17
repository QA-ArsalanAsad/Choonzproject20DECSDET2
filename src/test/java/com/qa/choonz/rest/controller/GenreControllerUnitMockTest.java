package com.qa.choonz.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.service.GenreService;

@SpringBootTest
@ActiveProfiles("dev")
public class GenreControllerUnitMockTest {

	@Autowired
	private GenreController controller;

	@MockBean
	private GenreService genreService;

	@Autowired
	private ModelMapper mapper;

	private final Genre testGenre = new Genre(2L, "Pop", "Top 40", null);
	private final List<Genre> listsOfGenres = List.of(testGenre);

	private GenreDTO mapToDTO(Genre genre) {
		return this.mapper.map(genre, GenreDTO.class);
	}

	@Test
	public void createTest() throws Exception {

		when(this.genreService.create(testGenre)).thenReturn(this.mapToDTO(testGenre));

		assertThat(new ResponseEntity<GenreDTO>(this.mapToDTO(testGenre), HttpStatus.CREATED))
				.isEqualTo(this.controller.create(testGenre));

		verify(this.genreService, atLeastOnce()).create(testGenre);

	}

	@Test
	public void readAllTest() throws Exception {

		List<GenreDTO> testReadList = listsOfGenres.stream().map(this::mapToDTO).collect(Collectors.toList());

		when(this.genreService.read()).thenReturn(testReadList);
		ResponseEntity<List<GenreDTO>> expected = ResponseEntity.ok(testReadList);
		ResponseEntity<List<GenreDTO>> result = this.controller.read();
		assertEquals(expected, result);

		verify(this.genreService, atLeastOnce()).read();
	}

	@Test
	public void readGenreByIDTest() throws Exception {
		Long testID = 2L;
		GenreDTO testReadIDGenre = this.mapToDTO(testGenre);

		when(this.genreService.read(testID)).thenReturn(testReadIDGenre);
		ResponseEntity<GenreDTO> expected = ResponseEntity.ok(testReadIDGenre);
		ResponseEntity<GenreDTO> result = this.controller.read(testID);
		assertEquals(expected, result);

		verify(this.genreService, atLeastOnce()).read(testID);
	}

	@Test
	public void updateGenreTest() throws Exception {

		Long testID = 2L;

		Genre newGenre = new Genre();
		newGenre.setId(testID);
		newGenre.setName("Updated Task name");
		newGenre.setDescription("A cool new updated description");
		GenreDTO testUpdateGenre = this.mapToDTO(testGenre);

		when(this.genreService.update(newGenre, testID)).thenReturn(testUpdateGenre);
		ResponseEntity<GenreDTO> expected = new ResponseEntity<>(testUpdateGenre, HttpStatus.ACCEPTED);
		ResponseEntity<GenreDTO> result = this.controller.update(newGenre, testID);

		assertEquals(expected, result);
		verify(this.genreService, atLeastOnce()).update(newGenre, testID);
	}

	@Test
	public void testDelete() throws Exception {
		when(this.genreService.delete(1L)).thenReturn(true);
		assertEquals(new ResponseEntity<>(null, HttpStatus.NO_CONTENT), (this.controller.delete(1L)));
		assertEquals(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR), (this.controller.delete(99L)));
		verify(this.genreService, atLeastOnce()).delete(1L);
	}

}
