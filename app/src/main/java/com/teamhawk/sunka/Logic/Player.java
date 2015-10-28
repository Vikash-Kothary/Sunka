package com.teamhawk.sunka.logic;

/**
 * Created by Vikash Kothary on 26-Oct-15.
 */
public class Player {

    /* all player stats should be sorted in this class */
    private String playerName;
    private int gamesPlayed;
    private int gamesWon;
    private int gamesLost;
    private int gamesDrawn;

    public Player(String name){
        this.playerName = name;
    }

    public String getPlayerName() {
        return playerName;
    }

    public boolean equals(Player player){
        /* What if two people have the same name */
        return this.toString().equals(player.toString());
    }

    public String toString(){
        return playerName;
    }
}
