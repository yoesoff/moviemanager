package com.ibrahim.movie.moviemanager.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Movie
 */
@Entity
@Table(name = "movies")
public class Movie extends AuditModel{ 

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String movie;
    private String genre;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "production_house_id")
    private ProductionHouse productionHouse;

    public Movie(String movie, String genre, ProductionHouse productionHouse) {
        this.movie = movie;
        this.genre = genre;
        this.productionHouse = productionHouse;
    }

    public Movie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public ProductionHouse getProductionHouse() {
        return productionHouse;
    }

    public void setProductionHouse(ProductionHouse productionHouse) {
        this.productionHouse = productionHouse;
    }
}