package com.ibrahim.movie.moviemanager.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import com.ibrahim.movie.moviemanager.exception.ResourceNotFoundException;
import com.ibrahim.movie.moviemanager.model.ProductionHouse;
import com.ibrahim.movie.moviemanager.model.ProductionHouseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ProductionHouseController
 */
@RestController
@RequestMapping("/api/v1")
public class ProductionHouseController {

    @Autowired
    private ProductionHouseRepository productionHouseRepository;

    @GetMapping("/productionhouses")
    public List<ProductionHouse> getProductionHouses() {
        return productionHouseRepository.findAll();
    }

    @GetMapping("/productionhouses/{id}")
    public ResponseEntity<ProductionHouse> getProductionHouseById(@PathVariable(value = "id") Long productionHouseId) throws ResourceNotFoundException {
        ProductionHouse productionHouse = productionHouseRepository.findById(productionHouseId)
            .orElseThrow(()-> new ResourceNotFoundException("Production house not found ::" + productionHouseId) );

        return ResponseEntity.ok().body(productionHouse);
    }

    @PostMapping("/productionhouses")
    public ProductionHouse create(@Valid @RequestBody ProductionHouse productionHouse) {
        return productionHouseRepository.save(productionHouse);
    }

    @PutMapping("/productionhouses")
    public ResponseEntity<ProductionHouse> update(@PathVariable(value = "id") Long productionHouseId, @Valid @RequestBody ProductionHouse productionHouse) throws ResourceNotFoundException {
        ProductionHouse existingProductionHouse = productionHouseRepository.findById(productionHouseId)
            .orElseThrow(()-> new ResourceNotFoundException("Production house not found ::" + productionHouseId) );
        
        existingProductionHouse.setName(productionHouse.getName());
        final ProductionHouse updatedUser = productionHouseRepository.save(existingProductionHouse);

        return ResponseEntity.ok(updatedUser);
    }

    public Map <String, Boolean> delete(@PathVariable(value = "id") Long productionHouseId) throws ResourceNotFoundException {
        ProductionHouse productionHouse = productionHouseRepository.findById(productionHouseId)
            .orElseThrow(()-> new ResourceNotFoundException("Production house not found ::" + productionHouseId) );
        
        productionHouseRepository.delete(productionHouse);

        Map < String, Boolean > response = new HashMap < > ();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}