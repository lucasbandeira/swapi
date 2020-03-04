package com.ame.swapi.model;

/**
 * Data Transfer Object for get attributes external API
 * 
 * @author Lucas Bandeira
 * @since 03/02/2020
 */
public class PlanetDTO {
	private String name;
	private Integer countFilms;
	
	public PlanetDTO(String name, Integer countFilms) {
		this.name = name;
		this.countFilms = countFilms;
	}
	public String getName() {
		return name;
	}
	public Integer getFilms() {
		return countFilms;
	}
}
