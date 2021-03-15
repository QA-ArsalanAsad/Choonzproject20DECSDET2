package com.qa.choonz.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String userName;
	@NotNull
	private String password;

	private String auth;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Playlist> playlists;

	public User(Long id, @NotNull String userName, @NotNull String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}

	public User(@NotNull String userName, @NotNull String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public User(Long id) {
		super();
		this.id = id;
	}

}
