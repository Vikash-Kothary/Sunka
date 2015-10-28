package com.teamhawk.sunka.logic;

/**
 * Created by Vikash Kothary on 23-Oct-15.
 */
public class Game {
    /* May be redundant. Considering combining with board clase */

    public final static String PLAYER1 = "com.teamhawk.sunka.PLAYER1";
    public final static String PLAYER2 = "com.teamhawk.sunka.PLAYER2";

    private Board board;
    private Player player1;
    private Player player2;
    private boolean turn;
    private boolean anotherTurn;

    public Game(Board board, Player player1, Player player2){
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        init();
    }

    public Game(Board board) {
        this.board = board;
        this.player1 = board.player1;
        this.player2 = board.player2;
        init();
    }

    private void init(){
        // random player start
        turn = Math.random()%2==0;
        anotherTurn=false;
    }

    private Player getTurnPlayer(){
        if(turn) return player2;
        return player1;
    }

    private void nextTurn(){
        if(!anotherTurn) turn = !turn;
    }

    public Board getBoard() {
        return board;
    }

    public void turn(Slot slot){
//        if (slot.getPlayer().equals(getTurnPlayer())){
            board.clicked(slot);
            nextTurn();
//        }
    }


}