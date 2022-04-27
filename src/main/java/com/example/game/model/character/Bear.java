package com.example.game.model.character;

import java.util.Random;

public class Bear extends AbstractNPC {

    public Bear() {
        this.description = "There is a large menacing bear here!";
        this.hitPoints = 50;
    }

    @Override
    public String attackMessage() {
        return "The bear swipes his massive paw at you.";
    }

    @Override
    public int attackDamage() {
        return new Random().nextInt(12);
    }

    @Override
    public void receiveDamage(int damage) {
        this.hitPoints -= damage;
    }

    @Override
    public String toString() {

        final StringBuilder sb = new StringBuilder();
        sb.append(description);
        if (!this.isAlive()) {
            sb.append(" The bear is dead.");
        }
        return sb.toString();
    }
}
