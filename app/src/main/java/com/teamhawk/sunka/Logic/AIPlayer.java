package com.teamhawk.sunka.logic;

import com.teamhawk.sunka.ui.MainActivity;

import java.util.Random;

/**
 * Created by Vikash Kothary on 27-Oct-15.
 */
public class AIPlayer extends Player {

    public AIPlayer(){
        super("AI");
    }

    @Override
    public boolean isAI(){
        return true;
    }

    //Logic for AI
    @Override
    public void takeTurn (Game game){
        Random random = new Random();
        Board board = game.getBoard();

        int buttonClick;
        buttonClick = random.nextInt(16 - 9) + 9;

        if (board.get(15).getBallCount() == 7){
            buttonClick = 15;
        }

        System.out.println("I'm clicking: " + buttonClick + " (" + board.get(buttonClick).getId() +
                "). " + "It has a ball count of: " + board.get(buttonClick).getBallCount() + ".");
        if (board.get(buttonClick).getBallCount() == 0){
            System.out.println("Woops, that slot was empty, I'll try again.");
            takeTurn(game);
        } else {
            game.turn((board.get(buttonClick)));
        }
    }

}
