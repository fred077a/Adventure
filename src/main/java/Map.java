import java.util.ArrayList;

public class Map {
    private ArrayList<Room> rooms = new ArrayList<>();

    public Map() {
        for (int i = 0; i < 9; i++) {
            this.rooms.add(new Room(i+1));
        }
    }

    public Room getRoom(int roomNumber) {
        return this.rooms.get(roomNumber-1);
    }
}
