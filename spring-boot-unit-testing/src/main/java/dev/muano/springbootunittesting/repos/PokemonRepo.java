package dev.muano.springbootunittesting.repos;

import dev.muano.springbootunittesting.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepo extends JpaRepository<Pokemon, Integer> {

    Optional<Pokemon> findByType(String type);
}
