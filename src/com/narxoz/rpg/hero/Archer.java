package com.narxoz.rpg.hero;

public class Archer implements Hero {
    private final String name;
    private int hp;
    private final int power;

    public Archer(String name, int hp, int power) {
        this.name = name;
        this.hp = hp;
        this.power = power;
    }
    @Override public String getName() { return name; }
    @Override public int getPower() { return power; }

    @Override
    public void receiveDamage(int dmg) {
        if (dmg < 0) dmg = 0;
        hp -= dmg;
        if (hp < 0) hp = 0;
    }
    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    public int getHp() { return hp; }
}