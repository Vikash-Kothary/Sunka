package com.teamhawk.sunka.Logic;

/**
 * Created by Vikash Kothary on 23-Oct-15.
 */
public class Game {

    public final static String PLAYER1 = "com.teamhawk.sunka.PLAYER1";
    public final static String PLAYER2 = "com.teamhawk.sunka.PLAYER2";

    private Board board;
    private Player player1;
    private Player player2;
    private boolean turn;

    public Game(Board board, Player player1){
        this.board = board;
        this.player1 = player1;
        this.player2 = new AIPlayer();
        new Game();
    }

    public Game(Board board, Player player1, Player player2){
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        new Game();
    }

    private Game(){
        // random player start
        turn = Math.random()%2==0;
    }

    private Player getTurn(){
        if(turn){
            turn = !turn;
            return player1;
        }
        turn = !turn;
        return player2;
    }
}
