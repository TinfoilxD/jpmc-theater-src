package com.jpmc.theater.discount;

import com.jpmc.theater.entity.Showing;
import org.springframework.stereotype.Component;

@Component
public class FirstShowingDiscount implements Discount{
    @Override
    public boolean checkCondition(Showing showing) {
        return showing.isSequence(1);
    }

    @Override
    public AppliedDiscount getDiscount(Showing showing) {
        return new AppliedDiscount(3.0, DiscountCode.FIRSTSHOWING);
    }


}
