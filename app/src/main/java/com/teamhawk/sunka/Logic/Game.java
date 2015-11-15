package com.teamhawk.sunka.logic;

import android.util.Log;
import android.widget.Chronometer;
import android.os.SystemClock;

import com.teamhawk.sunka.ui.MainActivity;

import java.util.Calendar;

/**
 * Created by Vikash Kothary on 23-Oct-15.
 */
public class Game {

    public final static String PLAYER1 = "com.teamhawk.sunka.PLAYER1";
    public final static String PLAYER2 = "com.teamhawk.sunka.PLAYER2";

    private Board board;
    private Player player1, player2;
    private boolean turn, anotherTurn, firstTurn;
    private int ballsInPlay, player1Balls, player2Balls, shellsLeft1, shellsLeft2;
    private Slot nextSlot1, nextSlot2, targetSlot1, targetSlot2;
    Chronometer chrom1;
    Chronometer chrom2;
    long elapsedTime1 = 0;
    long elapsedTime2 = 0;
    String currentTime1 = "";
    String currentTime2 = "";
    Boolean activity1 = false;
    Boolean activity2 = false;


    //Not used
//    public Game(Board board, Player player1, Player player2){
//        this.board = board;
//        this.player1 = player1;
//        this.player2 = player2;
//        init();
//    }

    public Game(Board board) {
        this.board = board;
        this.player1 = board.player1;
        this.player2 = board.player2;
        init();
    }

    private void init() {
        //Random player start unless there is an AI, in which case give priority to the human
        if (player2.isAI()) {
            turn = true;
        } else {
            turn = Math.random() < 0.5;
        }

        anotherTurn = false;
        firstTurn=true;
        nextSlot1 = null;
        nextSlot2 = null;
        targetSlot1 = null;
        targetSlot2 = null;
//        System.out.println(turn);
    }

    public void skipFirstTurn() {
        firstTurn=false;
    }

    //Player one is true
    public Player getTurnPlayer() {
        if (turn) return player1;
        return player2;
    }

    private void nextTurn() {

        //If there is an AI in the game then it shall take it's turn the reset for the human
        if (!anotherTurn) turn = !turn;

        if (player1Balls == 0) {
            turn = false;
        } else if (player2Balls == 0) {
            turn = true;
        }

        if (turn == false && player2.isAI()) {
            player2.takeTurn(this);
            //turn = true;
        }
    }

    public Board getBoard() {
        return board;
    }

    public void firstTurnLogic(Slot slot) {
        if (slot.getPlayer().equals(player1)) {
            if (targetSlot1 == null) {
                nextSlot1 = slot.getNext();
                targetSlot1 = slot;
                for (int i = 0; i < slot.getBallCount(); i++) {
                    targetSlot1 = targetSlot1.getNext();
                }
                shellsLeft1 = slot.resetBallCount();
            } else if (nextSlot1 != null) {
                if (slot.equals(targetSlot1)) {
                    firstTurn=false;
                    nextSlot1.incrementBallCount();
                    for(int i=0; i<shellsLeft2;i++){
                        nextSlot2.incrementBallCount();
                        nextSlot2 = nextSlot2.getNext();
                    }
                    if (getTurnPlayer().equals(player1)) anotherTurn = true;
                    nextTurn();
                } else if (slot.equals(nextSlot1)) {
                    nextSlot1.incrementBallCount();
                    nextSlot1 = nextSlot1.getNext();
                }
            }
        } else {
            if (targetSlot2 == null) {
                nextSlot2 = slot.getNext();
                targetSlot2 = slot;
                for (int i = 0; i < slot.getBallCount(); i++) {
                    targetSlot2 = targetSlot2.getNext();
                }
                shellsLeft2 = slot.resetBallCount();
            } else if (nextSlot2 != null) {
                if (slot.equals(targetSlot2)) {
                    firstTurn=false;
                    nextSlot2.incrementBallCount();
                    for(int i=0; i<shellsLeft1;i++){
                        nextSlot1.incrementBallCount();
                        nextSlot1 = nextSlot1.getNext();
                    }
                    if (getTurnPlayer().equals(player2)) anotherTurn = true;
                    nextTurn();
                } else if (slot.equals(nextSlot2)) {
                    nextSlot2.incrementBallCount();
                    nextSlot2 = nextSlot2.getNext();
                }
            }
        }
    }





    public void turn(Slot slot) {
        if(firstTurn){
            firstTurnLogic(slot);
        }else {
            Player inTurn = getTurnPlayer();

            //Comment out 'slot.getPlayer().equals(inTurn) && ' to skip the turn enforcement
            if (slot.getPlayer().equals(inTurn) && !slot.isHomeSlot() && (slot.getBallCount() != 0)) {

                //System.out.println("in play: " + ballsInPlay + " p1: " + player1Balls + " p2: " + player2Balls);

                //Make the turn
                anotherTurn = board.clicked(slot, inTurn);

                //Reset the ball counts for the next bit
                ballsInPlay = 0;
                player1Balls = 0;
                player2Balls = 0;

                //If there are no balls in play determine the winner
              /*  if (checkWinner() != null) {

                    //Game is over, create delay then exit
                    try {
                        Thread.sleep(10000); //1000 milliseconds is one second.
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    System.exit(0);
                }*/
                checkWinner();

                //System.out.println("in play: " + ballsInPlay + " p1: " + player1Balls + " p2: " + player2Balls);

                //Update who's next
                nextTurn();
            }
        }
    }
    public String checkWinner() {
        if (checkBalls(board.get(1)) == true) {
            int p1Ct = board.get(0).getBallCount();
            int p2Ct = board.get(8).getBallCount();

            if (p1Ct > p2Ct) {
                System.out.println(player1.getPlayerName()); // Do stuff here for P1 win

                return player1.getPlayerName();
            } else if (p2Ct > p1Ct) {
                System.out.println(player2.getPlayerName()); //Do stuff here for P2 win

                return player2.getPlayerName();
            } else return "tie"; //DO stuff here for tie
        }

        return null;
    }

    public void exitGame(){
        System.exit(0);
    }
    //Check the status of the balls in the game
    private boolean checkBalls(Slot slot) {
        int ID = slot.getId();
        if (ID != 15) {
            int ballsToAdd = slot.getBallCount();
            if (!slot.isHomeSlot()) ballsInPlay += ballsToAdd;
            if (ID < 7) player1Balls += ballsToAdd;
            if (ID > 7 && ID < 15) player2Balls += ballsToAdd;
            checkBalls(slot.getNext());
        }
        //Return true if there are no balls left in play
        return ballsInPlay == 0;
    }



    public boolean getTurn() {
        return turn;
    }

    public int getPlayer2Shells() {
        return player2Balls;
    }

    public int getPlayer1Shells() {
        return player1Balls;
    }

    public Player getPlayer(String playerName) {
        return board.getPlayer(playerName);
    }

    public int numberOfShells() {
        return ballsInPlay;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void rematch() {
        this.board = new Board(player1, player2);
        init();
    }



    //heavy code for the Chronometer

    public void triggerTimePlayer2(Chronometer chrom1f, Chronometer chrom2f){
        this.chrom1 = chrom1f;
        this.chrom2 = chrom2f;
        chrom2.stop();
        chrom2.setText(currentTime2);
        activity2 = true;

       // System.out.println("button below triggerTimePlayer2 trigger chrom1 currentTime2:"+currentTime2);

        if (!activity1&&elapsedTime1==0) {

            chrom1.setBase(SystemClock.elapsedRealtime());
            chrom1.start();
            activity2 = false;

        } else {

            chrom1.start();
        }
    }




    public void triggerTimePlayer1(Chronometer chrom1f, Chronometer chrom2f){
        this.chrom1 = chrom1f;
        this.chrom2 = chrom2f;
        chrom1.stop();
        chrom1.setText(currentTime1);
        activity1 = true;

      //  System.out.println("button above triggerTimePlayer1 trigger chrom2 currentTime1:"+currentTime1);

        if (!activity2&&elapsedTime2==0) {


            chrom2.setBase(SystemClock.elapsedRealtime());
            chrom2.start();
            activity1 = false;

        } else {

            chrom2.start();
        }
    }


    public void setTimeChrom1(Chronometer chrom1f){

        this.chrom1 = chrom1f;
        chrom1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {


                if (!activity1) {

                    long minutes = ((SystemClock.elapsedRealtime() - chrom1.getBase()) / 1000) / 60;
                    long seconds = ((SystemClock.elapsedRealtime() - chrom1.getBase()) / 1000) % 60;
                    currentTime1 = minutes + ":" + seconds;
                    chronometer.setText(currentTime1);
                    elapsedTime1 = SystemClock.elapsedRealtime();

                 //   System.out.println("Activity 1 !false , elapsedTime1, "+elapsedTime1);
                 //   System.out.println("Activity 1 !false , currentTime1, "+currentTime1);


                } else {
                    long minutes = ((elapsedTime1 - chrom1.getBase()) / 1000) / 60;
                    long seconds = ((elapsedTime1 - chrom1.getBase()) / 1000) % 60;
                    currentTime1 = minutes + ":" + seconds;
                    chronometer.setText(currentTime1);
                    elapsedTime1 = elapsedTime1 + 1000;

                  //  System.out.println("Activity 1 else , elapsedTime1, " + elapsedTime1);
                  //  System.out.println("Activity 1 else , currentTime1, "+currentTime1);


                }

            }

        });
    }
    public void setTimeChrom2(Chronometer chrom2f){
        this.chrom2 = chrom2f;

        chrom2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer2) {
                if (!activity2) {

                    long minutes = ((SystemClock.elapsedRealtime() - chrom2.getBase()) / 1000) / 60;
                    long seconds = ((SystemClock.elapsedRealtime() - chrom2.getBase()) / 1000) % 60;
                    currentTime2 = minutes + ":" + seconds;
                    chronometer2.setText(currentTime2);
                    elapsedTime2 = SystemClock.elapsedRealtime();

                  //  System.out.println("Activity 2 !false , elapsedTime2, " + elapsedTime2);
                   // System.out.println("Activity 2 !false , currentTime2, " + currentTime2);


                } else {
                    long minutes = ((elapsedTime2 - chrom2.getBase()) / 1000) / 60;
                    long seconds = ((elapsedTime2 - chrom2.getBase()) / 1000) % 60;
                    currentTime2 = minutes + ":" + seconds;
                    chronometer2.setText(currentTime2);
                    elapsedTime2 = elapsedTime2 + 1000;

                  //  System.out.println("Activity 2 else , elapsedTime2, " + elapsedTime2);
                 //   System.out.println("Activity 2 else , currentTime2, " + currentTime2);


                }


            }
        });





    }
    public void stopChroms(Chronometer chrom1f, Chronometer chrom2f){
        this.chrom1 = chrom1f;
        this.chrom2 = chrom2f;
        chrom1.stop();
        chrom2.stop();

    }
}