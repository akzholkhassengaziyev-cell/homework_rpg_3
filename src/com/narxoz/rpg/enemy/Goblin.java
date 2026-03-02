package com.narxoz.rpg.enemy;
public class Goblin implements Enemy {
    private final String name;
    private int hp;
    private final int damage;

    public Goblin(String name, int hp, int damage) {
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }
    @Override public String getName() { return name; }
    @Override public int getDamage() { return damage; }

    @Override
    public void applyDamage(int dmg) {
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