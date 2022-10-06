public class RangedWeapon extends Weapon{
    private int remainingUses;

    public RangedWeapon(String name, int damage, int remainingUses) {
        super(name, damage);
        this.remainingUses = remainingUses;
    }
    public void setRemainingUses(int newAmount) {
        this.remainingUses = newAmount;
    }
    public boolean canUse() {
        if (remainingUses > 0) {
            setRemainingUses(this.remainingUses-1);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public void useWeapon() {
        setRemainingUses(this.remainingUses - 1);
    }
}