package com.store.checkout;

import com.store.offers.Offer;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static java.math.RoundingMode.HALF_DOWN;

public class DiscountCheckout implements Checkout {

    private final List<Offer> offers;

    public DiscountCheckout(List<Offer> offers) {
        this.offers = offers == null ? Collections.emptyList() : offers;
    }

    @Override
    public BigDecimal apply(ShoppingBasket basket) {
        assert basket != null : "Shopping basket is null";
        BigDecimal totalDiscount = offers.stream()
                .filter(it -> it.isValidOn(basket.getShoppingDate()))
                .map(it -> it.apply(basket.getItems())).reduce(BigDecimal.ZERO, BigDecimal::add);
        return basket.getTotal().subtract(totalDiscount).setScale(2, HALF_DOWN);
    }

    @Override
    public String printReceipt(ShoppingBasket basket) {
        String receipt = basket.toString() + "\n";
        BigDecimal total = apply(basket);
        receipt += "Offer discount: " + basket.getTotal().subtract(total) + "\n";
        receipt += "Grand total: " + total + "\n";
        return receipt;
    }
}
