package com.store.service;

import com.store.model.Product;
import com.store.repos.ProductRepository;

import java.util.Optional;

public class ProductService implements ProductRepository {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return productRepository.getProductByName(name);
    }

}
