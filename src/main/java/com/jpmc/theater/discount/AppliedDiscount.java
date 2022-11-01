package com.jpmc.theater.discount;

public class AppliedDiscount {
    private double discountAmount;
    private DiscountCode code;

    public AppliedDiscount(double discountAmount, DiscountCode code) {
        this.discountAmount = discountAmount;
        this.code = code;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public DiscountCode getCode() {
        return code;
    }

    public void setCode(DiscountCode code) {
        this.code = code;
    }
}
