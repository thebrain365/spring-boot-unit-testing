package dev.muano.springbootunittesting.repository;

import dev.muano.springbootunittesting.models.Pokemon;
import dev.muano.springbootunittesting.repos.PokemonRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PokemonRepoTests {

    @Autowired
    private PokemonRepo pokemonRepo;

    @Test
    public void PokemonRepo_SaveAll_ReturnSavedPokemon() {

        //Arrange
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        //Act
        Pokemon savedPokemon = pokemonRepo.save(pokemon);

        //Assert
        Assertions.assertThat(pokemon).isNotNull();
        Assertions.assertThat(pokemon.getId()).isGreaterThan(0);
    }

    @Test
    public void PokemonRepo_GetAll_ReturnMoreThanOnePokemon() {

        //Arrange
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        Pokemon pokemon2 = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepo.save(pokemon);
        pokemonRepo.save(pokemon2);

        //Act
        List<Pokemon> pokemonList = pokemonRepo.findAll();

        //Assert
        Assertions.assertThat(pokemonList).isNotNull();
        Assertions.assertThat(pokemonList.size()).isEqualTo(2);
    }

    @Test
    public void PokemonRepo_FindById_ReturnsPokemonById() {
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepo.save(pokemon);

        Pokemon returnedPokemon = pokemonRepo.findById(pokemon.getId()).get();

        Assertions.assertThat(returnedPokemon).isNotNull();
    }

    @Test
    public void PokemonRepo_FindByType_ReturnsPokemonByType() {
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepo.save(pokemon);

        Pokemon returnedPokemon = pokemonRepo.findByType(pokemon.getType()).get();

        Assertions.assertThat(returnedPokemon).isNotNull();
    }

    @Test
    public void PokemonRepo_Update_ReturnsUpdatedPokemon() {
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepo.save(pokemon);

        Pokemon returnedPokemon = pokemonRepo.findById(pokemon.getId()).get();
        returnedPokemon.setName("Raichu");
        returnedPokemon.setType("Fire");

        Pokemon updatedPokemon = pokemonRepo.save(returnedPokemon);

        Assertions.assertThat(returnedPokemon.getName()).isEqualTo("Raichu");
        Assertions.assertThat(returnedPokemon.getType()).isEqualTo("Fire");
        Assertions.assertThat(returnedPokemon.getId()).isEqualTo(pokemon.getId());
    }

    @Test
    public void PokemonRepo_DeleteById_DeletesPokemon() {
        Pokemon pokemon = Pokemon.builder()
                .name("pikachu")
                .type("electric")
                .build();

        pokemonRepo.save(pokemon);

        pokemonRepo.deleteById(pokemon.getId());
        Optional<Pokemon> returnedPokemon = pokemonRepo.findById(pokemon.getId());

        Assertions.assertThat(returnedPokemon).isEmpty();
    }
}









