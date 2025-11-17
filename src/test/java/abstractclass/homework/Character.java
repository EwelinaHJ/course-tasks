package abstractclass.homework;

public abstract class Character implements Creature{

    protected int health;
    protected int strength;

    public Character(int health, int strength) {
        this.health = health;
        this.strength = strength;
    }

    public abstract void takeDamage();

    public abstract void heal();



    @Override
    public int getHealth() {
        return health;
    }
}