package com.ibrahim.movie.moviemanager.service;

import java.util.List;
import com.ibrahim.movie.moviemanager.model.Movie;
/**
 * MovieService
 */
public interface MovieService {
    List<Movie> findAll();
    Movie findById();         
}