public abstract class Weapon extends Item{
    int damage;
    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }
    public abstract boolean canUse();

    public void useWeapon() {

    }
}
