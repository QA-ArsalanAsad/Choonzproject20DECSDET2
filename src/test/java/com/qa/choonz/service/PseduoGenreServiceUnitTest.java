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
	

	@Test
	public void createTest() {

		//RESOURCES
//		GenreDTO testDTO = mapToDTO();
		//Creat a new GenreDTO object and pefrom the mapTODTO method of a genre

		//ACTIONS
		
//		Mockito.when(genreRepo.save()).thenReturn();
		//Perform the save operation of the genre object then return the genre object
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
