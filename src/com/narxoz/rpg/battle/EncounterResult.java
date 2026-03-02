package com.narxoz.rpg.battle;

import java.util.List;

public class EncounterResult {
    private final String winner;           // "Team A" or "Team B"
    private final int rounds;
    private final List<String> battleLog;  // readable output

    public EncounterResult(String winner, int rounds, List<String> battleLog) {
        this.winner = winner;
        this.rounds = rounds;
        this.battleLog = battleLog;
    }

    public String getWinner() { return winner; }
    public int getRounds() { return rounds; }
    public List<String> getBattleLog() { return battleLog; }

    public String summary() {
        return "Winner: " + winner + ", rounds: " + rounds + ", log lines: " + battleLog.size();
    }
}