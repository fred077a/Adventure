public class Adventure {
    public void run() {
        //Introduction
        System.out.println("Welcome to the room game!");

        //Start
        Room currentRoom = new Room(1);
        UserInterface ui = new UserInterface();
        do {
            ui.showAccess(currentRoom.getRoomNumber());
            currentRoom.setRoomNumber(ui.move(currentRoom.getRoomNumber(), currentRoom.getAccess()));
            if (currentRoom.getRoomNumber() == 5) {
                System.out.println("Congratulations! You found the hidden room!");
            }
        } while (currentRoom.getRoomNumber() != 5);
    }
}
