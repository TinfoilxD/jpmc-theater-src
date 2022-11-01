package com.jpmc.theater.discount;

import com.jpmc.theater.MovieCode;
import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.entity.ShowingTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class FirstShowingDiscountTest {

    FirstShowingDiscount firstShowingDiscount = new FirstShowingDiscount();

    @Test
    void preCondition_ReturnsFalseOnHighShowing() {
        Showing showing = ShowingTest.random();
        showing.setSequenceOfTheDay(new Random().nextInt(10) + 3);
        assertFalse(firstShowingDiscount.checkCondition(showing));
    }

    @Test
    void preCondition_ReturnsFalseOnSecondDay() {
        Showing showing = ShowingTest.random();
        showing.setSequenceOfTheDay(2);
        assertFalse(firstShowingDiscount.checkCondition(showing));
    }

    @Test
    void preCondition_ReturnsTrueOnFirstDay() {
        Showing showing = ShowingTest.random();
        showing.setSequenceOfTheDay(1);
        assertTrue(firstShowingDiscount.checkCondition(showing));
    }

    @Test
    void getDiscountAppliesFlatDiscount() {
        Showing showing = ShowingTest.random();
        AppliedDiscount actual = firstShowingDiscount.getDiscount(showing);
        assertThat(actual.getDiscountAmount(), equalTo(3.0));
        assertThat(actual.getCode(), equalTo(DiscountCode.FIRSTSHOWING));
    }
}