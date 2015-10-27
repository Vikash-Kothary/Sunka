package com.teamhawk.sunka.Logic;

import android.widget.Button;

import java.util.HashMap;

/**
 * Created by Vikash Kothary on 23-Oct-15.
 */
public class Slot {

    private int id;
    private int ballCt;
    private Slot next;

    public Slot() {
        this.id = 0;
        this.ballCt = 7;
        Slot next = null;
    }

    public void Slot(int id, int ballCt, Slot next) {
        init(id, ballCt, next);
    }

    public void init(int id, int ballCt, Slot next) {
        this.id = id;
        this.ballCt = ballCt;
        this.next = next;
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

    /*
     * These functions have to be updated as they should not be in the logic classes
     */

    //Logic for button press
    public void clicked(HashMap buttons, String idString){
        System.out.println("clicked");

        updateButton(buttons, idString, "0");

        adjustNext(buttons, ballCt);
        ballCt = 0;
    }

    //Recursive method does all the work
    public void adjustNext(HashMap buttons, int ballCt1) {
        if (ballCt1 != 0) {
            next.incrementBallCount();
            ballCt1--;
            next.updateButton(buttons,Integer.toString(next.id), Integer.toString(next.ballCt));
            next.adjustNext(buttons, ballCt1);
        }
    }

    //Updates the button GUI text
    public void updateButton(HashMap buttons, String idString, String val){
        Button b = (Button) buttons.get(idString);
        b.setText(val);
    }
}
