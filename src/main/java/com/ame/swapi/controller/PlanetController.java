package com.ame.swapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ame.swapi.business.PlanetService;
import com.ame.swapi.model.Planet;
import com.ame.swapi.repository.PlanetRepository;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Rest Controlle for planet search API
 * 
 * @author Lucas Bandeira
 * @since 03/02/2020
 */
@RestController
public class PlanetController {
	@Autowired
	private PlanetRepository planetRepository;
	@Autowired
	private PlanetService planetService;

	@GetMapping("/planets")
	public List<Planet> findAll() {
		return planetRepository.findAll();
	}
	
	@GetMapping("/swapi/planets")
	public List<Object> findPlanetsSwApi() throws UnirestException {
		return planetService.requestPlanets().toList();
	}

	@GetMapping("/planets/id/{id}")
	public Planet findById(@PathVariable Long id) {
		return planetRepository.findById(id)
					.orElse(null);
	}
	
	@GetMapping("/planets/name/{name}")
	public Planet findByName(@PathVariable String name) {
		return planetRepository.findByName(name)
					.orElse(null);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/planets")
	public Planet save(@Valid @RequestBody Planet planet) throws UnirestException {
		return planetService.save(planet);
	}
	
	@DeleteMapping("/planets/{id}")
    void deletePlanet(@PathVariable Long id) {
		planetRepository.deleteById(id);
    }
}
