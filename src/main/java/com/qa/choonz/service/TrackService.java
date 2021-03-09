package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.utils.BeanUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TrackService {

    private TrackRepository repo;
    private ModelMapper mapper;

    private TrackDTO mapToDTO(Track track) {
        return this.mapper.map(track, TrackDTO.class);
    }

    public TrackDTO create(Track track) {
        Track created = this.repo.save(track);
        return this.mapToDTO(created);
    }

    public List<TrackDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TrackDTO read(long id) {
        Track found = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
        return this.mapToDTO(found);
    }

    public TrackDTO update(Track track, long id) {
    	
        Track toUpdate = this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
        toUpdate.setName(track.getName());
        BeanUtils.mergeNotNull(track, toUpdate);
        Track updated = this.repo.save(toUpdate);
        return this.mapToDTO(updated);
    	 
    	 
    	 
    	     	// Old Update Method \\\
			/*
			 * Track toUpdate =
			 * this.repo.findById(id).orElseThrow(TrackNotFoundException::new);
			 * toUpdate.setName(track.getName()); toUpdate.setAlbum(track.getAlbum());
			 * toUpdate.setDuration(track.getDuration());
			 * toUpdate.setLyrics(track.getLyrics());
			 * toUpdate.setPlaylist(track.getPlaylist()); Track updated =
			 * this.repo.save(toUpdate); return this.mapToDTO(updated);
			 */
    }

    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

}
