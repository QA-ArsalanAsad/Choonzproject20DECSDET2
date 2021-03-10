package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;

import com.qa.choonz.persistence.domain.Playlist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TrackDTO {

	private long id;
	private String name;
	private List<AlbumDTO> albums = new ArrayList<>();
	private List <PlaylistDTO> playlists = new ArrayList<>();
	private int duration;
	private String lyrics; 
}
