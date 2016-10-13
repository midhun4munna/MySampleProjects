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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Film")
public class Film {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "filmid")
	private int filmId;
	@Column(name = "filmname")
	private String filmName;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Actor.class)
	@JoinTable(name = "Actor_Film", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "filmid") , inverseJoinColumns = @JoinColumn(name = "actor_id", referencedColumnName = "actorid") )
	private Set<Actor> actors = new HashSet<Actor>();

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getFilmName() {
		return filmName;
	}

	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}

	public Set<Actor> getActors() {
		return actors;
	}

	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}

	public void addActor(Actor actor) {
		this.actors.add(actor);
	}

}
