package com.teamhawk.sunka.logic;

import android.util.Log;

import com.teamhawk.sunka.ui.MainActivity;

import java.util.ArrayList;

/**
 * Testing a potential board layout
 */
public class Board extends ArrayList<Slot> {

    public Player player1, player2;

    public static final int P1_h = 0;
    public static final int P1_1 = 1;
    public static final int P1_2 = 2;
    public static final int P1_3 = 3;
    public static final int P1_4 = 4;
    public static final int P1_5 = 5;
    public static final int P1_6 = 6;
    public static final int P1_7 = 7;
    public static final int P2_7 = 8;
    public static final int P2_6 = 9;
    public static final int P2_5 = 10;
    public static final int P2_4 = 11;
    public static final int P2_3 = 12;
    public static final int P2_2 = 13;
    public static final int P2_1 = 14;
    public static final int P2_h = 15;

    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        Slot p1_h = new HomeSlot(P1_h);
        Slot p2_h = new HomeSlot(P2_h);

        Slot p1_1 = new Slot(P1_1);
        Slot p1_2 = new Slot(P1_2);
        Slot p1_3 = new Slot(P1_3);
        Slot p1_4 = new Slot(P1_4);
        Slot p1_5 = new Slot(P1_5);
        Slot p1_6 = new Slot(P1_6);
        Slot p1_7 = new Slot(P1_7);
        Slot p2_1 = new Slot(P2_1);
        Slot p2_2 = new Slot(P2_2);
        Slot p2_3 = new Slot(P2_3);
        Slot p2_4 = new Slot(P2_4);
        Slot p2_5 = new Slot(P2_5);
        Slot p2_6 = new Slot(P2_6);
        Slot p2_7 = new Slot(P2_7);

        for (int i=0;i<this.size();i++){
            if(i<size()/2)this.get(i).setPlayer(player1);
            else this.get(i).setPlayer(player2);
        }

        p1_h.setNext(p1_1);
        p1_1.setNext(p1_2);
        p1_2.setNext(p1_3);
        p1_3.setNext(p1_4);
        p1_4.setNext(p1_5);
        p1_5.setNext(p1_6);
        p1_6.setNext(p1_7);
        p1_7.setNext(p2_h);
        p2_h.setNext(p2_7);
        p2_7.setNext(p2_6);
        p2_6.setNext(p2_5);
        p2_5.setNext(p2_4);
        p2_4.setNext(p2_3);
        p2_3.setNext(p2_2);
        p2_2.setNext(p2_1);
        p2_1.setNext(p1_h);

        add(p1_h);
        add(p1_1);
        add(p1_2);
        add(p1_3);
        add(p1_4);
        add(p1_5);
        add(p1_6);
        add(p1_7);
        add(p2_h);
        add(p2_7);
        add(p2_6);
        add(p2_5);
        add(p2_4);
        add(p2_3);
        add(p2_2);
        add(p2_1);

    }

    public void clicked(Slot slot){
        /*
        An idea I had

        for(int i=0; i<slot.getBallCount();i++){
            slot.getNext().incrementBallCount();
            slot = slot.getNext();
        }
        slot.resetBallCount();

        */
        if (!slot.isHomeSlot()){
            adjustNext(slot, slot.getBallCount());
            slot.resetBallCount();
        }

    }

    public void adjustNext(Slot slot, int ballCt1) {
        if (ballCt1 != 0) {
            slot.getNext().incrementBallCount();
            ballCt1--;
            this.adjustNext(slot.getNext(), ballCt1);
        }
    }

    public Slot[] getSlots(){
        Slot[] slots = new Slot[this.size()];
        for(int i=0;i<this.size();i++){
            slots[i] = this.get(i);
        }
        return slots;
    }
}
