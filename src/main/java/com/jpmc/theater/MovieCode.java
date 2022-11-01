package com.jpmc.theater;

public enum MovieCode {
    BASIC("BASIC"),
    SPECIAL("SPECIAL");

    private final String label;

    MovieCode(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
