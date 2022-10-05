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

    public void showInventory() {
        System.out.println(adventure.getPlayer().getInventoryList());
    }

    public void take(String input) {
        try {
            System.out.println("\u001B[32m" + adventure.takeItem(input.split(" ")[1]) + "\u001B[0m");
        } catch (Exception ex) {
            System.out.println("Please enter an item after '" + "take'");
        }
    }

    public void drop(String input) {
        try {
            System.out.println("\u001B[32m" + adventure.dropItem(input.split(" ")[1]) + "\u001B[0m");
        } catch (Exception ex) {
            System.out.println("Please enter an item after '" + "drop'");
        }
    }

    public void showHealth() {
        Player player = adventure.getPlayer();
        int healthPoints = player.getHealth();
        final String green = "\u001B[32m";
        final String yellow = "\u001B[33m";
        final String red = "\u001B[31m";
        final String blue = "\u001B[34m";
        final String resetText = "\u001B[0m";
        if (healthPoints >= 10) {
            System.out.println(blue + "You have " + healthPoints + " HP - You are doing fantastic!" + resetText);
        } else if (healthPoints >= 6 && healthPoints <= 9) {
            System.out.println(green + "You have " + healthPoints + " HP - You are doing okay." + resetText);
        } else if (healthPoints >= 3 && healthPoints <= 5) {
            System.out.println(yellow + "You have " + healthPoints + " HP - You are not doing your best!" + resetText);
        } else if (healthPoints < 3) {
            System.out.println(red + "You have only " + healthPoints + " HP - You need to eat!" + resetText);
        }
    }

    public void eat(String input) {
        try {
            Player player = adventure.getPlayer();
            System.out.println(player.eat(input.split(" ")[1]));
        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println("Please enter an item after '" + "eat'");
        }
    }

    public void go(String input) {
        Room currentRoom = adventure.getCurrentRoom();
        int currentRoomNumber = currentRoom.getRoomNumber();
        int[] accessTo = currentRoom.getAccess();
        int newRoom;
        switch (input.split(" ")[0]) {
            case "north" -> newRoom = currentRoomNumber - 3;
            case "east" -> newRoom = currentRoomNumber + 1;
            case "south" -> newRoom = currentRoomNumber + 3;
            case "west" -> newRoom = currentRoomNumber - 1;
            default -> newRoom = currentRoomNumber;
        }
        //check if the direction is possible
        for (int i = 0; i < accessTo.length; i++) {
            if (newRoom == accessTo[i]) {
                adventure.moveToNewRoom(newRoom);
                break;
            } else if (i == accessTo.length-1) {
                System.out.println("Sorry, you cannot go " + input.split(" ")[0] + ".");
                break;
            }
        }
    }

    public void getInput() {
        boolean loop = true;
        do {
            System.out.print("\nWrite a command: ");
            String input = scanner.nextLine().toLowerCase();
            input = input.trim();
            if (Objects.equals(input.split(" ")[0], "inventory")) {
                showInventory();
                break;
            } else if (Objects.equals(input.split(" ")[0], "take")) {
                take(input);
                break;
            } else if (Objects.equals(input.split(" ")[0], "drop")) {
                drop(input);
                break;
            } else if (Objects.equals(input.split(" ")[0], "health")) {
                showHealth();
                break;
            } else if (Objects.equals(input.split(" ")[0], "eat")) {
                eat(input);
                break;
            } else if (
                    Objects.equals(input.split(" ")[0], "north") ||
                            Objects.equals(input.split(" ")[0], "east") ||
                    Objects.equals(input.split(" ")[0], "south") ||
                            Objects.equals(input.split(" ")[0], "west")
            ) {
                go(input);
                break;
            } else {
                System.out.println("We do not understand what you mean by '" + input + "'");
                System.out.println("List of commands:");
                System.out.println("'north'\n'east'\n'south'\n'west'");
                System.out.println("'take x'\n'drop x'\n'eat x'\n'inventory'\n'health'\n");
            }
        } while (loop);
    }
}
