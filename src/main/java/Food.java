public class Food extends Item{
    int healthPoints;

    public Food(String name, int healthPoints) {
        super(name);
        this.healthPoints = healthPoints;
    }
    public String toString() {
        return super.getName();
    }
}
