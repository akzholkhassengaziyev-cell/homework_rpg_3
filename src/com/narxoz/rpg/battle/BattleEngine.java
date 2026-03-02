package com.narxoz.rpg.battle;
import com.narxoz.rpg.adapter.Combatant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BattleEngine {
    private static BattleEngine instance;
    private Random rng = new Random(1L);
    private long seed = 1L;
    private BattleEngine() {}

    public static BattleEngine getInstance() {
        if (instance == null) {
            instance = new BattleEngine();
        }
        return instance;
    }
    public BattleEngine setRandomSeed(long seed) {
        this.seed = seed;
        this.rng = new Random(seed);
        return this;
    }
    public void reset() {
        this.seed = 1L;
        this.rng = new Random(1L);
    }

    public EncounterResult runEncounter(List<Combatant> teamA, List<Combatant> teamB) {
        if (teamA == null || teamB == null) throw new IllegalArgumentException("Teams cannot be null");
        List<Combatant> a = new ArrayList<>(teamA);
        List<Combatant> b = new ArrayList<>(teamB);
        removeDead(a);
        removeDead(b);

        List<String> log = new ArrayList<>();
        log.add("=== Encounter started (seed=" + seed + ") ===");
        log.add("Team A size=" + a.size() + ", Team B size=" + b.size());

        int round = 0;
        while (!a.isEmpty() && !b.isEmpty()) {
            round++;
            log.add("\n-- Round " + round + " --");
            // A attacks B
            performTeamAttacks("A", a, "B", b, log);
            removeDead(b);
            if (b.isEmpty()) break;
            // B attacks A
            performTeamAttacks("B", b, "A", a, log);
            removeDead(a);
        }

        String winner = a.isEmpty() ? "Team B" : "Team A";
        log.add("\n=== Encounter ended. " + winner + " wins! ===");
        return new EncounterResult(winner, round, log);
    }

    private void performTeamAttacks(String atkLabel,
                                    List<Combatant> attackers,
                                    String defLabel,
                                    List<Combatant> defenders,
                                    List<String> log) {

        if (defenders.isEmpty()) return;
        for (Combatant attacker : attackers) {
            if (!attacker.isAlive()) continue;
            if (defenders.isEmpty()) break;
            Combatant target = pickTarget(defenders);
            int base = attacker.getAttackPower();
            int dealt = computeDamage(base);

            target.takeDamage(dealt);
            log.add("Team " + atkLabel + ": " + attacker.getName()
                    + " hits " + defLabel + ":" + target.getName()
                    + " for " + dealt + " dmg");

            if (!target.isAlive()) {
                log.add(">>> " + defLabel + ":" + target.getName() + " died!");
            }
        }
    }

    private Combatant pickTarget(List<Combatant> defenders) {
        int idx = rng.nextInt(defenders.size());
        return defenders.get(idx);
    }

    private int computeDamage(int base) {
        // Small deterministic randomness: 80%..120%
        double mult = 0.8 + (rng.nextDouble() * 0.4);
        int dmg = (int) Math.round(base * mult);
        return Math.max(0, dmg);
    }
    private void removeDead(List<Combatant> team) {
        for (Iterator<Combatant> it = team.iterator(); it.hasNext();) {
            Combatant c = it.next();
            if (!c.isAlive()) it.remove();
        }
    }
}