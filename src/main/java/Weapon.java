public class Weapon extends Item{
    int damage;
    public Weapon(String name, int damage) {
        super(name);
        this.damage = damage;
    }
    public String toString() {
        return super.getName();
    }
}
