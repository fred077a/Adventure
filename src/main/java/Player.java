import java.util.ArrayList;

public class Player {
     private int currentRoomNumber;
     private ArrayList<String> inventory;

     public Player(int roomNumber) {
         this.currentRoomNumber = 1;
         this.inventory = new ArrayList<>();
     }

     public int getRoomNumber() {
         return this.currentRoomNumber;
     }

    public String getInventoryList() {
        String title = "My inventory:";
        String inventoryList = "";
        for (String item: inventory) {
            inventoryList = inventoryList + "\n" + "\u001B[32m" + item + "\u001B[0m";
        }
        if (inventoryList.equals("")) {
            return "You have no items";
        } else {
            return title + inventoryList;
        }
    }

     public void addItem(String item) {
         this.inventory.add(item);
     }

     public boolean removeItem(String item) {
         int position = this.inventory.indexOf(item);
         if (position != -1) {
             this.inventory.remove(item);
             return true;
         } else return false;

     }

     public void setRoomNumber(int newRoomNumber) {
         this.currentRoomNumber = newRoomNumber;
     }

}
