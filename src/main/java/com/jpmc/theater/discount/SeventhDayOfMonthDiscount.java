package com.jpmc.theater.discount;

import com.jpmc.theater.entity.Showing;
import org.springframework.stereotype.Component;

@Component
public class SeventhDayOfMonthDiscount implements Discount{
    @Override
    public boolean checkCondition(Showing showing) {
        return showing.getStartTime().getDayOfMonth() == 7;
    }

    @Override
    public AppliedDiscount getDiscount(Showing showing) {
        return new AppliedDiscount(1.0, DiscountCode.SEVENTHDAY);
    }
}
