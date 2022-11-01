package com.jpmc.theater.discount;

import com.jpmc.theater.DateProvider;
import com.jpmc.theater.entity.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeakTimesDiscount implements Discount{

    DateProvider dateProvider;

    @Autowired
    public PeakTimesDiscount(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    @Override
    public boolean checkCondition(Showing showing) {
        int hour = showing.getStartTime().getHour();
        return hour >= 11 && hour <= 15;
    }

    @Override
    public AppliedDiscount getDiscount(Showing showing) {
        return new AppliedDiscount(showing.getBaseTicketPrice() * .25, DiscountCode.PEAKTIME);
    }
}
