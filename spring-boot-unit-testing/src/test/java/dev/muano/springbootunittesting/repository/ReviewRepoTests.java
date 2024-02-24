package dev.muano.springbootunittesting.repository;

import dev.muano.springbootunittesting.models.Pokemon;
import dev.muano.springbootunittesting.models.Review;
import dev.muano.springbootunittesting.repos.PokemonRepo;
import dev.muano.springbootunittesting.repos.ReviewRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ReviewRepoTests {

    @Autowired
    private ReviewRepo reviewRepo;

    @Test
    public void ReviewRepo_SaveAll_ReturnSavedReview() {

        //Arrange
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        //Act
        Review savedReview = reviewRepo.save(review);

        //Assert
        Assertions.assertThat(savedReview).isEqualTo(review);
    }

    @Test
    public void ReviewRepo_GetAll_ReturnMoreThanOneReview() {

        //Arrange
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        Review review2 = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepo.save(review);
        reviewRepo.save(review2);

        //Act
        List<Review> reviewList = reviewRepo.findAll();

        //Assert
        Assertions.assertThat(reviewList.size()).isEqualTo(2);
    }

    @Test
    public void ReviewRepo_FindById_ReturnsReviewById() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepo.save(review);

        Review returnedReview = reviewRepo.findById(review.getId()).get();

        Assertions.assertThat(returnedReview).isNotNull();
    }

    @Test
    public void ReviewRepo_FindByTitle_ReturnsReviewByTitle() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepo.save(review);

        Review returnedReview = reviewRepo.findByTitle(review.getTitle()).get();

        Assertions.assertThat(returnedReview).isEqualTo(review);
    }

    @Test
    public void ReviewRepo_Update_ReturnsUpdatedReview() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepo.save(review);

        Review returnedReview = reviewRepo.findById(review.getId()).get();
        returnedReview.setTitle("new title");
        returnedReview.setContent("new content");
        returnedReview.setStars(4);

        Review updatedReview = reviewRepo.save(returnedReview);

        Assertions.assertThat(updatedReview).isEqualTo(returnedReview);
    }

    @Test
    public void ReviewRepo_DeleteById_DeletesReview() {
        Review review = Review.builder()
                .title("title")
                .content("content")
                .stars(5)
                .build();

        reviewRepo.save(review);

        reviewRepo.deleteById(review.getId());
        Optional<Review> returnedReview = reviewRepo.findById(review.getId());

        Assertions.assertThat(returnedReview).isEmpty();
    }
}









