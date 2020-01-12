package com.store.checkout

import com.store.model.Product
import com.store.model.Unit
import com.store.offers.AppleOffer
import com.store.offers.SoupBreadOffer
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

import static java.time.LocalDate.now
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth
import static java.util.Arrays.asList

class DiscountCheckoutSpec extends Specification {
    @Shared
    def today = now()

    def "should return zero given no basket"() {
        given:
        def checkout = new DiscountCheckout(null)

        when:
        checkout.apply(null) == BigDecimal.ZERO

        then:
        thrown(AssertionError)
    }

    @Unroll
    def "should apply all offers to shopping basket"() {
        setup:
        def soupOffer = new SoupBreadOffer(now().minusDays(1), now().plusDays(7))
        def appleOffer = new AppleOffer(now().plusDays(3), now().plusMonths(1).with(lastDayOfMonth()))
        def checkout = new DiscountCheckout(asList(appleOffer, soupOffer))


        expect:
        def basket = addToShoppingBasket(shoppingDate, apples, soup, bread, milk)

        checkout.apply(basket) == total

        where:
        shoppingDate      | apples | soup | bread | milk || total
        today             | 0      | 3    | 2     | 0    || 3.15
        today             | 6      | 0    | 0     | 1    || 1.90
        today.plusDays(5) | 6      | 0    | 0     | 1    || 1.84
        today.plusDays(5) | 3      | 2    | 1     | 0    || 1.97
        today             | 0      | 0    | 0     | 0    || 0.00
    }


    def addToShoppingBasket(LocalDate shoppingDate, int noOfApples, int tinsOfSoup, int loafsOfBread, int bottlesOfMilk) {

        def basket = new ShoppingBasket(shoppingDate)

        noOfApples.times { basket.addProduct(new Product(11, "APPLE", Unit.SINGLE, 0.10)) }
        tinsOfSoup.times { basket.addProduct(new Product(22, "SOUP", Unit.TIN, 0.65)) }
        loafsOfBread.times { basket.addProduct(new Product(33, "BREAD", Unit.LOAF, 0.80)) }
        bottlesOfMilk.times { basket.addProduct(new Product(44, "MILK", Unit.BOTTLE, 1.3)) }

        return basket
    }
}
