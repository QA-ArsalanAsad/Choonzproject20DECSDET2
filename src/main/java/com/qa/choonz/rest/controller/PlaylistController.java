package com.qa.choonz.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;

@RestController
@RequestMapping("/playlists")
@CrossOrigin

public class PlaylistController {

	private PlaylistService service;

	@Autowired
	public PlaylistController(PlaylistService service) {
		this.service = service;
	}

	@PostMapping("/create/{userID}")
	public ResponseEntity<PlaylistDTO> create(@RequestBody PlaylistDTO playlistDTO, @PathVariable Long userID) {
		return new ResponseEntity<>(this.service.create(playlistDTO, userID), HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<PlaylistDTO>> read() {
		return ResponseEntity.ok(this.service.read());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<PlaylistDTO> read(@PathVariable long id) {
		return ResponseEntity.ok(this.service.read(id));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<PlaylistDTO> update(@RequestBody PlaylistDTO playlistDTO, @PathVariable long id) {
		return new ResponseEntity<>(this.service.update(playlistDTO, id), HttpStatus.ACCEPTED);
	}

	@PostMapping("/addtrack/{trackID}")
	public ResponseEntity<PlaylistDTO> addTrack(@RequestBody PlaylistDTO playlistDTO, @PathVariable long trackID) {
		return new ResponseEntity<>(this.service.addTrack(playlistDTO, trackID), HttpStatus.ACCEPTED);
	}
	
//	@DeleteMapping("/removetrack/{trackID}")
//	public ResponseEntity<PlaylistDTO> removeTrack(@RequestBody PlaylistDTO playlistDTO, @PathVariable long trackID) {
//		return new ResponseEntity<>(this.service.removeTrack(playlistDTO, trackID), HttpStatus.ACCEPTED);
//	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<PlaylistDTO> delete(@PathVariable long id) {
		return this.service.delete(id) ? new ResponseEntity<PlaylistDTO>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
