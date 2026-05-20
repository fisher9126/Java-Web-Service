package re.bt3.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    static class Movie {

        private String movieId;
        private String title;
        private String genre;
        private double rating;

        public Movie(String movieId, String title,
                     String genre, double rating) {

            this.movieId = movieId;
            this.title = title;
            this.genre = genre;
            this.rating = rating;
        }

        public String getMovieId() {
            return movieId;
        }

        public String getTitle() {
            return title;
        }

        public String getGenre() {
            return genre;
        }

        public double getRating() {
            return rating;
        }
    }

    private List<Movie> movieList() {

        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("M001", "Inception", "Sci-Fi", 8.8));
        movies.add(new Movie("M002", "Parasite", "Drama", 8.6));
        movies.add(new Movie("M003", "Interstellar", "Sci-Fi", 8.7));

        return movies;
    }


    @GetMapping("/{movieId}")
    public Movie getMovieById(@PathVariable String movieId) {

        return movieList()
                .stream()
                .filter(movie -> movie.getMovieId().equals(movieId))
                .findFirst()
                .orElse(null);
    }

    @GetMapping
    public List<Movie> getMoviesByGenre(
            @RequestParam String genre) {

        return movieList()
                .stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }
}
