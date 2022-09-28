import java.util.Scanner;
public class UserInterface {
    public void showAccess(int currentRoom) {
        Room room = new Room(currentRoom);
        System.out.println(room);
    }

    public int move(int currentRoom, int[] access) {
        int newRoom;
        do {
            System.out.print("Where do you want to go? ");
            String input = new Scanner(System.in).next().toLowerCase();
            switch (input.trim().split(" ")[0]) {
                case "north" -> {
                    newRoom = currentRoom - 3;
                }
                case "east" -> {
                    newRoom = currentRoom + 1;
                }
                case "south" -> {
                    newRoom = currentRoom + 3;
                }
                case "west" -> {
                    newRoom = currentRoom - 1;
                }
                default -> {
                    newRoom = currentRoom;
                }
            }
            for (int i = 0; i < access.length; i++) {
                if (newRoom == access[i]) {
                    return newRoom;
                } else if (i == access.length-1) {
                    System.out.println("Sorry, you cannot go that way!");
                    break;
                }
            }
        } while (true);
    }
}
