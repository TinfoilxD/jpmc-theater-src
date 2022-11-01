package com.jpmc.theater.discount;

import com.jpmc.theater.DateProvider;
import com.jpmc.theater.entity.Showing;
import com.jpmc.theater.entity.ShowingTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PeakTimesDiscountTest {

    @Mock
    DateProvider dateProvider;

    PeakTimesDiscount peakTimesDiscount;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(dateProvider.currentDate()).thenReturn(LocalDate.now());
        peakTimesDiscount = new PeakTimesDiscount(dateProvider);
    }

    @Test
    void preCondition_ReturnsTrueAt11Am() {
        Showing showing = ShowingTest.random();
        showing.setShowStartTime(showing.getStartTime().withHour(11));
        assertTrue(peakTimesDiscount.checkCondition(showing));
    }

    @Test
    void preCondition_ReturnsTrueAt4pm() {
        Showing showing = ShowingTest.random();
        showing.setShowStartTime(showing.getStartTime().withHour(15));
        assertTrue(peakTimesDiscount.checkCondition(showing));
    }

    @Test
    void preCondition_ReturnsTrueBetween11And4() {
        Showing showing = ShowingTest.random();
        showing.setShowStartTime(showing.getStartTime().withHour(new Random().nextInt(4) + 11));
        assertTrue(peakTimesDiscount.checkCondition(showing));
    }

    @Test
    void preCondition_ReturnsFalseOutsideOfPeakTimes() {
        Showing showing = ShowingTest.random();

        Random rand = new Random();
        int beforePeak = rand.nextInt(11);
        int afterPeak = rand.nextInt(9) + 15;
        int hour = rand.nextBoolean() ? beforePeak : afterPeak;
        showing.setShowStartTime(showing.getStartTime().withHour(hour));
        assertFalse(peakTimesDiscount.checkCondition(showing));
    }

    @Test
    void getDiscountAppliesPercentageDiscount() {
        Showing showing = ShowingTest.random();
        AppliedDiscount actual = peakTimesDiscount.getDiscount(showing);
        assertThat(actual.getDiscountAmount(), equalTo(showing.getBaseTicketPrice() * .25));
        assertThat(actual.getCode(), equalTo(DiscountCode.PEAKTIME));
    }
}