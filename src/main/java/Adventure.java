import java.util.Objects;

public class Adventure {
    private Player player = new Player(1);
    private Map map = new Map();

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return map.getRoom(player.getRoomNumber());
    }

    public void moveToNewRoom(int newRoomNumber) {
        this.player.setHealth(this.player.getHealth() - 1);
        this.player.setRoomNumber(newRoomNumber);
    }

    public String dropItem(String itemName) {
        int currentRoomNumber = player.getRoomNumber();
        Object removedItem = player.removeItem(itemName);
        if (removedItem != null) {
            map.getRoom(currentRoomNumber).addItem(removedItem);
            return "You have dropped '" + itemName + "'";
        } else return "There is no such item in your inventory";
    }

    public String takeItem(String itemName) {
        int currentRoomNumber = player.getRoomNumber();
        Object newItem = map.getRoom(currentRoomNumber).removeItem(itemName);
        if (newItem != null) {
            player.addItem(newItem);
            return "You have taken '" + itemName + "'";
        } else {
            return "The room does not contain '" + itemName + "'";
        }
    }
}
