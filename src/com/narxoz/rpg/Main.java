package com.narxoz.rpg;

import com.narxoz.rpg.adapter.Combatant;
import com.narxoz.rpg.adapter.EnemyCombatantAdapter;
import com.narxoz.rpg.adapter.HeroCombatantAdapter;
import com.narxoz.rpg.battle.BattleEngine;
import com.narxoz.rpg.battle.EncounterResult;
import com.narxoz.rpg.enemy.DragonBoss;
import com.narxoz.rpg.enemy.Goblin;
import com.narxoz.rpg.hero.Archer;
import com.narxoz.rpg.hero.Mage;
import com.narxoz.rpg.hero.Warrior;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== HW3: Battle Engine (Singleton + Adapter) ===\n");

        // 1) Singleton demo
        BattleEngine engine1 = BattleEngine.getInstance();
        BattleEngine engine2 = BattleEngine.getInstance();
        System.out.println("Singleton check: engine1 == engine2 ? " + (engine1 == engine2));
        System.out.println();

        // 2) Create heroes
        var warrior = new Warrior("Warrior", 220, 55);
        var mage = new Mage("Mage", 160, 70);
        var archer = new Archer("Archer", 180, 60);
        var goblin1 = new Goblin("Goblin", 140, 35);
        var goblin2 = new Goblin("Goblin Rogue", 120, 40);
        var dragon = new DragonBoss("Dragon Boss", 420, 85);
        // 3) Adapt them into Combatant
        List<Combatant> teamA = new ArrayList<>();
        teamA.add(new HeroCombatantAdapter(warrior));
        teamA.add(new HeroCombatantAdapter(mage));
        teamA.add(new HeroCombatantAdapter(archer));
        List<Combatant> teamB = new ArrayList<>();
        teamB.add(new EnemyCombatantAdapter(goblin1));
        teamB.add(new EnemyCombatantAdapter(goblin2));
        teamB.add(new EnemyCombatantAdapter(dragon));
        // 4) Run deterministic battle
        EncounterResult result = BattleEngine.getInstance()
                .setRandomSeed(2026L)
                .runEncounter(teamA, teamB);
        // 5) Print log
        for (String line : result.getBattleLog()) {
            System.out.println(line);
        }
        // 6) Summary
        System.out.println("\n" + result.summary());
        // 7) reset demo (optional)
        BattleEngine.getInstance().reset();
    }
}