package com.samples.servlets.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Actor")
public class Actor {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "actorid")
	private int actorId;
	@Column(name = "name")
	private String name;
	@ManyToMany(mappedBy = "actors", cascade = { CascadeType.ALL }, targetEntity = Film.class)
	private Set<Film> films = new HashSet<Film>();

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}

}
