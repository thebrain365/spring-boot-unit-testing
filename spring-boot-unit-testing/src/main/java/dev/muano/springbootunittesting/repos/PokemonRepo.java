package dev.muano.springbootunittesting.repos;

import dev.muano.springbootunittesting.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepo extends JpaRepository<Pokemon, Integer> {
}
