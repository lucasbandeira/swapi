package com.swapi.ame.bussiness;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ame.swapi.business.PlanetService;
import com.ame.swapi.model.Planet;
import com.ame.swapi.repository.PlanetRepository;
import com.mashape.unirest.http.exceptions.UnirestException;

@ExtendWith(MockitoExtension.class)
public class PlanetServiceTeste {
	@InjectMocks
	PlanetService planetService;
	@Mock
	PlanetRepository planetRepository;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSaveFoundPlanetExternalApi() throws UnirestException {
		Planet planet = new Planet("Alderaan", Arrays.asList("temperate"), Arrays.asList("jungle", "mountains"));
		planetService.save(planet);
		
		assertTrue(planet.getCountFilms() > 0, "Error, film not found");
		Mockito.verify(this.planetRepository, Mockito.times(1)).save(planet);
	}
	
	@Test
	public void testSaveNotFoundPlanetExternalApi() throws UnirestException {
		Planet planet = new Planet("Planet Test", Arrays.asList("temperate"), Arrays.asList("jungle", "mountains"));
		planetService.save(planet);
		
		assertEquals(0, planet.getCountFilms(), "Error, film found");
		Mockito.verify(this.planetRepository, Mockito.times(1)).save(planet);
	}
	
}
