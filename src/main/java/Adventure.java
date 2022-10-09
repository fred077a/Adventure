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
                this.player.setHealth(this.player.getHealth() - currentRoom.getTotalEnemyDamage());
                showHealth();
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
        int inputs = input.split(" ").length;
        if (player.isWeaponEquipped()) {
            try {
                Weapon weapon = player.getWeaponEquipped();
                if (weapon.canUse()) {
                    weapon.useWeapon();

                    //player info
                    int playerHealth = player.getHealth();
                    int playerDamage = weapon.getDamage();

                    //room & enemy info
                    Room room = map.getRoom(player.getRoomNumber());
                    String enemyName;
                    Enemy enemy;
                    if (inputs != 1) {
                        enemyName = input.split(" ")[1];
                        enemy = room.findEnemy(input.split(" ")[1]);
                    } else {
                        enemy = room.getRandomEnemy();
                        enemyName = enemy.getName();
                    }
                    Weapon enemyWeapon = enemy.getWeapon();
                    int enemyWeaponDamage = enemyWeapon.getDamage();

                    //attack
                    int enemyHealthAfter = enemy.attacked(playerDamage);
                    if (enemyHealthAfter <= 0) {
                        room.removeEnemy(enemy);
                        return "You defeated " + enemyName + " and you now have " + player.getHealth() + " HP";
                    } else {
                        if (!weapon.getClass().toString().equals("class RangedWeapon")) {
                            player.setHealth(playerHealth - enemyWeaponDamage);
                        }
                        return "You attacked " + enemyName + "(" + enemyHealthAfter + " HP), and you now have " + player.getHealth() + " HP";
                    }
                } else {
                    dropItem(weapon.getName());
                    player.setWeaponEquipped(null);
                    return "Your weapon does not work anymore, and it has been dropped!";
                }
            } catch (Exception ex) {
                System.out.println(ex);
                return "No such enemy";
            }
        } else {
            return "You need to equip a weapon";
        }
    }

    public String equip(String input) {
        try {
            input = input.split(" ")[1];
            Weapon weapon = (Weapon) player.findItem(input);
            player.setWeaponEquipped(weapon);
            return "You have equipped " + weapon;
        } catch (Exception ex) {
            System.out.println(input);
            return "No such weapon in inventory";
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
