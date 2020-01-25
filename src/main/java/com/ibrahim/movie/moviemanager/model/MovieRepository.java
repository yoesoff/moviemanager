package com.ibrahim.movie.moviemanager.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * MovieRepository
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByProductionHouseId(Long id);
    Optional<Movie> findByIdAndProductionHouseId(Long movieId, Long productionHouseId);
}