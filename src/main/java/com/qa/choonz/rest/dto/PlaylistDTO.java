package com.qa.choonz.rest.dto;


import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlaylistDTO {

	private long id;
	private String name;
	private String description;
	private List <Playlist_TrackDTO> tracks = new ArrayList<>();
	private String artwork;

//	public PlaylistDTO(long id, String name, String description, String artwork, List<Track> tracks) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.description = description;
//		this.artwork = artwork;
//		this.tracks = tracks;
//	}

}
