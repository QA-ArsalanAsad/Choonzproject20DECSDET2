package com.qa.choonz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;

@SpringBootTest
@ActiveProfiles("dev")
public class GenreServiceMockUnitTest {

	@MockBean
	private GenreRepository genreRepo;
	@MockBean
	private ModelMapper mockMapper;

	@Autowired
	private GenreService genreService;

	private final Genre testGenre = new Genre(2L, "Hip Hop", "A cool description", null);
	private final List<Genre> fullGenres = List.of(testGenre);
	private GenreDTO testGenreDTO;

	private static final ModelMapper testMapper = new ModelMapper();

	private GenreDTO mapToDTO(Genre genres) {
		return GenreServiceMockUnitTest.testMapper.map(genres, GenreDTO.class);
	}

	@Test
	public void createTest() {

		// We
		GenreDTO testDTO = mapToDTO(testGenre);

		Mockito.when(genreRepo.save(testGenre)).thenReturn(testGenre);

		Mockito.when(mockMapper.map(testGenre, GenreDTO.class)).thenReturn(testDTO);

		GenreDTO result = genreService.create(testGenre);

		// We make sure what has happened matches to what we expect
		assertEquals(testDTO, result);

		// We confirm the the save method (which inserts the data into the table) has
		// performed at least once
		Mockito.verify(this.genreRepo, Mockito.times(1)).save(testGenre);
	}

	@Test
	public void readAllTest() {

		List<GenreDTO> testReadGenreList = fullGenres.stream().map(this::mapToDTO).collect(Collectors.toList());

		when(this.genreService.read()).thenReturn(testReadGenreList);
		List<GenreDTO> result = testReadGenreList;
		List<Genre> expected = this.genreRepo.findAll();
		assertEquals(expected, result);

		Mockito.verify(this.genreRepo, Mockito.times(1)).findAll();
	}

	@Test
	public void readByIDTest() {
		Long testID = 2L;

		testGenreDTO = mapToDTO(testGenre);

		Optional<Genre> list = Optional.of(this.testGenre);
		Mockito.when(this.genreRepo.findById(testID)).thenReturn(list);
		Mockito.when(mockMapper.map(testGenre, GenreDTO.class)).thenReturn(testGenreDTO);
		GenreDTO result = this.genreService.read(testID);

		assertEquals(testGenreDTO, result);

		Mockito.verify(this.genreRepo, Mockito.times(1)).findById(testID);

	}

	@Test
	void testUpdate() throws Exception {
		// GIVEN
		Genre testGenre2 = new Genre(2L, "Hip Hop Updated", "A cool description Updated", null);
		GenreDTO testGenreDto = mapToDTO(testGenre);
		Optional<Genre> testGenreOp = Optional.of(testGenre);

		// AND
		when(genreRepo.findById(2L)).thenReturn(testGenreOp);
		when(genreRepo.save(testGenre2)).thenReturn(testGenre2);
		when(mockMapper.map(testGenre, GenreDTO.class)).thenReturn(testGenreDto);

		// WHEN
		GenreDTO result = genreService.update(testGenre2, 2L);
		assertEquals(testGenreDto, result);
		assertEquals(true, testGenreOp.isPresent());

		// THEN
		verify(this.genreRepo, atLeastOnce()).save(testGenre2);
		verify(this.mockMapper, atLeastOnce()).map(testGenre2, GenreDTO.class);

	}

	@Test
	void testDelete() throws Exception {

		Boolean result = genreService.delete(2L);

		assertEquals(true, result);
		verify(this.genreRepo, atLeastOnce()).deleteById(testGenre.getId());

	}

	@Test
	void testDeleteFalse() throws Exception {

		when(genreRepo.existsById(999L)).thenReturn(true);

		Boolean result = genreService.delete(999L);

		assertEquals(false, result);
		verify(this.genreRepo, atLeastOnce()).deleteById(999L);
		verify(this.genreRepo, atLeastOnce()).existsById(999L);

	}

}