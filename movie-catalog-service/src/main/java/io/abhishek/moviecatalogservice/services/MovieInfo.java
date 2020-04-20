package io.abhishek.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.abhishek.moviecatalogservice.models.CatalogItem;
import io.abhishek.moviecatalogservice.models.Movie;
import io.abhishek.moviecatalogservice.models.Rating;


@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem")
	public CatalogItem getCatalogItem(Rating rating) {
		
		 Movie movie = webClientBuilder.build()
                 .get()
                 .uri("http://movie-info-service/movies/"+rating.getMovieId())
                 .retrieve()
                 .bodyToMono(Movie.class)
                 .block();
      return new CatalogItem(movie.getName(),movie.getDescription(),rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		
		 
     return new CatalogItem("No Movie","LOL",rating.getRating());
	}

}
