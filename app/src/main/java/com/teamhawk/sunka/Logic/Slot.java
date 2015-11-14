package com.teamhawk.sunka.logic;

/**
 * Created by Vikash Kothary on 23-Oct-15.
 */
public class Slot {

    protected Slot next;
    protected Slot opposite;
    protected Slot home;
    private int id;
    private int ballCt;
    private Player player;
    private boolean clickable;

    public Slot(int id, Player player) {
        this.id = id;
        this.player = player;
        this.clickable=true;
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

    public int resetBallCount(){
        int ballCount = ballCt;
        ballCt=0;
        return ballCount;
    }

    public boolean isHomeSlot() {
        return false;
    }

    public int getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Slot)) return false;

        Slot slot = (Slot) o;

        return id == slot.id;
    }

    //
//    public boolean getClickable(){
//        return clickable;
//    }
//
//    public void setClickable(boolean clickable){
//        this.clickable = clickable;
//    }
}
