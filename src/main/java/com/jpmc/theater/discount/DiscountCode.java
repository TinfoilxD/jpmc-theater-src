package com.jpmc.theater.discount;

public enum DiscountCode {
    SPECIAL("SPECIAL"),
    FIRSTSHOWING("FIRSTSHOWING"),
    SECONDSHOWING("SECONDSHOWING"),
    PEAKTIME("PEAKTIME"),
    SEVENTHDAY("SEVENTHDAY");

    private final String label;

    DiscountCode(String label) {
        this.label = label;
    }

    public static DiscountCode getEnumByLabel(String label) {
        for(DiscountCode n : DiscountCode.values()){
            if(n.label.equals(label))
                return n;
        }
        return null;
    }
}
