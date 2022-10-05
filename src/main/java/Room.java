import java.util.ArrayList;
import java.util.Objects;

public class Room {
    private final int roomNumber;
    private int[] access;
    private ArrayList<Item> items;
    private ArrayList<Food> foods;
    private ArrayList<Weapon> weapons;

    public Room(int roomNumber, ArrayList items, int[] access, ArrayList foods, ArrayList weapons) {
        this.roomNumber = roomNumber;
        this.access = access;
        this.items = items;
        this.foods = foods;
        this.weapons = weapons;
    }

    public String toString() {
        String itemList = getItemList();
        StringBuilder access = new StringBuilder();
        for (int i = 0; i < this.access.length; i++) {
            String direction = "\u001B[34m" + getDirection(this.roomNumber, this.access[i]) + "\u001B[0m";
            if (i == this.access.length-2) {
                access.append(direction).append(" or ");
            } else if (i == this.access.length-1) {
                access.append(direction).append(".");
            } else {
                access.append(direction).append(", ");
            }
        }
        return "You are in room " + this.roomNumber + ", and you can now go " + access + "\n" + itemList;
    }

    public String getItemList() {
        String title = "\u001B[32m" + "There are items in this room:";
        String itemList = "";
        for (Item item: items) {
            itemList = itemList + "\n" + "\u001B[32m" + item + "\u001B[0m";
        }
        for (Food food: foods) {
            itemList = itemList + "\n" + "\u001B[32m" + food + "\u001B[0m";
        }
        for (Weapon weapon: weapons) {
            itemList = itemList + "\n" + "\u001B[32m" + weapon + "\u001B[0m";
        }
        if (itemList.equals("")) {
            return "There are no items in this room";
        } else {
            return title + itemList;
        }
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public int[] getAccess() {
        return this.access;
    }

    public String getDirection(int roomNumber, int toRoom) {
        if (toRoom == roomNumber-1) {
            return "west";
        } else if (toRoom == roomNumber-3) {
            return "north";
        } else if (toRoom == roomNumber+1) {
            return "east";
        } else {
            return "south";
        }
    }

    public Object findItem(String itemName) {
        Object itemFound = null;
        for (Item item: items) if (item.getName().toLowerCase().equals(itemName)) itemFound = item;
        for (Food food: foods) if (food.getName().toLowerCase().equals(itemName)) itemFound = food;
        for (Weapon weapon: weapons) if (weapon.getName().toLowerCase().equals(itemName)) itemFound = weapon;
        return itemFound;
    }

    public Object removeItem(String itemName) {
        try {
            Object item = findItem(itemName);
            if (item.getClass().toString().equals("class Food")) {
                this.foods.remove(item);
            } else if (item.getClass().toString().equals("class Weapon")) {
                this.weapons.remove(item);
            } else {
                this.items.remove(item);
            }
            return item;
        } catch (Exception ex) {
            return null;
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
}
