package com.ame.swapi.business;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ame.swapi.model.Planet;
import com.ame.swapi.model.PlanetDTO;
import com.ame.swapi.repository.PlanetRepository;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Sercice that provide all business rules | AME Digital (Challenge)
 * 
 * @author Lucas Bandeira
 * @since 03/02/2020
 */
@Service
public class PlanetService {

	@Autowired
	private PlanetRepository planetRepository;

	public Planet save(Planet newPlanet) throws UnirestException {
		setCountFilms(newPlanet);

		return planetRepository.save(newPlanet);
	}

	private void setCountFilms(Planet newPlanet) throws UnirestException {
		PlanetDTO planetDTO = readPlanetsApiToPlanetDTO().stream()
				.filter(planet -> newPlanet.getName().equals(planet.getName())).findAny().orElse(null);

		if (planetDTO != null && planetDTO.getFilms() > 0) {
			newPlanet.setCountFilms(planetDTO.getFilms());
		} else {
			newPlanet.setCountFilms(0);
		}
	}

	private List<PlanetDTO> readPlanetsApiToPlanetDTO() throws UnirestException {
		JSONArray planets = requestPlanets();

		List<PlanetDTO> planetsDTO = new ArrayList<PlanetDTO>();
		for (int i = 0; i < planets.length(); i++) {

			JSONObject planet = planets.getJSONObject(i);
			PlanetDTO planetDTO = new PlanetDTO(planet.getString("name"), planet.getJSONArray("films").length());
			planetsDTO.add(planetDTO);
		}

		return planetsDTO;
	}

	public JSONArray requestPlanets() throws UnirestException {
		return Unirest.get("https://swapi.co/api/planets/").asJson().getBody().getObject().getJSONArray("results");
	}
}
