import java.util.ArrayList;

public class Map {
    private ArrayList<Room> rooms = new ArrayList<>();

    public Map() {
        for (int i = 0; i < 9; i++) {
            int roomNumber = i+1;
            this.rooms.add(
                    new Room(
                            roomNumber,
                            setItems(roomNumber),
                            getAccessValues(roomNumber),
                            setFoods(roomNumber),
                            setWeapons(roomNumber))
            );
        }
    }

    public Room getRoom(int roomNumber) {
        return this.rooms.get(roomNumber-1);
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
                return new int[]{};
            }
        }
    }

    public ArrayList setItems(int roomNumber) {
        ArrayList items = new ArrayList(){};
        switch (roomNumber) {
            case 4 -> {
                items.add(new Item("Lighter"));
                return items;
            }
            default -> {
                return items;
            }
        }
    }
    public ArrayList setFoods(int roomNumber) {
        ArrayList items = new ArrayList(){};
        switch (roomNumber) {
            case 1 -> {
                items.add(new Food("Apple", 4));
                return items;
            }
            case 2 -> {
                items.add(new Food("Chocolate", 2));
                return items;
            }
            case 5 -> {
                items.add(new Food("Bread", 5));
                return items;
            }
            case 8 -> {
                items.add(new Food("Crumbs", 1));
                return items;
            }
            default -> {
                return items;
            }
        }
    }
    public ArrayList setWeapons(int roomNumber) {
        ArrayList items = new ArrayList(){};
        switch (roomNumber) {
            case 2 -> {
                items.add(new Weapon("Knife", 3));
                return items;
            }
            case 4 -> {
                items.add(new Weapon("Stone", 1));
                return items;
            }
            case 6 -> {
                items.add(new Weapon("Sword", 7));
                return items;
            }
            case 9 -> {
                items.add(new Weapon("Gun", 9));
                return items;
            }
            default -> {
                return items;
            }
        }
    }
}
