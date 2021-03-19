package com.qa.choonz.rest.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Playlist_TrackDTO {

	private long id;
	private TrackDTO track;

}
