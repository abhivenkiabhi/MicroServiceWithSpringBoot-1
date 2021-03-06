package io.abhishek.moviecatalogservice.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.abhishek.moviecatalogservice.models.Rating;
import io.abhishek.moviecatalogservice.models.UserRating;

@Service
public class UserRatingInfo {
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@HystrixCommand(fallbackMethod = "getFallbackUserRating")
	public UserRating getUserRating(String userId) {
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId,UserRating.class);
	    return ratings;
	}
	
	public UserRating getFallbackUserRating(String userId) {
		UserRating userRating = new UserRating();
		userRating.setRatings(Arrays.asList(
				              new Rating("0",0)
				              ));
	    return userRating;
	}

}
