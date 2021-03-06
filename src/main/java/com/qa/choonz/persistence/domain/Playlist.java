package com.qa.choonz.persistence.domain;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Playlist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String name;

	@NotNull
	@Size(max = 500)
	@Column(unique = true)
	private String description;

	@NotNull
	@Size(max = 1000)
	@Column(unique = true)
	private String artwork;

	@OneToMany(mappedBy = "playlist",  cascade = CascadeType.ALL)
	private List<Playlist_Track> tracks;

	@ManyToOne
	private User user;

	public Playlist(long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
			@NotNull @Size(max = 1000) String artwork) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.artwork = artwork;
	}

	public Playlist(@NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
			@NotNull @Size(max = 1000) String artwork) {
		super();
		this.name = name;
		this.description = description;
		this.artwork = artwork;
	}
	
	public Playlist(long id) {
		this.id = id;
	}

}
