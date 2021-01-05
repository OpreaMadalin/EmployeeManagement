package com.company.model;

public enum Department {
    IT(1),
    HR(2),
    SALES(3),
    MARKETING(4);
    private final int value;

    Department(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
