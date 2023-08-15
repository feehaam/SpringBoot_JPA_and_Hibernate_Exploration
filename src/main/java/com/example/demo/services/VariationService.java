package com.example.demo.services;

import com.example.demo.dao.VariationDAO;
import com.example.demo.entities.Product;
import com.example.demo.entities.Variation;
import com.example.demo.iservices.IVariationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariationService implements IVariationService {

    @Autowired
    private VariationDAO variationDao;
    @Autowired
    private ProductService products;

    @Override
    public Variation createVariation(int productId, Variation variation) {
    	Product product = products.getProductById(productId).get();
    	if(product != null) {
    		product.addVariation(variation);;
    		products.updateProduct(productId, product);
    		return variation;
    	}
    	return null;
    }

    @Override
    public Optional<Variation> getVariationById(int variationId) {
        return variationDao.findById(variationId);
    }

    @Override
    public List<Variation> getAllVariations() {
        return variationDao.findAll();
    }

    @Override
    public Variation updateVariation(int variationId, Variation updatedVariation) {
        Optional<Variation> existingVariation = getVariationById(variationId);
        if (existingVariation.isPresent()) {
            updatedVariation.setId(variationId);
            variationDao.save(updatedVariation);
            return updatedVariation;
        }
        return null;
    }

    @Override
    public boolean deleteVariation(int variationId) {
        if (variationDao.existsById(variationId)) {
            variationDao.deleteById(variationId);
            return true;
        }
        return false;
    }
}
