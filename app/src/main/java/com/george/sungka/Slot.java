package com.george.sungka;

import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.HashMap;


/**
 * Created by George on 16/10/2015.
 */
public class Slot {

    private String id;
    private int ballCt;
    private Slot next;
    int homeCount;
    int nextCount;
    Slot slot0;
    Slot slot1;

    public Slot(){}

    public String getId() {
        return id;
    }

    public boolean isEmpty() {
        return ballCt==0;
    }

    //Logic for button press
    public boolean clicked(HashMap buttons, HashMap slots, String idString, boolean turn){

        System.out.println("clicked");

        //Records pre-conditions for another turn
        if (idString.contains("p1")){
            slot0 = (Slot)slots.get("p1_h");
            slot1 = (Slot)slots.get("p2_0");
        } else if (idString.contains("p2")) {
            slot0 = (Slot) slots.get("p2_h");
            slot1 = (Slot) slots.get("p1_0");
        }
        this.homeCount = slot0.ballCt;
        this.nextCount = slot1.ballCt;

        if((turn && idString.contains("p1")) || (!turn && idString.contains("p2")) ){
            updateButton(buttons, idString, "0");

            adjustNext(buttons, ballCt);

            ballCt = 0;

            //Determines post-conditions for another turn
            if (idString.contains("p1")){
                slot0 = (Slot)slots.get("p1_h");
                slot1 = (Slot)slots.get("p2_0");

            } else if (idString.contains("p2")) {
                slot0 = (Slot) slots.get("p2_h");
                slot1 = (Slot) slots.get("p1_0");
            }
            if (this.homeCount < slot0.ballCt && slot1.ballCt == this.nextCount){
                return turn;
            } else {
                return !turn;
            }

        }
        return turn;
    }

    //Recursive method does all the work
    public void adjustNext(HashMap buttons, int ballCt1) {

        if (ballCt1 == 0) {

        } else {
            next.ballCt++;
            ballCt1--;
            next.updateButton(buttons, next.id, Integer.toString(next.ballCt));
            next.adjustNext(buttons, ballCt1);
        }
    }

    //Updates the button GUI text
    public void updateButton(HashMap buttons, String idString, String val){
        Button b = (Button) buttons.get(idString);
        b.setText(val);
    }

    //Sets up the slot objects
    public void Initialise (String id, int ballCtSlot, Slot next) {this.id = id; this.ballCt = ballCtSlot; this.next = next;}

    //not used yet
    public void setNext(Slot next) {this.next = next;}
}
