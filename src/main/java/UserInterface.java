import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Adventure adventure = new Adventure();

    public void runUi() {
        //Introduction
        System.out.println("Welcome to the room game!");

        //Player
        Player player = adventure.getPlayer();

        //Start of the game
        do {
            //show open ways
            System.out.println(adventure.getCurrentRoom());

            //making a move
            getInput();

            //check if out of health
            if (player.getHealth() == 0) {
                System.out.println("GAME OVER - You starved to death!");
                break;
            }

            //check if in final room
            if (adventure.getCurrentRoom().getRoomNumber() == 5) {
                System.out.println("Congratulations! You found the room!");
                break;
            }
        } while (true);
    }

    public void getInput() {
        boolean loop = true;
        do {
            System.out.print("\nWrite a command: ");
            String input = scanner.nextLine().toLowerCase();
            input = input.trim();
            switch (input.split(" ")[0]) {
                case "inventory" -> {
                    System.out.println(adventure.getPlayer().getInventoryList());
                    loop = false;
                }
                case "take" -> {
                    System.out.println(adventure.takeItem(input));
                    loop = false;
                }
                case "drop" -> {
                    System.out.println(adventure.dropItem(input));
                    loop = false;
                }
                case "health" -> {
                    System.out.println(adventure.showHealth());
                    loop = false;
                }
                case "eat" -> {
                    System.out.println(adventure.eat(input));
                    loop = false;
                }
                case "attack" -> {
                    System.out.println(adventure.attack(input));
                }
                case "equip" -> {
                    System.out.println(adventure.equip(input));
                }
                case "north", "west", "east", "south" -> {
                    System.out.println(adventure.moveToNewRoom(input));
                    loop = false;
                }
                default -> {
                    System.out.println("We do not understand what you mean by '" + input + "'");
                    System.out.println("List of commands:");
                    System.out.println("'north'\n'east'\n'south'\n'west'");
                    System.out.println("'take x'\n'drop x'\n'eat x'\n'inventory'\n'health'\n");
                }
            }
        } while (loop);
    }
}
