package io.abhishek.movieinfoservice.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.abhishek.movieinfoservice.models.Movie;
import io.abhishek.movieinfoservice.models.MovieSummary;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
    	
    	MovieSummary movieSummary = new MovieSummary("100","transformer","science fiction");
      //  MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/100" +  "?api_key=" +  apiKey, MovieSummary.class);
    	// MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/100?api_key=18e94ca29d18141ae610decdb188cb6c", MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }

}
