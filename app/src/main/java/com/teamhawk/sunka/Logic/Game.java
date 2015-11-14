package com.teamhawk.sunka.logic;

/**
 * Created by Vikash Kothary on 23-Oct-15.
 */
public class Game {

    public final static String PLAYER1 = "com.teamhawk.sunka.PLAYER1";
    public final static String PLAYER2 = "com.teamhawk.sunka.PLAYER2";

    private Board board;
    private Player player1, player2;
    private boolean turn, anotherTurn, firstTurn;
    private int ballsInPlay, player1Balls, player2Balls;

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
        //Random player start unless there is an AI, in which case give priority to the human
        if (player2.isAI()){
            turn = true;
        } else {
            turn = Math.random() < 0.5;
        }

        anotherTurn=false;
        firstTurn=true;
//        System.out.println(turn);
    }

    //Player one is true
    private Player getTurnPlayer(){
        if(turn) return player1;
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

        if (turn == false && player2.isAI()){
            player2.takeTurn(this);
            //turn = true;
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
            if (checkWinner()!=null){

                //Game is over, create delay then exit
                try {
                    Thread.sleep(10000); //1000 milliseconds is one second.
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.exit(0);
            }
            //System.out.println("in play: " + ballsInPlay + " p1: " + player1Balls + " p2: " + player2Balls);

            //Update who's next
            nextTurn();
        }
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
        return ballsInPlay == 0;
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

    public boolean getTurn() {
        return turn;
    }

    public int getPlayer2Shells() {
        return player2Balls;
    }

    public int getPlayer1Shells() {
        return player1Balls;
    }

    public Player getPlayer(String playerName){
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

    public void rematch(){
        this.board = new Board(player1, player2);
        init();
    }
}