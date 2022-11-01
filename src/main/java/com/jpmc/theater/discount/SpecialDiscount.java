package com.jpmc.theater.discount;

import com.jpmc.theater.MovieCode;
import com.jpmc.theater.entity.Showing;
import org.springframework.stereotype.Component;

@Component
public class SpecialDiscount implements Discount{
    @Override
    public boolean checkCondition(Showing showing) {
        return showing.getMovie().getSpecialCode().equals(MovieCode.SPECIAL);
    }

    @Override
    public AppliedDiscount getDiscount(Showing showing) {
        return new AppliedDiscount(showing.getBaseTicketPrice() * .20, DiscountCode.SPECIAL);
    }

}
