package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.utils.BeanUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrackService {

	private final TrackRepository repo;
	private final ModelMapper mapper;

	private TrackDTO mapToDTO(Track track) {
		return this.mapper.map(track, TrackDTO.class);
	}
	
	private Track mapFromDTO(TrackDTO trackDTO) {
		return this.mapper.map(trackDTO, Track.class);
	}
	
	public TrackDTO create(TrackDTO trackDTO, Long albumID) {
		Track tmpTrack = this.mapFromDTO(trackDTO);
		tmpTrack.setAlbum(new Album(albumID));
		Track created = this.repo.save(tmpTrack);
		return this.mapToDTO(created);
	}

	public List<TrackDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public TrackDTO read(long id) {
		Track found = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
		return this.mapToDTO(found);
	}

	public TrackDTO update(TrackDTO trackDTO, long id) {

		Track toUpdate = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
		BeanUtils.mergeNotNull(mapFromDTO(trackDTO), toUpdate);
		Track updated = this.repo.save(toUpdate);
		return this.mapToDTO(updated);
		
	}

//	public TrackDTO update(TrackDTO trackDTO, long id, String method, long playlistID) {
//
//		Track toUpdate = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
//		List<Playlist> tmpPlaylist = toUpdate.getPlaylists();
//		
//		if (method == "add") {
//			tmpPlaylist.add(new Playlist(playlistID));
//		} else if (method == "remove") {
//			tmpPlaylist.remove(new Playlist(playlistID));
//		}
//		
//		toUpdate.setPlaylists(tmpPlaylist);
//		
//		BeanUtils.mergeNotNull(mapFromDTO(trackDTO), toUpdate);
//		Track updated = this.repo.save(toUpdate);
//		return this.mapToDTO(updated);
//		
//	}

	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

}
