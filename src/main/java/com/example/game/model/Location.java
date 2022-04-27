package com.example.game.model;

import com.example.game.model.character.AbstractNPC;
import com.example.game.util.CollectionUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Location {

    private final String id;
    private final String roomTitle;
    private final String roomDescription;
    private final List<Exit> exits;
    private final List<AbstractNPC> characters;

    public Location(String id, String title, String description) {

        this.id = id;
        this.roomTitle = title;
        this.roomDescription = description;
        this.exits = new ArrayList<>();
        this.characters = new ArrayList<>();
    }

    public void addExit(Exit exit) {
        this.exits.add(exit);
    }

    public void addCharacter(AbstractNPC npc) {
        characters.add(npc);
    }

    public String getId() {
        return this.id;
    }

    public List<Exit> getExits() {
        return CollectionUtility.cloneList(this.exits);
    }

    public String getTitle() {
        return this.roomTitle;
    }

    public String getDescription() {
        return this.roomDescription;
    }

    public List<AbstractNPC> getCharacters() {
        return characters;
    }

    public int getActiveCharacterCount() {
        final AtomicInteger counter = new AtomicInteger(0);
        characters.forEach(npc -> {
            if (npc.isAlive()) {
                counter.incrementAndGet();
            }
        });
        return counter.get();
    }

    public boolean hasCharacters() {
        return this.characters.size() > 0;
    }

    public String toString() {
        return this.roomTitle;
    }
}
