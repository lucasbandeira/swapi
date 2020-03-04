package com.ame.swapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ame.swapi.model.Planet;

public interface PlanetRepository extends JpaRepository<Planet, Long>{
	Optional<Planet> findByName(String name);
}
