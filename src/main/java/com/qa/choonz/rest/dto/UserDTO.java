package com.qa.choonz.rest.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {

	private Long id;
	private String userName;
	private String password;
	private String auth;
	private List<PlaylistDTO> playlists = new ArrayList<>();

}
