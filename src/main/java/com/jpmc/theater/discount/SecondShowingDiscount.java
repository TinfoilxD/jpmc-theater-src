package com.jpmc.theater.discount;

import com.jpmc.theater.entity.Showing;
import org.springframework.stereotype.Component;

@Component
public class SecondShowingDiscount implements Discount{

    @Override
    public boolean checkCondition(Showing showing) {
        return showing.isSequence(2);
    }

    @Override
    public AppliedDiscount getDiscount(Showing showing) {
        return new AppliedDiscount(2.0, DiscountCode.SECONDSHOWING);
    }

}
