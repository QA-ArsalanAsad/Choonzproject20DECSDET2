package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AlbumDTO {

	private long id;
	private String name;
	private List<TrackDTO> tracks = new ArrayList<>();
	private String cover;

	// IS THIS REQUIRED?
//	public AlbumDTO(long id, String name, List<Track> tracks, Artist artist, Genre genre, String cover) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.tracks = tracks;
//		this.artist = artist;
//		this.genre = genre;
//		this.cover = cover;
//	}

}
