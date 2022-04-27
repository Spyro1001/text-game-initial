package com.example.game;

import com.example.game.model.Exit;
import com.example.game.model.Location;
import com.example.game.model.character.AbstractNPC;
import com.example.game.model.character.Player;
import com.example.game.util.GameOutputStream;

import java.io.OutputStream;
import java.util.List;

public class GameWorld {

    private final Player player;
    private Location currentLocation;
    private GameOutputStream gameOutputStream;

    public GameWorld() {

        this.currentLocation = null;
        this.player = new Player();
        setOutputStream(System.out, 80);
    }

    public Player getPlayer() {

        return player;
    }

    public Location getCurrentLocation() {

        return this.currentLocation;
    }

    public void setCurrentLocation(Location newLocation) {

        this.currentLocation = newLocation;
    }

    public void setOutputStream(OutputStream out, int width) {

        this.gameOutputStream = new GameOutputStream(out, width);
    }

    public void showLocation() {

        this.gameOutputStream.println(currentLocation.getTitle());
        this.gameOutputStream.println(currentLocation.getDescription());
        this.gameOutputStream.println();
    }

    public void showCharacters() {

        List<AbstractNPC> characters = this.currentLocation.getCharacters();
        characters.forEach(npc -> this.gameOutputStream.println(npc.toString()));
        this.gameOutputStream.println();
    }

    public void showExits() {

        this.gameOutputStream.println("There are exits available... ");
        for (Exit exit : this.currentLocation.getExits()) {
            this.gameOutputStream.println(exit.toString());
        }
    }

    public void showHpAll() {

        showHpPlayer();
        if (this.currentLocation.getCharacters().isEmpty()) {
            showHpCharacter();
        }
        println();
    }

    public void showHpPlayer() {

        println("The " +
                Player.class.getSimpleName() +
                " has " +
                this.player.getHitPoints() +
                " HP");
    }

    public void showHpCharacter() {

        List<AbstractNPC> characters = this.currentLocation.getCharacters();
        characters.forEach(npc -> println("The " +
                npc.getClass().getSimpleName().toLowerCase() +
                " has " +
                npc.getHitPoints() +
                " HP")
        );
    }

    public void println() {

        this.gameOutputStream.println();
    }

    public void println(String str) {

        this.gameOutputStream.println(str);
    }
}
