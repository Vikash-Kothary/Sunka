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
    private Slot next;
    private Slot opposite;

    public Slot(int id) {
        this.id = id;
        this.ballCt = 7;
        this.player = null;
    }

    public void Slot(int id, int ballCt, Slot next) {
        init(id, ballCt, next, null);
    }

    public void Slot(Slot next, Slot opposite) {
        this.next = next;
        this.opposite = opposite;
    }

    public void init(int id, int ballCt, Slot next, Slot opposite) {
        this.id = id;
        this.ballCt = ballCt;
        this.next = next;
        this.opposite = opposite;
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

    public void setNext(Slot next) {
        this.next = next;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public int getID(){
        return id;
    }

    public void setOpposite(Slot opposite) {
        this.opposite = opposite;
    }

    public void resetBallCount(){
        ballCt=0;
    }

    public boolean isHomeSlot() {
        return false;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
