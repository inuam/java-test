package com.store.offers;

import com.store.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.math.BigDecimal.valueOf;

public class AppleOffer implements Offer {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public AppleOffer(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public LocalDate getOfferStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getOfferEndDate() {
        return endDate;
    }

    @Override
    public BigDecimal apply(List<Product> products) {
         return products.stream()
                .filter(it->it.getName().equals("APPLE"))
                .map(Product::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add).multiply(valueOf(0.1));
    }
}
