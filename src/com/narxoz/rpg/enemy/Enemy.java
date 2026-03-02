package com.narxoz.rpg.enemy;

public interface Enemy {
    String getName();
    int getDamage();
    void applyDamage(int dmg);
    boolean isAlive();
}