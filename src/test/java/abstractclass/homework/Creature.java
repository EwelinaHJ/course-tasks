package abstractclass.homework;

public interface Creature {

    void move();
    void attack();
    void defend();

    default boolean isAlive() {
        return getHealth() > 0;
    }


    int getHealth();
}

