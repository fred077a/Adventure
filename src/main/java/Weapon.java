public abstract class Weapon extends Item{
    private int damage;
    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }
    public abstract boolean canUse();

    public void useWeapon() {}

    public int getDamage() {
        return damage;
    }
}
