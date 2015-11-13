package com.teamhawk.sunka.logic;

import android.os.SystemClock;
import android.widget.Chronometer;

import java.util.Random;
import android.content.Context;

/**
 * Created by Vikash Kothary on 23-Oct-15.
 */
public class Game{

    public final static String PLAYER1 = "com.teamhawk.sunka.PLAYER1";
    public final static String PLAYER2 = "com.teamhawk.sunka.PLAYER2";

    private Board board;

    private Player player1;
    private Player player2;


    private boolean turn;
    private boolean anotherTurn;

    private int ballsInPlay;
    private int player1Balls;
    private int player2Balls;



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

    private void init(){
        // random player start
        turn = Math.random() < 0.5;

        anotherTurn=false;
        System.out.println(turn);
    }

    //Player one is true
    public Player getTurnPlayer(){
        if(turn) return player1;

        return player2;
    }




    private void nextTurn() {
        if (!anotherTurn) turn = !turn;

        if (player1Balls == 0) {

            turn = false;

        } else if (player2Balls == 0) {
            turn = true;
        }
    }


    public Board getBoard() {
        return board;
    }

    public void turn(Slot slot){
        Player inTurn = getTurnPlayer();

        //Comment out 'slot.getPlayer().equals(inTurn) && ' to skip the turn enforcement
        if (slot.getPlayer().equals(inTurn) && !slot.isHomeSlot() && (slot.getBallCount() != 0)){

            //System.out.println("in play: " + ballsInPlay + " p1: " + player1Balls + " p2: " + player2Balls);

            //Make the turn
            anotherTurn = board.clicked(slot, inTurn);

            //Reset the ball counts for the next bit
            ballsInPlay = 0;
            player1Balls = 0;
            player2Balls = 0;

            //If there are no balls in play determine the winner
        checkWinner();
        //System.out.println("in play: " + ballsInPlay + " p1: " + player1Balls + " p2: " + player2Balls);

            //Update who's next
            nextTurn();
        }
    }

    public String checkWinner(){
        if (checkBalls(board.get(1)) == true) {
            int p1Ct = board.get(0).getBallCount();
            int p2Ct = board.get(8).getBallCount();
            if (p1Ct > p2Ct){
                return player1.getPlayerName();
                //System.out.println("p1 won"); Do stuff here for P1 win
            }
            else if (p2Ct > p1Ct) {
                return player2.getPlayerName();

               // System.out.println("p2 won"); Do stuff here for P2 win
            }
            else return "tie"; //DO stuff here for tie
        }
        return null;
    }

    public Player getPlayer(String playerName){
        return board.getPlayer(playerName);
    }

    //Check the status of the balls in the game
    private boolean checkBalls(Slot slot){
        int ID = slot.getId();
        if (ID != 15) {
            int ballsToAdd = slot.getBallCount();
            if (!slot.isHomeSlot()) ballsInPlay += ballsToAdd;
            if (ID < 7) player1Balls += ballsToAdd;
            if (ID > 7 && ID < 15) player2Balls += ballsToAdd;
            checkBalls(slot.getNext());
        }
        //Return true if there are no balls left in play
        if (ballsInPlay == 0) return true;
        return false;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer1() {
        return player1;
    }


    public void rematch(){
        this.board = new Board(player1, player2);
        init();
    }



}