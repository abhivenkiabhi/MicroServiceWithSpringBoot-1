package io.abhishek.moviecatalogservice.resource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.abhishek.moviecatalogservice.models.CatalogItem;
import io.abhishek.moviecatalogservice.models.Movie;
import io.abhishek.moviecatalogservice.models.Rating;
import io.abhishek.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){
		//get all rated Movie id
		//for a movie id 
		// for each MovieId,take all the details from movie-info-service
		
		
//		List<Rating> ratings = webClientBuilder.build().get()
//				               .uri("http://localhost:8084/ratingsdata/users/"+userId)
//				               .retrieve()
//				               .bodyToMono(UserRating.class)
//				               .block();
		
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/"+userId,UserRating.class);
   return ratings.getRatings().stream().map(rating -> {
 //  Movie movie = restTemplate.getForObject("http://localhost:8083/movies/"+rating.getMovieId(), Movie.class);
   
	 Movie movie = webClientBuilder.build()
	                 .get()
	                 .uri("http://movie-info-service/movies/"+rating.getMovieId())
	                 .retrieve()
                     .bodyToMono(Movie.class)
                     .block();
   return new CatalogItem(movie.getName(),"test",rating.getRating());
   }).
     collect(Collectors.toList());
   
	}
		   
		   
//		return Collections.singletonList(
//			new CatalogItem("Transformer","test",4)
//		);
//		
	

}
