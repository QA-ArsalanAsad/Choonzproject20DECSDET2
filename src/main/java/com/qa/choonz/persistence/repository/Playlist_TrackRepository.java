package com.qa.choonz.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.choonz.persistence.domain.Playlist_Track;

@Repository
public interface Playlist_TrackRepository extends JpaRepository<Playlist_Track, Long> {

}
