package com.store.offers

import com.store.model.Product
import spock.lang.Specification
import spock.lang.Unroll

import static com.store.model.Unit.SINGLE
import static java.time.LocalDate.now

class AppleOfferSpec extends Specification {
    def appleOffer = new AppleOffer(now(), now())

    @Unroll
    def "should return Apple offer discount for list of products"() {
        setup:
        def anApple = new Product(1, "APPLE", SINGLE, 0.1)
        def products = []

        expect:
        noOfApple.times { products << anApple }
        appleOffer.apply(products) == discount

        where:
        noOfApple || discount
        0         || 0
        1         || 0.01
        2         || 0.02
        3         || 0.03
        4         || 0.04
    }
}
