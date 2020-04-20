package io.abhishek.moviecatalogservice.resource;

import java.lang.reflect.Array;
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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.abhishek.moviecatalogservice.models.CatalogItem;
import io.abhishek.moviecatalogservice.models.Movie;
import io.abhishek.moviecatalogservice.models.Rating;
import io.abhishek.moviecatalogservice.models.UserRating;
import io.abhishek.moviecatalogservice.services.MovieInfo;
import io.abhishek.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable String userId){

		
   UserRating ratings = userRatingInfo.getUserRating(userId);
   return ratings.getRatings().stream().map(rating -> movieInfo.getCatalogItem(rating))	
                                       .
                                       collect(Collectors.toList());
   }
		   
		   
//		return Collections.singletonList(
//			new CatalogItem("Transformer","test",4)
//		);
//		
	
	
	
	
	
	

}
