package com.example.game.model.character;

public interface Attackable {

    String attackMessage();

    int attackDamage();

    void receiveDamage(int damage);
}
