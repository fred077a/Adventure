import java.util.Objects;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Adventure adventure = new Adventure();

    public void startGame() {
        //Introduction
        System.out.println("Welcome to the room game!");
        System.out.println("You can use the following commands:");
        System.out.println("north - east - south - west");
        System.out.println("take - drop - inventory - eat - health\n");

        //Start of the game
        do {
            //show open ways
            System.out.println(adventure.getPlayerRoom());

            //making a move
            makeMove();
        } while (adventure.getPlayerRoom().getRoomNumber() != 5);
        System.out.println("Congratulations! You found the room!");
    }

    public void makeMove() {
        Room currentRoom = adventure.getPlayerRoom();
        int currentRoomNumber = currentRoom.getRoomNumber();
        int[] accessTo = currentRoom.getAccess();
        int newRoom;
        boolean loop = true;
        do {
            System.out.print("\nWrite a command: ");
            String input = scanner.nextLine().toLowerCase();
            input = input.trim();
            if (Objects.equals(input.split(" ")[0], "inventory")) {
                System.out.println(adventure.getPlayer().getInventoryList());
            } else if (Objects.equals(input.split(" ")[0], "take")) {
                try {
                    System.out.println(adventure.takeItem(input.split(" ")[1]));
                } catch (Exception ex) {
                    System.out.println("Please enter an item after '" + "take'");
                }
            } else if (Objects.equals(input.split(" ")[0], "drop")) {
                try {
                    System.out.println(adventure.dropItem(input.split(" ")[1]));
                } catch (Exception ex) {
                    System.out.println("Please enter an item after '" + "drop'");
                }
            } else {
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
                        loop = false;
                        break;
                    } else if (i == accessTo.length-1) {
                        System.out.println("Sorry, that is not possible! - Try again!");
                        break;
                    }
                }
            }
        } while (loop);
    }
}
