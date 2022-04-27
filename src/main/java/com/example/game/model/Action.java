package com.example.game.model;

public enum Action {

    UNDEFINED("NULL"),
    KILL("K"),
    ATTACK("A");

    private final String abbreviation;

    Action(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }
}
