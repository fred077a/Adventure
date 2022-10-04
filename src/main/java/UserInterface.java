import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private Adventure adventure = new Adventure();

    public void startGame() {
        //Introduction
        System.out.println("Welcome to the room game!");

        //Start of the game
        do {
            Room currentRoom = adventure.getPlayer().getRoom();

            //show open ways
            System.out.println(currentRoom);

            //making a move
            moveToNewRoom(currentRoom);

            //check if room number is the final room
            if (adventure.getPlayer().getRoom().getRoomNumber() == 5)
                System.out.println("Congratulations! You found the room!");
        } while (adventure.getPlayer().getRoom().getRoomNumber() != 5);
    }

    public void moveToNewRoom(Room currentRoom) {
        int currentRoomNumber = currentRoom.getRoomNumber();
        int[] accessTo = currentRoom.getAccess();
        int newRoom;
        boolean loop = true;
        do {
            System.out.print("Where do you want to go now? ");
            String input = scanner.next().toLowerCase();
            switch (input.trim().split(" ")[0]) {
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
                    System.out.println("Sorry, you cannot go that way!");
                    break;
                }
            }
        } while (loop);
    }
}
