package com.example.game.model;

public class Exit implements Copyable<Exit> {

    private final String id;
    private final Direction direction;
    private final Location leadsTo;

    private Exit(Exit other) {

        this.id = other.id;
        this.leadsTo = other.leadsTo;
        this.direction = other.direction;
    }

    public Exit(String id, Direction direction, Location leadsTo) {

        this.id = id;
        this.direction = direction;
        this.leadsTo = leadsTo;
    }

    public String getId() {
        return this.id;
    }

    public String getDirectionName() {

        return this.direction.name();
    }

    public String getShortDirectionName() {

        return this.direction.getAbbreviation();
    }

    public Location getLeadsTo() {

        return this.leadsTo;
    }

    @Override
    public Exit copyOf(Exit exit) {

        return new Exit(exit);
    }

    @Override
    public String toString() {

        return this.direction.name();
    }
}
