package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.utils.BeanUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlaylistService {

	private final PlaylistRepository repo;
	private final ModelMapper mapper;

	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.mapper.map(playlist, PlaylistDTO.class);
	}
	
	private Playlist mapFromDTO(PlaylistDTO playlistDTO) {
		return this.mapper.map(playlistDTO, Playlist.class);
	}
	
	public PlaylistDTO create(PlaylistDTO playlistDTO, Long userID) {
		Playlist tempPlaylist = this.mapFromDTO(playlistDTO);
		tempPlaylist.setUser(new User(userID));
		Playlist created = this.repo.save(tempPlaylist);
		return this.mapToDTO(created);
	}

	public List<PlaylistDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public PlaylistDTO read(long id) {
		Playlist found = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
		return this.mapToDTO(found);
	}

	public PlaylistDTO update(Playlist playlist, long id) {

		Playlist toUpdate = this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
		toUpdate.setName(toUpdate.getName());
		BeanUtils.mergeNotNull(playlist, toUpdate);
		Playlist updated = this.repo.save(toUpdate);
		return this.mapToDTO(updated);

		// Old Update Method \\
		/*
		 * Playlist toUpdate =
		 * this.repo.findById(id).orElseThrow(PlaylistNotFoundException::new);
		 * toUpdate.setName(toUpdate.getName());
		 * toUpdate.setDescription(toUpdate.getDescription());
		 * toUpdate.setArtwork(toUpdate.getArtwork());
		 * toUpdate.setTracks(toUpdate.getTracks()); Playlist updated =
		 * this.repo.save(toUpdate); return this.mapToDTO(updated);
		 */
	}

	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
