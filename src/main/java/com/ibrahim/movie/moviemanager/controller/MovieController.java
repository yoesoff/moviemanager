package com.ibrahim.movie.moviemanager.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.ibrahim.movie.moviemanager.exception.ResourceNotFoundException;
import com.ibrahim.movie.moviemanager.model.Movie;
import com.ibrahim.movie.moviemanager.model.MovieRepository;
import com.ibrahim.movie.moviemanager.model.ProductionHouseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * MovieController
 */
@RestController
@RequestMapping("/api/v1")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository; 

    @Autowired
    private ProductionHouseRepository productionHouseRepository;

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/movies/{id}")
    public Optional<Movie> getAMovie(@PathVariable(value = "id") Long id) {
        return movieRepository.findById(id);
    }
    
    @GetMapping("/productionhouses/{phid}/movies")
    public List<Movie> getMoviesByProductionHouse(@PathVariable(value = "phid") Long phId) {
        return movieRepository.findByProductionHouseId(phId);
    }

    @PostMapping("/productionhouses/{phid}/movies")
    public Movie create(@PathVariable(value = "phid") Long phId, @Valid @RequestBody Movie movie) throws ResourceNotFoundException {
        
        return productionHouseRepository.findById(phId).map(productionHouse->{
            movie.setProductionHouse(productionHouse);
            return movieRepository.save(movie);
        }).orElseThrow(() -> new ResourceNotFoundException("Production house not found"));
    }

    @PutMapping("/productionhouses/{phid}/movies/{movieid}")
    public Movie update(@PathVariable(value = "phid") Long phId, @PathVariable(value = "movieid") Long movieId, @Valid @RequestBody Movie reqMovie) throws ResourceNotFoundException{
        if (!productionHouseRepository.existsById(phId)) {
            throw new ResourceNotFoundException("Production House not found!");
        }

        return movieRepository.findById(movieId).map(movie -> {
            movie.setMovie(reqMovie.getMovie());
            return movieRepository.save(movie);
        }).orElseThrow(() -> new ResourceNotFoundException("movie id not found"));
    }

    @DeleteMapping("/productionhouses/{phid}/movies/{movieid}")
    public ResponseEntity<?> delete(@PathVariable(value = "phid") Long phId, @PathVariable(value = "movieid") Long movieId) throws ResourceNotFoundException {

        return movieRepository.findById(movieId).map(movie -> {
            movieRepository.delete(movie);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("movie id not found"));
    }
}