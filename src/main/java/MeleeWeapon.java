public class MeleeWeapon extends Weapon{
    public MeleeWeapon(String name, int damage) {
        super(name, damage);
    }
    public boolean canUse() {
        return true;
    }
}
