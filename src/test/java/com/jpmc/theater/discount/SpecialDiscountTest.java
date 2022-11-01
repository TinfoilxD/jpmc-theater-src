package com.jpmc.theater.discount;

import com.jpmc.theater.MovieCode;
import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.entity.ShowingTest;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class SpecialDiscountTest {

    SpecialDiscount specialDiscount = new SpecialDiscount();

    @Test
    void preCondition_ReturnsFalseOnNormal() {
        Showing showing = ShowingTest.random();
        showing.getMovie().setSpecialCode(MovieCode.BASIC);
        assertFalse(specialDiscount.checkCondition(showing));
    }

    @Test
    void preCondition_ReturnsTrueOnSpecial() {
        Showing showing = ShowingTest.random();
        showing.getMovie().setSpecialCode(MovieCode.SPECIAL);
        assertTrue(specialDiscount.checkCondition(showing));
    }

    @Test
    void getDiscountAppliesPercentageDiscount() {
        Showing showing = ShowingTest.random();
        AppliedDiscount actual = specialDiscount.getDiscount(showing);
        assertThat(actual.getDiscountAmount(), equalTo(showing.getBaseTicketPrice() * .20));
        assertThat(actual.getCode(), equalTo(DiscountCode.SPECIAL));
    }
}