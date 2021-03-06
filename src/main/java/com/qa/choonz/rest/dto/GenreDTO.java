package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenreDTO {

	private long id;
	private String name;
	private String description;
	private List<AlbumDTO> albums = new ArrayList<>();

//	public GenreDTO(long id, String name, String description, List<Album> albums) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.albums = albums;
//	}

}
