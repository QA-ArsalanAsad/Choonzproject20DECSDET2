package com.qa.choonz.persistence.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Track {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String name;

	@ManyToOne
	private Album album;

	@ManyToMany(mappedBy = "tracks")
	private List <Playlist> playlists;

	// in seconds
	private int duration;

	private String lyrics;

	public Track(long id, @NotNull @Size(max = 100) String name, int duration,
			String lyrics) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.lyrics = lyrics;
	}

	public Track(@NotNull @Size(max = 100) String name, int duration, String lyrics) {
		super();
		this.name = name;
		this.duration = duration;
		this.lyrics = lyrics;
	}
	
	public Track(long id) {
		this.id = id;
	}
}
