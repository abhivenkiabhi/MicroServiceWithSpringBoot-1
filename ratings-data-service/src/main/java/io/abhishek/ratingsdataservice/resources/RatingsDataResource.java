package io.abhishek.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.abhishek.ratingsdataservice.models.Rating;
import io.abhishek.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable String movieId) {
		return new Rating(movieId,4);
	}
	
	@RequestMapping("/users/{userId}")
	public UserRating getUserRating(@PathVariable String userId) {
		
		List<Rating> ratings = Arrays.asList(
				new Rating("500",4),
				new Rating("100",3)
				);
		UserRating userRating = new UserRating();
		userRating.setRatings(ratings);
		return userRating;
	}

}
