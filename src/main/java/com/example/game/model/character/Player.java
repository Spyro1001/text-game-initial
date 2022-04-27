package com.example.game.model.character;

import java.util.Random;

public class Player extends AbstractCharacter {

    public static final int MAX_HP = 50;
    public static final int MAX_DAMAGE = 25;

    public Player() {
        this.hitPoints = MAX_HP;
    }

    public int attackDamage() {
        return new Random().nextInt(MAX_DAMAGE + 1);
    }

    public void receiveDamage(int damage) {
        this.hitPoints -= damage;
    }
}
