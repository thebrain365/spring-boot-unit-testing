package dev.muano.springbootunittesting.repos;

import dev.muano.springbootunittesting.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, Integer> {
}
