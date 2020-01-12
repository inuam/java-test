package com.store.offers;

import com.store.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ZERO;
import static java.math.BigDecimal.valueOf;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class SoupBreadOffer implements Offer {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public SoupBreadOffer(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public BigDecimal apply(List<Product> products) {
        Map<String, Long> productCount = products
                .stream()
                .collect(groupingBy(Product::getName, counting()));

        Long soupCount = productCount.getOrDefault("SOUP", 0L);
        Long breadCount = productCount.getOrDefault("BREAD", 0L);

        if (soupCount < 2 || breadCount < 1) {
            return ZERO;
        }

        BigDecimal breadPrice =products.stream()
                .filter(product -> product.getName().equals("BREAD"))
                .findFirst()
                .get()
                .getPrice();

        long discountableLoafs = soupCount / 2;

        BigDecimal discount;
        if (breadCount < discountableLoafs) {
            discount = valueOf(breadCount).multiply(breadPrice).multiply(valueOf(0.5));
        } else {
            discount = valueOf(discountableLoafs).multiply(breadPrice).multiply(valueOf(0.5));
        }

        return discount;
    }

    @Override
    public LocalDate getOfferStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getOfferEndDate() {
        return endDate;
    }
}
