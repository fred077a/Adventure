
public class Adventure {
    private Player player = new Player(1);
    private Map map = new Map();

    public Player getPlayer() {
        return player;
    }

    public Room getPlayerRoom() {
        return map.getRoom(player.getRoomNumber());
    }

    public void moveToNewRoom(int newRoomNumber) {
        this.player.setRoomNumber(newRoomNumber);
    }

    public String dropItem(String item) {
        int currentRoomNumber = player.getRoomNumber();
        boolean removed = player.removeItem(item);
        if (removed) {
            map.getRoom(currentRoomNumber).addItem(item);
            return "You have dropped '" + item + "'";
        } else return "There is no such item in your inventory";
    }

    public String takeItem(String item) {
        int currentRoomNumber = player.getRoomNumber();
        boolean removed = map.getRoom(currentRoomNumber).removeItem(item);
        if (removed) {
            player.addItem(item);
            return "You have taken '" + item + "'";
        } else {
            return "The room does not contain '" + item + "'";
        }
    }
}
