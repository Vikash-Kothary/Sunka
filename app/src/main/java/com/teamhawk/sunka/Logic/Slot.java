package com.teamhawk.sunka.logic;

import android.widget.Button;

import java.util.HashMap;

/**
 * Created by Vikash Kothary on 23-Oct-15.
 */
public class Slot {

    private int id;
    private int ballCt;
    private Player player;
    protected Slot next;
    protected Slot opposite;
    protected Slot home;

    public Slot(int id) {
        this.id = id;
        this.player = null;
    }

    public int getBallCount() {
        return ballCt;
    }

    public void setBallCount(int ballCt) {
        this.ballCt = ballCt;
    }

    public void incrementBallCount(){
        ballCt+=1;
    }

    public Slot getNext() {
        return next;
    }

    public Slot getOpposite() {
        return opposite;
    }

    public Slot getHome() {
        return home;
    }

    public void resetBallCount(){
        ballCt=0;
    }

    public boolean isHomeSlot() {
        return false;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
