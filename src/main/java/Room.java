import java.util.ArrayList;

public class Room {
    private int roomNumber;
    private int[] access;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.access = getAccess(this.roomNumber);
    }

    public String toString() {
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
        return "You are in room " + this.roomNumber + ", and you can now go " + access;
    }

    public int getRoomNumber() {
        return this.roomNumber;
    }

    public int[] getAccess() {
        return this.access;
    }

    public void setRoomNumber(int newRoomNumber) {
        this.roomNumber = newRoomNumber;
        this.access = getAccess(newRoomNumber);
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

    public int[] getAccess(int roomNumber) {
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
