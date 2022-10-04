import java.util.ArrayList;

public class Player {
     Room room;
     ArrayList<String> inventory;

     public Player(int roomNumber) {
         this.room = new Room(roomNumber);
         this.inventory = new ArrayList<>();
     }

     public Room getRoom() {
         return this.room;
     }

}
