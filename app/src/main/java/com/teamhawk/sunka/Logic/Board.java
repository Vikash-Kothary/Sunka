package com.teamhawk.sunka.logic;

import android.util.Log;

import com.teamhawk.sunka.ui.MainActivity;

import java.util.ArrayList;

/**
 * Testing a potential board layout
 */
public class Board extends ArrayList<Slot> {

    public Player player1, player2;
    private int ballsInPlay;

    public static final int P1_1 = 0;
    public static final int P1_2 = 1;
    public static final int P1_3 = 2;
    public static final int P1_4 = 3;
    public static final int P1_5 = 4;
    public static final int P1_6 = 5;
    public static final int P1_7 = 6;
    public static final int P1_h = 7;

    public static final int P2_1 = 8;
    public static final int P2_2 = 9;
    public static final int P2_3 = 10;
    public static final int P2_4 = 11;
    public static final int P2_5 = 12;
    public static final int P2_6 = 13;
    public static final int P2_7 = 14;
    public static final int P2_h = 15;

    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        HomeSlot p1_h = new HomeSlot(P1_h);
        HomeSlot p2_h = new HomeSlot(P2_h);

        MainSlot p1_1 = new MainSlot(P1_1);
        MainSlot p1_2 = new MainSlot(P1_2);
        MainSlot p1_3 = new MainSlot(P1_3);
        MainSlot p1_4 = new MainSlot(P1_4);
        MainSlot p1_5 = new MainSlot(P1_5);
        MainSlot p1_6 = new MainSlot(P1_6);
        MainSlot p1_7 = new MainSlot(P1_7);
        MainSlot p2_1 = new MainSlot(P2_1);
        MainSlot p2_2 = new MainSlot(P2_2);
        MainSlot p2_3 = new MainSlot(P2_3);
        MainSlot p2_4 = new MainSlot(P2_4);
        MainSlot p2_5 = new MainSlot(P2_5);
        MainSlot p2_6 = new MainSlot(P2_6);
        MainSlot p2_7 = new MainSlot(P2_7);

        for (int i=0;i<this.size();i++){
            if(i<size()/2)this.get(i).setPlayer(player1);
            else this.get(i).setPlayer(player2);
        }

        p1_h.setFriends(p2_1);
        p2_h.setFriends(p1_1);

        p1_1.setFriends(p1_2, p2_7, p1_h);
        p1_2.setFriends(p1_3, p2_6, p1_h);
        p1_3.setFriends(p1_4, p2_5, p1_h);
        p1_4.setFriends(p1_5, p2_4, p1_h);
        p1_5.setFriends(p1_6, p2_3, p1_h);
        p1_6.setFriends(p1_7, p2_2, p1_h);
        p1_7.setFriends(p1_h, p2_1, p1_h);

        p2_1.setFriends(p2_2, p1_7, p2_h);
        p2_2.setFriends(p2_3, p1_6, p2_h);
        p2_3.setFriends(p2_4, p1_5, p2_h);
        p2_4.setFriends(p2_5, p1_4, p2_h);
        p2_5.setFriends(p2_6, p1_3, p2_h);
        p2_6.setFriends(p2_7, p1_2, p2_h);
        p2_7.setFriends(p2_h, p1_1, p2_h);

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
        add(p2_1);;
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

            if (checkEnd(this.get(1)) == true){
                int p1Ct = this.get(0).getBallCount();
                int p2Ct = this.get(8).getBallCount();
                if (p1Ct > p2Ct) System.out.println("p1 won");
                else if (p2Ct > p1Ct) System.out.println("p2 won");
                else System.out.println("tie");
            }
            ballsInPlay = 0;
        }

    }

    private void adjustNext(Slot slot, int ballCt1) {
        if (ballCt1 != 0) {
            //Steal function
            if ((ballCt1 == 1) && (slot.getNext().getBallCount() == 0)
                    && !slot.isHomeSlot() && !slot.getNext().isHomeSlot()){
                Slot opposite = slot.getNext().getOpposite();
                Slot home = slot.getHome();

                home.setBallCount(home.getBallCount() + opposite.getBallCount());
                opposite.setBallCount(0);

            }
            slot.getNext().incrementBallCount();
            ballCt1--;
            this.adjustNext(slot.getNext(), ballCt1);
        }
    }

    private boolean checkEnd(Slot slot){
//        System.out.println(Integer.toString(slot.getId()));
        if (slot.getId() != 15) {
            if (!slot.isHomeSlot()) ballsInPlay += slot.getBallCount();
            checkEnd(slot.getNext());
        }
        if (ballsInPlay == 0) return true;
        return false;
    }

    public Slot[] getSlots(){
        Slot[] slots = new Slot[this.size()];
        for(int i=0;i<this.size();i++){
            slots[i] = this.get(i);
        }
        return slots;
    }
}
