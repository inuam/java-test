package com.store.repos;

import com.store.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class SimpleProductRepo implements ProductRepository {

    private final List<Product> products;

    public SimpleProductRepo(List<Product> products) {
        this.products = products;
    }

    public static Predicate<Product> byId(Long id) {
        return p -> p.getId().equals(id);
    }

    public static Predicate<Product> byName(String name) {
        return p -> p.getName().equals(name);
    }

    @Override
    public Optional<Product> getProduct(Long id) {
        return findFirst(byId(id));
    }

    @Override
    public Optional<Product> getProductByName(String name) {
        return findFirst(byName(name));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    private Optional<Product> findFirst(Predicate<Product> predicate) {
        return products.stream().filter(predicate).findFirst();
    }
}
