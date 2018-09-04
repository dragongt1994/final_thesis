package com.example.mark.myapplication;

public enum Mssg_Type {
    COMM("COMM"),
    CONFI("CONFI");
    private final String text;
    Mssg_Type(final String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return text;
    }

}
