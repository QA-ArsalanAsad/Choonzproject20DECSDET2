package com.qa.choonz.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

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
public class PseduoGenreServiceUnitTest {
	
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

	// private final List<ToDoList> fullLists = List.of(testToDoList);

	private GenreDTO mapToDTO(Genre genres) {
		return PseduoGenreServiceUnitTest.testMapper.map(genres, GenreDTO.class);
	}

	@Test
	public void createTest() {

		//We
//		GenreDTO testDTO = mapToDTO();
//
//		Mockito.when(genreRepo.save()).thenReturn();
//		
//		Mockito.when(mockMapper.map(testGenre, GenreDTO.class)).thenReturn();
//		
//		GenreDTO result = genreService.create(testGenre);
//
//		//We make sure what has happened matches to what we expect
//		assertEquals(, );
//
//		//We confirm the the save method (which inserts the data into the table) has performed at least once
//		Mockito.verify(this.genreRepo, Mockito.times(1)).save(testGenre);
//	}

	}

}
