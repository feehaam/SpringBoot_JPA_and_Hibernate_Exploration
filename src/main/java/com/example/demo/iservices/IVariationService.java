package com.example.demo.iservices;

import com.example.demo.entities.Variation;
import java.util.List;
import java.util.Optional;

public interface IVariationService {
	Variation createVariation(int productId, Variation variation);
    Optional<Variation> getVariationById(int variationId);
    List<Variation> getAllVariations();
    Variation updateVariation(int variationId, Variation updatedVariation);
    boolean deleteVariation(int variationId);
}
