package com.store.model

import spock.lang.Specification

import static com.store.model.Unit.LOAF

class ProductSpec extends Specification {
    def "should print product description"() {
        given:
        def aProduct = new Product(1, "Bread", LOAF, BigDecimal.valueOf(0.8))

        expect:
        aProduct.toString() == "[Bread\t, price=0.8]"

        and:
        aProduct == new Product(1, "Bread", LOAF, BigDecimal.valueOf(0.8))
    }
}
