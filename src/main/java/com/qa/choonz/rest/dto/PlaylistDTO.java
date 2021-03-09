package com.qa.choonz.rest.dto;

import java.util.List;

import com.qa.choonz.persistence.domain.Track;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaylistDTO {

	private long id;
	private String name;
	private String description;
	private String artwork;
	private List<Track> tracks;

//	public PlaylistDTO(long id, String name, String description, String artwork, List<Track> tracks) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.artwork = artwork;
//		this.tracks = tracks;
//	}

}
