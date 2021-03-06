package com.qa.choonz.persistence.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(max = 100)
	private String name;

	@OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Album> albums;

	
	public Artist(Long id)
	{

		this.id = id;
	}

	public Artist(long id, @NotNull @Size(max = 100) String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public Artist(@NotNull @Size(max = 100) String name) {
		super();
		this.name = name;

	}
	
	public Artist(long id) {
		super();
		this.id = id;
	}
}
