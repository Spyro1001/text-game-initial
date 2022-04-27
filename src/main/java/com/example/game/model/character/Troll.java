package com.example.game.model.character;

import java.util.Random;

public class Troll extends AbstractNPC {

    public Troll() {
        this.description = "There is a somewhat docile troll here.";
        this.hitPoints = 500;
    }

    @Override
    public String attackMessage() {
        return "The troll is no longer somewhat docile.  The troll swings his huge club down on your head, crushing your skull.";
    }

    @Override
    public int attackDamage() {
        return new Random().nextInt(250);
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
            int index = sb.indexOf("docile");
            sb.replace(index, index + 6, "dead");
        }
        return sb.toString();
    }

}
