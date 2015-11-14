package com.teamhawk.sunka.logic;

import java.util.ArrayList;

/**
 * The board layout
 */
public class Board extends ArrayList<Slot> {

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
    public Player player1, player2, inTurn;
    private boolean anotherTurn;

    public Board(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;

        HomeSlot p1_h = new HomeSlot(P1_h, player1);
        HomeSlot p2_h = new HomeSlot(P2_h, player2);

        MainSlot p1_1 = new MainSlot(P1_1, player1);
        MainSlot p1_2 = new MainSlot(P1_2, player1);
        MainSlot p1_3 = new MainSlot(P1_3, player1);
        MainSlot p1_4 = new MainSlot(P1_4, player1);
        MainSlot p1_5 = new MainSlot(P1_5, player1);
        MainSlot p1_6 = new MainSlot(P1_6, player1);
        MainSlot p1_7 = new MainSlot(P1_7, player1);
        MainSlot p2_1 = new MainSlot(P2_1, player2);
        MainSlot p2_2 = new MainSlot(P2_2, player2);
        MainSlot p2_3 = new MainSlot(P2_3, player2);
        MainSlot p2_4 = new MainSlot(P2_4, player2);
        MainSlot p2_5 = new MainSlot(P2_5, player2);
        MainSlot p2_6 = new MainSlot(P2_6, player2);
        MainSlot p2_7 = new MainSlot(P2_7, player2);

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

        //Add the slots to the board array list, the numbers are for AI reference
        add(p1_h);
        add(p1_1);
        add(p1_2);
        add(p1_3);
        add(p1_4);
        add(p1_5);
        add(p1_6);
        add(p1_7);
        add(p2_h);
        add(p2_7);  //9
        add(p2_6);  //10
        add(p2_5);  //11
        add(p2_4);  //12
        add(p2_3);  //13
        add(p2_2);  //14
        add(p2_1);  //15
    }

    public Player getPlayer(String playerName){
        if(player1.getPlayerName().equalsIgnoreCase(playerName)) return player1;
        else if(player2.getPlayerName().equalsIgnoreCase(playerName)) return player2;
        return null;
    }

    public boolean clicked(Slot slot, Player player) {
        inTurn = player;

        //Reset the another turn variable
        anotherTurn = false;
        //Initiate the ball adjustments
        adjustNext(slot, slot.getBallCount());
        //All balls in the initial slot for this turn have been used up
        slot.resetBallCount();
        return anotherTurn;
    }

    //Recursive method, does most of the work
    private void adjustNext(Slot slot, int ballCt) {
        //Define slot once to reduce method calls
        Slot next = slot.getNext();


        if (ballCt != 0) {
            //Steal function
            if ((ballCt == 1) && (!slot.isHomeSlot() && !next.isHomeSlot() &&
                    next.getBallCount() == 0 && next.getOpposite().getBallCount() != 0)){
                //Define slots once to reduce method calls
                Slot home = slot.getHome();
                Slot opposite = next.getOpposite();

                home.setBallCount(home.getBallCount() + opposite.getBallCount() + 1);
                opposite.setBallCount(0);
            } else {

                //Check if slot is opponent's home, if so skip it
                if ((next.isHomeSlot()) && (next.getPlayer() != inTurn)){
                    next.getNext().incrementBallCount();
                    ballCt --;
                    this.adjustNext(next.getNext(), ballCt);

                } else {

                    //Check for another turn
                    if (ballCt == 1 && next.isHomeSlot() == true) {
                        anotherTurn = true;
                    }
                    slot.getNext().incrementBallCount();
                    ballCt--;
                    this.adjustNext(slot.getNext(), ballCt);
                }
            }
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
