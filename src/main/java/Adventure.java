public class Adventure {
    private Player player = new Player(1);
    private Map map = new Map();

    public Player getPlayer() {
        return player;
    }

    public Room getCurrentRoom() {
        return map.getRoom(player.getRoomNumber());
    }

    public String moveToNewRoom(String input) {
        Room currentRoom = getCurrentRoom();
        int currentRoomNumber = currentRoom.getRoomNumber();
        int[] accessTo = currentRoom.getAccess();
        int newRoom;
        switch (input.split(" ")[0]) {
            case "north" -> newRoom = currentRoomNumber - 3;
            case "east" -> newRoom = currentRoomNumber + 1;
            case "south" -> newRoom = currentRoomNumber + 3;
            case "west" -> newRoom = currentRoomNumber - 1;
            default -> newRoom = currentRoomNumber;
        }
        //check if the direction is possible
        String result = "";
        for (int i = 0; i < accessTo.length; i++) {
            if (newRoom == accessTo[i]) {
                this.player.setHealth(this.player.getHealth() - 1);
                this.player.setRoomNumber(newRoom);
                result = "You are going to room " + newRoom;
                break;
            } else if (i == accessTo.length-1) {
                result = "Sorry, you cannot go " + input.split(" ")[0] + ".";
                break;
            }
        }
        return result;
    }

    public String eat(String input) {
        try {
            Player player = getPlayer();
            return player.eat(input.split(" ")[1]);
        } catch (Exception ex) {
            return "Please enter a weapon after '\" + \"equip'";
        }
    }

    public String attack(String input) {
        try {
            if (player.isWeaponEquipped()) {
                Weapon weapon = player.getWeaponEquipped();
                weapon.useWeapon();

                return "placeholder";
            } else {
                return "You do not have a weapon equipped";
            }
        } catch (Exception ex) {
            return "Please enter an enemy after '" + "attack'";
        }
    }

    public String equip(String input) {
        try {
            input = input.split(" ")[1];
            Weapon weapon = (Weapon) player.findItem(input);
            player.setWeaponEquipped(weapon);
            return "You have equipped " + weapon;
        } catch (Exception ex) {
            if (input.split(" ").length == 1) {
                return "Please enter something after 'equip'";
            } else {
                return "No such weapon in inventory";
            }
        }
    }

    public String dropItem(String itemName) {
        try {
            itemName = itemName.split(" ")[1];
            int currentRoomNumber = player.getRoomNumber();
            Object removedItem = player.removeItem(itemName);
            if (removedItem != null) {
                map.getRoom(currentRoomNumber).addItem(removedItem);
                return "\u001B[32m" + "You have dropped '" + itemName + "'" + "\u001B[0m";
            } else return "\u001B[32m" + "There is no such item in your inventory" + "\u001B[0m";
        } catch (Exception ex) {
            return "Please enter an item after '" + "drop'";
        }
    }

    public String showHealth() {
        int healthPoints = player.getHealth();
        final String green = "\u001B[32m";
        final String yellow = "\u001B[33m";
        final String red = "\u001B[31m";
        final String blue = "\u001B[34m";
        final String resetText = "\u001B[0m";
        if (healthPoints >= 10) {
            return blue + "You have " + healthPoints + " HP - You are doing fantastic!" + resetText;
        } else if (healthPoints >= 6) {
            return green + "You have " + healthPoints + " HP - You are doing okay." + resetText;
        } else if (healthPoints >= 3) {
            return yellow + "You have " + healthPoints + " HP - You are not doing your best!" + resetText;
        } else {
            return red + "You have only " + healthPoints + " HP - You need to eat!" + resetText;
        }
    }

    public String takeItem(String itemName) {
        try {
            itemName = itemName.split(" ")[1];
            int currentRoomNumber = player.getRoomNumber();
            Object newItem = map.getRoom(currentRoomNumber).removeItem(itemName);
            if (newItem != null) {
                player.addItem(newItem);
                return "\u001B[32m" + "You have taken '" + itemName + "'" + "\u001B[0m";
            } else {
                return "\u001B[32m" + "The room does not contain '" + itemName + "'" + "\u001B[0m";
            }
        } catch (Exception ex) {
            return "Please enter an item after '" + "take'";
        }
    }
}
