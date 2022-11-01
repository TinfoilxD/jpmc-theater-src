package com.jpmc.theater.discount;

import com.jpmc.theater.entity.Showing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DiscountService {

    List<Discount> discounts;

    @Autowired
    public DiscountService(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public AppliedDiscount getDiscount(Showing showing) {
        AppliedDiscount discount = discounts.stream()
                .filter(i -> i.checkCondition(showing))
                .map(i -> i.getDiscount(showing))
                .max(Comparator.comparing(AppliedDiscount::getDiscountAmount))
                .get();
        return discount;
    }


}
