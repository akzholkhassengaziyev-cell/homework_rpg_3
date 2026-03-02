package com.narxoz.rpg.adapter;

import com.narxoz.rpg.enemy.Enemy;
public class EnemyCombatantAdapter implements Combatant {
    private final Enemy enemy;
    public EnemyCombatantAdapter(Enemy enemy) {
        if (enemy == null) throw new IllegalArgumentException("Enemy cannot be null");
        this.enemy = enemy;
    }
    @Override
    public String getName() {
        return enemy.getName();
    }
    @Override
    public int getAttackPower() {
        return enemy.getDamage();
    }
    @Override
    public void takeDamage(int damage) {
        enemy.applyDamage(damage);
    }
    @Override
    public boolean isAlive() {
        return enemy.isAlive();
    }
    public Enemy getEnemy() {
        return enemy;
    }
}