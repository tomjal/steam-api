package com.github.goive.steamapi.enums;

public enum Type {
    GAME("game"),
    MOVIE("movie"),
    DEMO("demo"),
    UNDEFINED("undefined");

    private String value;

    private Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
