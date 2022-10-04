import java.util.Scanner;
public class Adventure {
    private Player player = new Player(1);

    public Player getPlayer() {
        return player;
    }
    public void moveToNewRoom(int newRoomNumber) {
        this.player.getRoom().setRoomNumber(newRoomNumber);
    }
}
