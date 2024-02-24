package dev.muano.springbootunittesting.repos;

import dev.muano.springbootunittesting.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

    Optional<Review> findByTitle(String title);
}
