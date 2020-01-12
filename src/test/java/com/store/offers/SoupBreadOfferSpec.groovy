package com.store.offers

import com.store.model.Product
import spock.lang.Specification
import spock.lang.Unroll

import static com.store.model.Unit.LOAF
import static com.store.model.Unit.TIN
import static java.math.BigDecimal.valueOf
import static java.time.LocalDate.now
import static java.time.LocalDate.parse

class SoupBreadOfferSpec extends Specification {

    def aLoafOfBread = new Product(1, "BREAD", LOAF, valueOf(0.8))
    def tinOfSoup = new Product(2, "SOUP", TIN, valueOf(0.65))
    def soupOffer = new SoupBreadOffer(now(), now())

    def "should return offer start date and end date"() {
        given:
        def today = now()
        def tomorrow = now().plusDays(1)

        when:
        def soupBreadOffer = new SoupBreadOffer(today, tomorrow)

        then:
        soupBreadOffer.getOfferStartDate() == today

        and:
        soupBreadOffer.getOfferEndDate() == tomorrow
    }

    def "is offer valid on a given date"() {
        setup:
        def offer = new AppleOffer(parse(validFrom), parse(validTo))

        expect:
        offer.isValidOn(parse(validOn)) == isValid

        where:
        validFrom    | validTo      | validOn      || isValid
        "2020-01-01" | "2020-01-01" | "2020-01-01" || true
        "2020-01-01" | "2020-01-10" | "2020-01-01" || true
        "2020-01-01" | "2020-01-10" | "2020-01-10" || true
        "2020-01-01" | "2020-01-10" | "2020-01-11" || false
        "2020-01-02" | "2020-01-10" | "2020-01-01" || false
    }

    @Unroll
    def "should return discount from Bread and Soup offer"() {
        setup:
        def products = []

        expect:
        noOfTinSoup.times { products << tinOfSoup }
        noLoafOfBread.times { products << aLoafOfBread }

        soupOffer.apply(products) == discount

        where:
        noOfTinSoup | noLoafOfBread || discount
        0           | 0             || 0
        1           | 0             || 0
        2           | 0             || 0
        1           | 1             || 0
        2           | 1             || 0.4
        3           | 0             || 0
        3           | 1             || 0.4
        4           | 2             || 0.8
        4           | 3             || 0.8
        6           | 3             || 1.2
        7           | 3             || 1.2
        7           | 1             || 0.4
    }

}
