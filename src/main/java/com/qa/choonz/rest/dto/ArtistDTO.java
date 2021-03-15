package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDTO {

	private long id;
	private String name;
	private List<AlbumDTO> albums = new ArrayList<>();

//	public ArtistDTO(long id, String name, List<Album> albums) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.albums = albums;
//	}

}
