package com.jpmc.theater.discount;

import com.jpmc.theater.entity.ShowingTest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DiscountServiceTest {

    DiscountService discountService;

    @Test
    void getDiscountReturnsHighestDiscount() {
        List<Discount> mockDiscounts = List.of(
                mockDiscountWithValue(10),
                mockDiscountWithValue(5),
                mockDiscountWithValue(15),
                mockDiscountWithValue(20)
        );
        discountService = new DiscountService(mockDiscounts);
        AppliedDiscount actual = discountService.getDiscount(ShowingTest.random());
        assertEquals(actual.getDiscountAmount(), 20);
    }

    private Discount mockDiscountWithValue(double discountAmount) {
        Discount discount = mock(Discount.class);
        when(discount.checkCondition(any())).thenReturn(true);
        when(discount.getDiscount(any())).thenReturn(new AppliedDiscount(discountAmount, randomCode()));
        return discount;
    }

    private DiscountCode randomCode() {
        int n = new Random().nextInt(DiscountCode.values().length);
        return DiscountCode.values()[n];
    }
}