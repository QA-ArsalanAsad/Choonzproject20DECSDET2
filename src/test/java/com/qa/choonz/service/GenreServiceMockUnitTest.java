package com.qa.choonz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;



@SpringBootTest
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

		//We
		GenreDTO testDTO = mapToDTO(testGenre);

		Mockito.when(genreRepo.save(testGenre)).thenReturn(testGenre);
		
		Mockito.when(mockMapper.map(testGenre, GenreDTO.class)).thenReturn(testDTO);
		
		GenreDTO result = genreService.create(testGenre);

		//We make sure what has happened matches to what we expect
		assertEquals(testDTO, result);

		//We confirm the the save method (which inserts the data into the table) has performed at least once
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
	public void readByIDTest()
	{
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
	public void updateTest()
	{
		
	}
	
//	@Test
//	public void deleteTest() {
//		Long testID = 2L;
//
//		testGenreDTO = this.mapToDTO(testGenre);
//		
//		GenreRepository delete = Mockito.mock(GenreRepository.class);
//		
//		Mockito.doNothing().when(delete).deleteById(testID);
//		delete.deleteById(testID);
//		Mockito.when(!this.genreRepo.existsById(testID)).thenReturn(true);
//		
//		boolean result = this.genreService.delete(testID);
//		boolean expected; // = !this.genreRepo.existsById(testID);
//		
//		
//		assertEquals(expected, result);
//
//		Mockito.verify(this.genreRepo, Mockito.times(1)).existsById(testID);
//	}
//	

}
