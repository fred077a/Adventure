import java.util.ArrayList;

public class Player {
     private int currentRoomNumber;
     private ArrayList<Item> items;
     private ArrayList<Food> foods;
     private ArrayList<Weapon> weapons;
     private int health = 10;

     public Player(int roomNumber) {
         this.currentRoomNumber = roomNumber;
         this.items = new ArrayList<>();
         this.foods = new ArrayList<>();
         this.weapons = new ArrayList<>();
     }

     public int getRoomNumber() {
         return this.currentRoomNumber;
     }

     public int getHealth() {
         return this.health;
     }

     public void setHealth(int newHealthPoints) {
         this.health = newHealthPoints;
     }

     public String getInventoryList() {
        String title = "My inventory:";
        String inventoryList = "";
        for (Item item: items) {
            inventoryList = inventoryList + "\n" + "\u001B[32m" + item + "\u001B[0m";
        }
        for (Food food: foods) {
             inventoryList = inventoryList + "\n" + "\u001B[32m" + food + "\u001B[0m";
        }
        for (Weapon weapon: weapons) {
             inventoryList = inventoryList + "\n" + "\u001B[32m" + weapon + "\u001B[0m";
        }
        if (inventoryList.equals("")) {
            return "You have no items";
        } else {
            return title + inventoryList;
        }
    }

     public void addItem(Object item) {
         if (item.getClass().toString().equals("class Food")) {
             this.foods.add((Food) item);
         } else if (item.getClass().toString().equals("class Weapon")) {
             this.weapons.add((Weapon) item);
         } else {
             this.items.add((Item) item);
         }
     }

     public Item findItem(String itemName) {
         Item itemFound = null;
         for (Item item: items) {
             if (item.getName().toLowerCase().equals(itemName)) {
                 itemFound = item;
             }
         }
         return itemFound;
     }

    public Food findFood(String itemName) {
        Food itemFound = null;
        for (Food f: foods) {
            if (f.getName().toLowerCase().equals(itemName)) {
                itemFound = f;
            }
        }
        return itemFound;
    }

     public String eat(String foodName) {
         Food f = findFood(foodName);
         if (f != null) {
             setHealth(health + f.healthPoints);
             foods.remove(f);
             return "You ate '" + foodName + "' and you now have " + health + " HP!";
         } else {
             return "You do not have '" + foodName + "' to eat.";
         }
     }

     public Item removeItem(String itemName) {
         try {
             int position = this.items.indexOf(findItem(itemName));
             if (position != -1) {
                 Item newItem = this.items.get(position);
                 this.items.remove(position);
                 return newItem;
             } else {
                 return null;
             }
         } catch (Exception ex) {
             return null;
         }
     }

     public void setRoomNumber(int newRoomNumber) {
         this.currentRoomNumber = newRoomNumber;
     }

}
