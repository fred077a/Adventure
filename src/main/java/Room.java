import java.util.ArrayList;
import java.util.Objects;

public class Room {
    private final int roomNumber;
    private int[] access;
    private ArrayList<String> items;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.access = getAccessValues(roomNumber);
        this.items = getItemsValues(roomNumber);
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
        String title = "Items in this room:";
        String itemList = "";
        for (String item: items) {
            itemList = itemList + "\n" + "\u001B[32m" + item + "\u001B[0m";
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

    public ArrayList<String> getItemsValues(int roomNumber) {
        ArrayList<String> items = new ArrayList<String>(){};
        switch (roomNumber) {
            case 1 -> {
                items.add("rope");
                return items;
            }
            case 2 -> {
                items.add("dust");
                items.add("lighter");
                return items;
            }
            /*case 3 -> {
                return items;
            }*/
            case 4 -> {
                items.add("stones");
                return items;
            }
            case 5 -> {
                items.add("gold");
                return items;
            }
            case 6 -> {
                items.add("sword");
                return items;
            }
            /*case 7 -> {
                return items;
            }*/
            case 8 -> {
                items.add("keys");
                return items;
            }
            case 9 -> {
                items.add("knife");
                return items;
            }
            default -> {
                return items;
            }
        }
    }

    public boolean removeItem(String item) {
        int position = this.items.indexOf(item);
        if (position != -1) {
            this.items.remove(item);
            return true;
        } else {
            return false;
        }
    }
    public void addItem(String item) {
        this.items.add(item);
    }

    public int[] getAccessValues(int roomNumber) {
        switch (roomNumber) {
            case 1 -> {
                return new int[]{2, 4};
            }
            case 2 -> {
                return new int[]{1, 3};
            }
            case 3 -> {
                return new int[]{2, 6};
            }
            case 4 -> {
                return new int[]{1, 7};
            }
            case 5 -> {
                return new int[]{2, 4, 6, 8};
            }
            case 6 -> {
                return new int[]{3, 9};
            }
            case 7 -> {
                return new int[]{4, 8};
            }
            case 8 -> {
                return new int[]{7, 5, 9};
            }
            case 9 -> {
                return new int[]{8, 6};
            }
            default -> {
                return new int[]{this.roomNumber};
            }
        }
    }
}
