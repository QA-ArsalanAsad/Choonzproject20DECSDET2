package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.AlbumNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.utils.BeanUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlbumService {

	private final AlbumRepository repo;
	private final ModelMapper mapper;

	private AlbumDTO mapToDTO(Album album) {
		return this.mapper.map(album, AlbumDTO.class);
	}

	private Album mapFromDTO(AlbumDTO albumDTO) {
		return this.mapper.map(albumDTO, Album.class);
	}

	public AlbumDTO create(Long artistID, Long genreID, AlbumDTO albumDTO) {
		Album tempAlbum = this.mapFromDTO(albumDTO);
		tempAlbum.setArtist(new Artist(artistID));
		tempAlbum.setGenre(new Genre(genreID));
		Album created = this.repo.save(tempAlbum);
		return this.mapToDTO(created);
	}

	public List<AlbumDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public AlbumDTO read(long id) {
		Album found = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
		return this.mapToDTO(found);
	}

	public AlbumDTO update(Album album, long id) {

		Album toUpdate = this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
		toUpdate.setName(toUpdate.getName());
		BeanUtils.mergeNotNull(album, toUpdate);
		Album updated = this.repo.save(toUpdate);
		return this.mapToDTO(updated);

		// Old Update Method \\\
		/*
		 * Album toUpdate =
		 * this.repo.findById(id).orElseThrow(AlbumNotFoundException::new);
		 * toUpdate.setName(toUpdate.getName());
		 * toUpdate.setTracks(toUpdate.getTracks());
		 * toUpdate.setArtist(toUpdate.getArtist());
		 * toUpdate.setCover(toUpdate.getCover()); Album updated =
		 * this.repo.save(toUpdate); return this.mapToDTO(updated);
		 */
	}

	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
