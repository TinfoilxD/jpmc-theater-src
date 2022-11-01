package com.jpmc.theater.discount;

import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.entity.ShowingTest;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class SeventhDayOfMonthDiscountTest {

    SeventhDayOfMonthDiscount seventhDayOfMonthDiscount = new SeventhDayOfMonthDiscount();

    @Test
    void preCondition_ReturnsFalseOnNonSeventhDay() {
        Showing showing = ShowingTest.random();
        showing.setShowStartTime(showing.getStartTime().withDayOfMonth(new Random().nextInt(20) + 7));
        assertFalse(seventhDayOfMonthDiscount.checkCondition(showing));
    }

    @Test
    void preCondition_ReturnsTrueOnSeventhDay() {
        Showing showing = ShowingTest.random();
        showing.setShowStartTime(showing.getStartTime().withDayOfMonth(7));
        assertTrue(seventhDayOfMonthDiscount.checkCondition(showing));
    }


    @Test
    void getDiscountAppliesFlatDiscount() {
        Showing showing = ShowingTest.random();
        AppliedDiscount actual = seventhDayOfMonthDiscount.getDiscount(showing);
        assertThat(actual.getDiscountAmount(), equalTo(1.0));
        assertThat(actual.getCode(), equalTo(DiscountCode.SEVENTHDAY));
    }
}