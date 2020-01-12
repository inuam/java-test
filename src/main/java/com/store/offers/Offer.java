package com.store.offers;

import com.store.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface Offer {

    BigDecimal apply(List<Product> products);
    LocalDate getOfferStartDate();
    LocalDate getOfferEndDate();

    default boolean isValidOn(LocalDate shoppingDate) {
        return !(shoppingDate.isBefore(getOfferStartDate()) || shoppingDate.isAfter(getOfferEndDate()));
    }
}
