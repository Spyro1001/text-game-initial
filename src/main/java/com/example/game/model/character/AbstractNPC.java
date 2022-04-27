package com.example.game.model.character;

public abstract class AbstractNPC extends AbstractCharacter implements Attackable {

    protected String description;

    public String getDescription() {
        return description;
    }
}
