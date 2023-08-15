package com.example.demo.controllers;

import com.example.demo.entities.Variation;
import com.example.demo.iservices.IVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/variation")
public class VariationController {

    @Autowired
    private IVariationService variationService;

    @GetMapping("/{variationId}")
    public ResponseEntity<Variation> get(@PathVariable int variationId) {
        Optional<Variation> optionalVariation = variationService.getVariationById(variationId);
        return optionalVariation.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Object> get() {
        List<Variation> listOfVariations = variationService.getAllVariations();
        if (listOfVariations == null || listOfVariations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("There are no variations");
        }
        return ResponseEntity.ok(listOfVariations);
    }

    @PostMapping("/{productId}")
    public ResponseEntity<Variation> add(@PathVariable int productId, @RequestBody Variation variation) {
        return ResponseEntity.ok(variationService.createVariation(productId, variation));
    }

    @PutMapping("/{variationId}")
    public ResponseEntity<Variation> update(@PathVariable int variationId, @RequestBody Variation variation) {
        Variation updatedVariation = variationService.updateVariation(variationId, variation);
        return updatedVariation != null ? ResponseEntity.ok(updatedVariation) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{variationId}")
    public ResponseEntity<String> delete(@PathVariable int variationId) {
        if (variationService.deleteVariation(variationId)) {
            return ResponseEntity.ok("Variation is deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not delete variation");
        }
    }
}
