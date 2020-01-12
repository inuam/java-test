package com.store.checkout

import com.store.model.Product
import spock.lang.Specification

import static com.store.model.Unit.SINGLE
import static com.store.model.Unit.TIN
import static java.time.LocalDate.parse

class ShoppingBasketSpec extends Specification {
    def "should create a shopping basket"() {
        when:
        def basket = new ShoppingBasket(parse("2020-01-01"))
        basket.addProduct(new Product(11, "APPLES", SINGLE, 0.10));
        basket.addProduct(new Product(22, "SOUP", TIN, 0.65));

        then:
        basket.getTotal() == 0.75

        and:
        basket.toString() == "Date: 2020-01-01\n" +
                "[APPLES\t, price=0.10]\n" +
                "[SOUP\t, price=0.65]\n" +
                "===========================\n" +
                "Sub total: 0.75"
    }
}
