import org.w3c.dom.ranges.Range;

import java.util.ArrayList;

public class Map {
    private ArrayList<Room> rooms = new ArrayList<>();

    public Map() {
        for (int i = 0; i < 9; i++) {
            int roomNumber = i+1;
            this.rooms.add(new Room(roomNumber, setItems(roomNumber), getAccessValues(roomNumber), setEnemies(roomNumber)));
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
            case 1 -> {
                items.add(new Food("Apple", 4));
                items.add(new MeleeWeapon("Virus", 2));
                return items;
            }
            case 2 -> {
                items.add(new MeleeWeapon("Syringe", 3));
                items.add(new Food("Chocolate", 2));
                return items;
            }
            case 3 -> {
                items.add(new Food("burger", 6));
                return items;
            }
            case 4 -> {
                items.add(new Item("Thermometer"));
                items.add(new RangedWeapon("Taser", 4, 1));
                return items;
            }
            case 6 -> {
                items.add(new Food("Bread", 5));
                return items;
            }
            case 7 -> {
                items.add(new MeleeWeapon("Knife", 4));
                return items;
            }
            case 8 -> {
                items.add(new Food("Crumbs", 1));
                return items;
            }
            case 9 -> {
                items.add(new RangedWeapon("Gun", 6, 8) {
                });
                return items;
            }
            default -> {
                return items;
            }
        }
    }
    public ArrayList<Enemy> setEnemies(int roomNumber) {
        ArrayList<Enemy> enemies = new ArrayList(){};
        switch (roomNumber) {
            case 2, 4, 9 -> {
                enemies.add(new Enemy("Nurse", new MeleeWeapon("Syringe", 4), 4));
                return enemies;
            }
            case 8 -> {
                enemies.add(new Enemy("Doctor", new MeleeWeapon("Defibrillator", 4), 10));
                enemies.add(new Enemy("Nurse", new RangedWeapon("Taser", 2, 2), 4));
                return enemies;
            }
            default -> {
                return enemies;
            }
        }
    }
}
