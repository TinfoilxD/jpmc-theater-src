package com.jpmc.theater.discount;

import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.entity.ShowingTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class SecondShowingDiscountTest {
    SecondShowingDiscount secondShowingDiscount = new SecondShowingDiscount();

    @Test
    void preCondition_ReturnsFalseOnHighShowing() {
        Showing showing = ShowingTest.random();
        showing.setSequenceOfTheDay(new Random().nextInt(10) + 3);
        assertFalse(secondShowingDiscount.checkCondition(showing));
    }

    @Test
    void preCondition_ReturnsTrueOnSecondDay() {
        Showing showing = ShowingTest.random();
        showing.setSequenceOfTheDay(2);
        assertTrue(secondShowingDiscount.checkCondition(showing));
    }

    @Test
    void preCondition_ReturnsFalseOnFirstDay() {
        Showing showing = ShowingTest.random();
        showing.setSequenceOfTheDay(1);
        assertFalse(secondShowingDiscount.checkCondition(showing));
    }

    @Test
    void getDiscountAppliesFlatDiscount() {
        Showing showing = ShowingTest.random();
        AppliedDiscount actual = secondShowingDiscount.getDiscount(showing);
        assertThat(actual.getDiscountAmount(), equalTo(2.0));
        assertThat(actual.getCode(), equalTo(DiscountCode.SECONDSHOWING));
    }
}