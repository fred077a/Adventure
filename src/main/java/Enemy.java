public class Enemy {
    private String name;
    private int healthPoints;
    private Weapon weapon;

    public String toString() {
        return this.name + " - " + this.healthPoints + " HP";
    }

    public Enemy(String name, Weapon weapon, int healthPoints) {
        this.name = name;
        this.weapon = weapon;
        this.healthPoints = healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int attacked(int healthPoints) {
        setHealthPoints(getHealthPoints() - healthPoints);
        return this.healthPoints;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public Weapon getWeapon() {
        return this.weapon;
    }

    public String getName () {
        return this.name;
    }
}
