package com.narxoz.rpg.adapter;

public interface Combatant {
    String getName();
    int getAttackPower();
    void takeDamage(int damage);
    boolean isAlive();
}
