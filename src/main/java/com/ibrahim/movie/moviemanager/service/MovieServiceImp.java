package com.ibrahim.movie.moviemanager.service;

import java.util.List;

import com.ibrahim.movie.moviemanager.model.Movie;
import com.ibrahim.movie.moviemanager.model.MovieRepository;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * MovieServiceImp
 */
public class MovieServiceImp implements MovieService {

    @Autowired
    private MovieRepository movieRepo;

    @Override
    public List<Movie> findAll() {
        return movieRepo.findAll();
    }

    @Override
    public Movie findById() {
        return null;
    }

    
}