import java.util.ArrayList;

public class Player {
     private int currentRoomNumber;
     private ArrayList<Item> items;
     private int health = 10;
     private Weapon weaponEquipped = null;

     public Player(int roomNumber) {
         this.currentRoomNumber = roomNumber;
         this.items = new ArrayList<>();
     }

     public int getRoomNumber() {
         return this.currentRoomNumber;
     }

     public int getHealth() {
         return this.health;
     }

     public void setWeaponEquipped(Weapon weapon) {
         this.weaponEquipped = weapon;
     }
     public boolean isWeaponEquipped() {
         if (this.weaponEquipped != null) {
             return true;
         } else {
             return false;
         }
     }
     public Weapon getWeaponEquipped() {
         return weaponEquipped;
     }

     public void setHealth(int newHealthPoints) {
         this.health = newHealthPoints;
     }

     public String getInventoryList() {
        String title = "My inventory:";
        String inventoryList = "";
        for (Item item: items) inventoryList = inventoryList + "\n" + "\u001B[32m" + item + "\u001B[0m";
        if (inventoryList.equals("")) return "You have no items";
        else return title + inventoryList;
    }

     public void addItem(Object item) {
         this.items.add((Item) item);
     }

     public Object findItem(String itemName) {
         Object itemFound = null;
         for (Item item: items) {
             if (item.getName().toLowerCase().equals(itemName)) {
                 itemFound = item;
             }
         }
         return itemFound;
     }

    public Food findFood(String itemName) {
        try {
            Item itemFound = null;
            for (Item item: items) {
                if (item.getName().toLowerCase().equals(itemName)) {
                    itemFound = item;
                }
            }
            return (Food) itemFound;
        } catch (Exception ex) {
            return null;
        }
    }

     public String eat(String foodName) {
         Food f = findFood(foodName);
         if (f != null) {
             setHealth(health + f.getHealthPoints());
             items.remove((Item) f);
             return "You ate '" + foodName + "' and you now have " + health + " HP!";
         } else {
             return "You do not have '" + foodName + "' to eat.";
         }
     }

     public Object removeItem(String itemName) {
         try {
             Object item = findItem(itemName);
             this.items.remove(item);
             return item;
         } catch (Exception ex) {
             return null;
         }
     }

     public void setRoomNumber(int newRoomNumber) {
         this.currentRoomNumber = newRoomNumber;
     }

}
