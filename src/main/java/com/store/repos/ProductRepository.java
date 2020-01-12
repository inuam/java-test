package com.store.repos;

import com.store.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> getProduct(Long id);
    Optional<Product> getProductByName(String name);
    List<Product> findAll();
}
