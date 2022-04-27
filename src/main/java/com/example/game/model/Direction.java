package com.example.game.model;

public enum Direction {

    UNDEFINED("NULL"),
    NORTH("N"),
    SOUTH("S"),
    EAST("E"),
    WEST("W"),
    UP("U"),
    DOWN("D"),
    NORTHEAST("NE"),
    NORTHWEST("NW"),
    SOUTHEAST("SE"),
    SOUTHWEST("SW"),
    IN("I"),
    OUT("O");

    private final String abbreviation;

    Direction(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return this.abbreviation;
    }
}
