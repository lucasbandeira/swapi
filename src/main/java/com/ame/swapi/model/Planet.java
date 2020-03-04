package com.ame.swapi.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotEmpty;

/**
 * Model that represent entity planet in data base
 * 
 * @author Lucas Bandeira
 * @since 03/02/2020
 */
@Entity
public class Planet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Please provide a name")
	private String name;
	
	@Column(name = "count_films")
	private Integer countFilms;
	
	@ElementCollection
	@CollectionTable(name = "planet_climate", joinColumns = @JoinColumn(name = "planet_id"))
	@Column(name = "name")
	private List<String> climate;
	
	@ElementCollection
	@CollectionTable(name = "planet_terrain", joinColumns = @JoinColumn(name = "planet_id"))
	@Column(name = "name")
	private List<String> terrain;
	
	public Planet() {}
	
	public Planet(String name, List<String> climate, List<String> terrain) {
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}

	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getClimate() {
		return climate;
	}
	public void setClimate(List<String> climate) {
		this.climate = climate;
	}
	public List<String> getTerrain() {
		return terrain;
	}
	public void setTerrain(List<String> terrain) {
		this.terrain = terrain;
	}
	public Integer getCountFilms() {
		return countFilms;
	}
	public void setCountFilms(Integer countFilms) {
		this.countFilms = countFilms;
	}
	
	
}
