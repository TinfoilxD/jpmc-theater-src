package com.jpmc.theater.discount;

import com.jpmc.theater.entity.Showing;

public interface Discount {
    boolean checkCondition(Showing showing);

    AppliedDiscount getDiscount(Showing showing);

}
