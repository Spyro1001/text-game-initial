package com.example.game.util;

import com.example.game.GameWorld;
import com.example.game.model.Direction;
import com.example.game.model.Exit;
import com.example.game.model.Location;
import com.example.game.model.character.Bear;
import com.example.game.model.character.Troll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class GameInitializer {

    private static final Logger logger = LoggerFactory.getLogger(GameInitializer.class.getSimpleName());

    private GameInitializer() {
    }

    public static GameWorld initializeGame() {

        GameWorld game = new GameWorld();

        // Create room objects
        Location location1 = new Location("1", "Bottom of the Well", "You have reached the bottom of a deep and rather smelly well. Less than a foot of water remains, and it looks undrinkable.");
        Location location2 = new Location("2", "Courtyard", "At the centre of the courtyard is an old stone well. A strong and sturdy rope is attached to the well, and descends into the darkness. The only other items of interest are the farmhouse to the north, and a path to the east.");
        Location location3 = new Location("3", "Farmhouse Entrance", "The door to the farmhouse hangs crooked, and is slightly ajar. Obviously no-one has lived here for some time, and you can only guess at what lies within.");
        Location location4 = new Location("4", "Blood-Stained Room", "Dried blood stains can be seen on the walls and stone floor of the farmhouse. Whatever massacre occurred here long ago, you can only guess. With the absence of bodies, however, you may never know.");
        Location location5 = new Location("5", "Long Windy Path", "You are standing on a long, windy path, leading from the mountains in the far east, to a small farm that lies to the west.");
        Location location6 = new Location("6", "Base of the Mountain", "At the base of the mountain is a path that leads westward beyond a large boulder. Climbing such a mountain would be difficult - if not impossible.");
        Location location7 = new Location("7", "Top of the Mountain", "From this vantage point, you can see all that lies on the plains below. Large boulders dot the landscape, and just within view to the west you make out some sort of a building - though its details are too hard to make out from this distance.");
        Location location8 = new Location("8", "Outside Castle", "You are standing outside a rather large and obviously abandoned castle.  The drawbridge is up, but a cracked and broken door lays open headed downward into the castle dungeon.");
        Location location9 = new Location("9", "Castle Dungeon", "You enter a dimly lit room.  Along two of the walls, several skeletons lay slumped over and several more are chained to the wall.  It's apparent from the tracks on the ground that some kind of large animal has been here, but is now long gone.");

        // Create exit objects
        Exit exit1 = new Exit("1", Direction.UP, location2);
        Exit exit2 = new Exit("2", Direction.DOWN, location1);
        Exit exit3 = new Exit("3", Direction.NORTH, location3);
        Exit exit4 = new Exit("4", Direction.SOUTH, location2);
        Exit exit5 = new Exit("5", Direction.NORTH, location4);
        Exit exit6 = new Exit("6", Direction.SOUTH, location3);
        Exit exit7 = new Exit("7", Direction.EAST, location5);
        Exit exit8 = new Exit("8", Direction.WEST, location2);
        Exit exit9 = new Exit("9", Direction.EAST, location6);
        Exit exit10 = new Exit("10", Direction.WEST, location5);
        Exit exit11 = new Exit("11", Direction.UP, location7);
        Exit exit12 = new Exit("12", Direction.DOWN, location6);
        Exit exit13 = new Exit("13", Direction.WEST, location8);
        Exit exit14 = new Exit("14", Direction.EAST, location7);
        Exit exit15 = new Exit("15", Direction.DOWN, location9);
        Exit exit16 = new Exit("16", Direction.UP, location8);

        // associate exits with locations
        location1.addExit(exit1);
        location2.addExit(exit2);
        location2.addExit(exit3);
        location2.addExit(exit7);
        location3.addExit(exit4);
        location3.addExit(exit5);
        location4.addExit(exit6);
        location5.addExit(exit8);
        location5.addExit(exit9);
        location6.addExit(exit10);
        location6.addExit(exit11);
        location7.addExit(exit12);
        location7.addExit(exit13);
        location8.addExit(exit14);
        location8.addExit(exit15);
        location9.addExit(exit16);

        // create npc characters
        Bear bear = new Bear();
        Troll troll = new Troll();

        // add npc characters
        location6.addCharacter(bear);
        location8.addCharacter(troll);

        // set initial location
        game.setCurrentLocation(location2);

        logger.info("Game initialized.");
        return game;
    }
}
