package com.store.checkout;

import com.store.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShoppingBasket {

    private final LocalDate shoppingDate;
    private List<Product> items = new ArrayList<>();

    public ShoppingBasket(LocalDate shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    public boolean addProduct(Product product) {
        return items.add(product);
    }

    public LocalDate getShoppingDate() {
        return shoppingDate;
    }

    public List<Product> getItems() {
        return Collections.unmodifiableList(items);
    }

    public BigDecimal getTotal() {
        return items.stream().map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public String toString() {
        String receipt = "Date: " + shoppingDate + "\n";
        for (Product item : items) {
            receipt += item + "\n";
        }
        receipt += "===========================\n";
        receipt += "Sub total: " + getTotal();
        return receipt;
    }
}
