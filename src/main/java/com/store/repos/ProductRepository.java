package com.store.repos;

import com.store.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> getProductByName(String name);
}
