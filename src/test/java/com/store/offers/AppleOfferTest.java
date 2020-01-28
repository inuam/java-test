package com.store.offers;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static java.math.BigDecimal.ZERO;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AppleOfferTest {

    private AppleOffer classToTest = new AppleOffer(LocalDate.now(), LocalDate.now());

    @Test
    public void shouldReturnZeroDiscountWhenNoApplesBought() {
        // Given

        // When
        BigDecimal discount = classToTest.apply(Collections.emptyList()).setScale(2);

        // Then
        assertThat(discount, is(ZERO.setScale(2)));
    }


}