import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Adventure adventure = new Adventure();

    public void runUi() {
        //Introduction
        System.out.print("Welcome to ");
        System.out.println("\033[0;1m" + "Find The virus" + "\u001B[0m");
        System.out.println("You are infected by Covid");
        System.out.println("You need to find room 5 where the vaccine is");


        //Player
        Player player = adventure.getPlayer();

        //Start of the game
        do {
            //show open ways
            System.out.println(adventure.getCurrentRoom());

            //making a move
            getInput();

            //check if out of health
            if (player.getHealthPoints() <= 0) {
                System.out.println("GAME OVER - You died!");
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
                    ItemResults result = adventure.takeItem(input);
                    if (result == ItemResults.ITEM_TAKEN) {
                        System.out.println("\u001B[32m" + "You have taken '" + input.split(" ")[1] + "'" + "\u001B[0m");
                    } else if (result == ItemResults.DOES_NOT_EXIST_IN_ROOM) {
                        System.out.println("\u001B[32m" + "The room does not contain '" + input.split(" ")[1] + "'" + "\u001B[0m");
                    } else if (result == ItemResults.INVALID_COMMAND_TAKE) {
                        System.out.println("Please enter an item after '" + "take'");
                    }
                    loop = false;
                }
                case "drop" -> {
                    ItemResults result = adventure.dropItem(input);
                    if (result == ItemResults.ITEM_DROPPED) {
                        System.out.println("\u001B[32m" + "You have dropped '" + input.split(" ")[1] + "'" + "\u001B[0m");
                    } else if (result == ItemResults.DOES_NOT_EXIST_IN_INVENTORY) {
                        System.out.println("\u001B[32m" + "There is no such item in your inventory" + "\u001B[0m");
                    } else if (result == ItemResults.INVALID_COMMAND_DROP) {
                        System.out.println("Please enter an item after '" + "drop'");
                    }
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
                    loop = false;
                }
                case "equip" -> {
                    System.out.println(adventure.equip(input));
                    loop = false;
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
