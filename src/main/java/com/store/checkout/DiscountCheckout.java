package com.store.checkout;

import com.store.offers.Offer;
import java.math.BigDecimal;

import java.util.List;

import static java.math.RoundingMode.HALF_DOWN;
import static java.util.Collections.emptyList;

public class DiscountCheckout implements Checkout {

    private final List<Offer> offers;

    public DiscountCheckout(List<Offer> offers) {
        this.offers = offers == null ? emptyList() : offers;
    }

    @Override
    public BigDecimal apply(ShoppingBasket basket) {
        assert basket != null : "Shopping basket is null";

        BigDecimal totalDiscount = offers.stream()
                .filter(offer -> offer.isValidOn(basket.getShoppingDate()))
                .map(offer -> offer.apply(basket.getItems())).reduce(BigDecimal.ZERO, BigDecimal::add);
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
