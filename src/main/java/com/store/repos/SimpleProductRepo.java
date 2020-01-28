package com.store.repos;

import com.store.model.Product;

import java.util.List;
import java.util.Optional;

public class SimpleProductRepo implements ProductRepository {

    private final List<Product> products;

    public SimpleProductRepo(List<Product> products) {
        this.products = products;
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return products.stream().filter(product -> product.getName().equals(name)).findFirst();
    }
}
