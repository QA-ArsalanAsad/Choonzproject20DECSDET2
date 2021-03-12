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

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;

@RestController
@RequestMapping("/albums")
@CrossOrigin
public class AlbumController {


	private AlbumService service;


	@Autowired
	public AlbumController(AlbumService service) {
		this.service = service;
	}


	@PostMapping("/create/{artistID}/{genreID}")
	public ResponseEntity<AlbumDTO> create(@RequestBody AlbumDTO albumDTO, @PathVariable Long artistID,
			@PathVariable Long genreID) {
		return new ResponseEntity<>(this.service.create(artistID, genreID, albumDTO), HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<AlbumDTO>> read() {
		return ResponseEntity.ok(this.service.read());
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<AlbumDTO> read(@PathVariable long id) {
		return ResponseEntity.ok(this.service.read(id));
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<AlbumDTO> update(@RequestBody Album album, @PathVariable long id) {
		return new ResponseEntity<>(this.service.update(album, id), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("delete/{id}")
	public ResponseEntity<AlbumDTO> delete(@PathVariable long id) {
		return this.service.delete(id) ? new ResponseEntity<AlbumDTO>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
