package com.example.game;

import com.example.game.model.Action;
import com.example.game.model.Direction;
import com.example.game.model.Exit;
import com.example.game.model.character.AbstractNPC;
import com.example.game.model.character.Player;
import com.example.game.util.GameInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

class TextAdventureGame {

    private static final Logger logger = LoggerFactory.getLogger(TextAdventureGame.class.getName());
    private final GameWorld game;

    public TextAdventureGame() {

        game = GameInitializer.initializeGame();
        game.setOutputStream(System.out, 60);
    }

    public static void main(String[] args) {

        try {
            new TextAdventureGame().play();
        } catch (Exception e) {
            logger.error("There was an error.", e);
        }
    }

    private void play() {

        String command;
        AtomicBoolean validCommand = new AtomicBoolean(false);
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {

            // show location
            game.showLocation();

            // show characters
            if (game.getCurrentLocation().hasCharacters()) {
                game.showCharacters();
            }

            // show exits
            game.showExits();

            // Get user input
            command = scanner.nextLine();
            this.game.println();

            // Parse user input
            if (command.length() == 0) {
                this.game.println("Huh? Invalid command!");
                continue;
            }

            // Identify command type
            command = command.toUpperCase().trim();
            String finalCommand = command;

            boolean isAction = Arrays.stream(Action.values())
                    .anyMatch(action -> action.name().equals(finalCommand) ||
                            action.getAbbreviation().equals(finalCommand));
            boolean isDirection = Arrays.stream(Direction.values())
                    .anyMatch(direction -> direction.name().equals(finalCommand) ||
                            direction.getAbbreviation().equals(finalCommand));
            boolean isQuit = doQuit(command);

            // process move
            if (isDirection) {
                boolean success = doMove(command);
                validCommand.set(success);
                game.getPlayer().setHitPoints(Player.MAX_HP);
            } else if (isAction) {
                boolean success = doAttack(command);
                validCommand.set(success);
            } else if (isQuit) {
                validCommand.set(true);
                running = false;
            }

            // If no valid commands, warn the user...
            if (!validCommand.get()) {
                this.game.println("Huh? Invalid command!");
                this.game.println();
            }

            // player is dead
            if (!game.getPlayer().isAlive()) {
                this.game.println("YOU HAVE DIED!!");
                this.game.println("You fall to the ground where your bones are eventually gnawed on by a large fuzzy animal.");
                running = false;
            }
        }
    }

    // Attack logic...
    private boolean doAttack(String command) {

        boolean validCommand = false;
        Player player = game.getPlayer();
        int activeNPCCount = game.getCurrentLocation().getActiveCharacterCount();

        if (activeNPCCount > 0) {
            AbstractNPC npc = game.getCurrentLocation().getCharacters().get(0);
            if (command.equals(Action.KILL.getAbbreviation()) ||
                    command.equals(Action.ATTACK.getAbbreviation()) ||
                    command.equals(Action.KILL.name()) ||
                    command.equals(Action.ATTACK.name())) {

                // player attacks...
                int playerDamage = player.attackDamage();
                npc.receiveDamage(playerDamage);
                game.println("The " +
                        npc.getClass().getSimpleName().toLowerCase() +
                        " receives " +
                        playerDamage +
                        " hp damage.");

                // if npc is dead...
                if (npc.getHitPoints() <= 0) {

                    npc.setAlive(false);
                    npc.setHitPoints(0);
                    game.println("The " +
                            npc.getClass().getSimpleName().toLowerCase() +
                            " is dead!");

                } else { // player gets hit...

                    int npcDamage = npc.attackDamage();
                    player.receiveDamage(npcDamage);
                    game.println(npc.attackMessage());
                    game.println("You receive " + npcDamage + " hp damage.");

                    if (player.getHitPoints() >= 0) {
                        game.println("You survive the hit!");
                    } else {
                        player.setHitPoints(0);
                        player.setAlive(false);
                    }
                }

                game.showHpAll();
                validCommand = true;
            }
        }
        return validCommand;
    }

    // movement logic...
    private boolean doMove(String command) {

        boolean validCommand = false;
        List<Exit> exits = game.getCurrentLocation().getExits();
        for (Exit exit : exits) {
            if (exit.getDirectionName().equals(command) ||
                    exit.getShortDirectionName().equals(command)) {

                game.setCurrentLocation(exit.getLeadsTo());
                validCommand = true;

                break;
            }
        }
        return validCommand;
    }

    // when the player wants to quit...
    private boolean doQuit(String command) {

        boolean quit = false;
        if (command.equalsIgnoreCase("QUIT")) {
            this.game.println("Okay. Bye!");
            quit = true;
        }
        return quit;
    }
}
