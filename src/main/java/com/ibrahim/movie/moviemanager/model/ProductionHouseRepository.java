package com.ibrahim.movie.moviemanager.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ProductionHouseRepository
 */
@Repository
public interface ProductionHouseRepository extends JpaRepository<ProductionHouse, Long> {

    
}