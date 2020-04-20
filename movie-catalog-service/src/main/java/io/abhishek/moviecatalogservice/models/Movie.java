package io.abhishek.moviecatalogservice.models;



public class Movie {

		
		private String movieId;
		private String description;
		private String name;
		
		
		public Movie() {

		}


		public Movie(String movieId, String name,String description) {
			super();
			this.movieId = movieId;
			this.name = name;
			this.description = description;
		}


		public String getMovieId() {
			return movieId;
		}


		public void setMovieId(String movieId) {
			this.movieId = movieId;
		}


		public String getDescription() {
			return description;
		}


		public void setDescrption(String description) {
			this.description = description;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}
	

}
