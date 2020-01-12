package com.store.checkout;

import java.math.BigDecimal;

public interface Checkout {
    BigDecimal apply(ShoppingBasket basket);
    String printReceipt(ShoppingBasket basket);
}
