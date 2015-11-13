package com.teamhawk.sunka;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.teamhawk.sunka.logic.AIPlayer;
import com.teamhawk.sunka.logic.Board;
import com.teamhawk.sunka.logic.Game;
import com.teamhawk.sunka.logic.MainSlot;
import com.teamhawk.sunka.logic.Player;
import com.teamhawk.sunka.logic.Slot;

import org.junit.Test;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Test
    public void testInitalShellsInMainSlot() {
        MainSlot slot = new MainSlot(0, null);
        int numOfShells = slot.getBallCount();
        assertEquals("7 Shells failed", 7, numOfShells);
    }

    @Test
    public void testNumberOfSlots() {
        Board board = new Board(null, null);
        Slot[] numOfSlots = board.getSlots();
        int slot = numOfSlots.length;
        assertEquals("16 buttons failed", 16, slot);
    }

    @Test
    public void testNumberOfShells() {
        Game game = new Game(new Board(new Player("Mahesh"), new Player("George")));
        assertEquals("96 shells failed", 98, game.numberOfShells());
    }

    @Test
    public void testNumberOfP1Shells() {
        Game game = new Game(new Board(new Player("Mahesh"), new Player("George")));
        assertEquals("49 shells failed", 49, game.getPlayer1Shells());
    }

    @Test
    public void testNumberOfP2Shells() {
        Game game = new Game(new Board(new Player("Mahesh"), new Player("George")));
        assertEquals("49 shells failed", 49, game.getPlayer2Shells());
    }

    @Test
    public void testExistanceOfPlayer2() {
        Game game = new Game(new Board(new Player("Mahesh"), new Player("George")));
        assertEquals("Player 2 is an AI", false, game.getBoard().player2.isAI());
    }

    @Test
    public void testExistanceOfAI() {
        Game game = new Game(new Board(new Player("George"), new AIPlayer()));
        assertEquals("Player 2 is not AI", true, game.getBoard().player2.isAI());
    }

    @Test
    public void testTwoHomeSlots() {
        Game game = new Game(new Board(new Player("Mahesh"), new Player("George")));
        assertEquals("There are not two home slots", true, game.getBoard().get(0).isHomeSlot() &&
                game.getBoard().get(8).isHomeSlot());
    }

    @Test
    public void testAnotherTurn() {
        Game game = new Game(new Board(new Player("Mahesh"), new AIPlayer()));
        game.turn(game.getBoard().get(1));
        assertEquals("Player 1 has not got another turn", true, game.getTurn());
    }

    @Test
    public void testSlotBecomingEmpty() {
        Game game2 = new Game(new Board(new Player("Mahesh"), new AIPlayer()));
        game2.turn(game2.getBoard().get(1));
        assertEquals("Slot is not empty", 0, game2.getBoard().get(1).getBallCount());
    }

    @Test
    public void testIncrimentByOne() {
        Game game3 = new Game(new Board(new Player("Mahesh"), new AIPlayer()));
        game3.turn(game3.getBoard().get(1));
        assertEquals("Slots have not incrimented by 1", 8, game3.getBoard().get(2).getBallCount());
    }

    @Test
    public void testIncrimentCorrectNumberOfSlots() {
        Game game = new Game(new Board(new Player("Mahesh"), new AIPlayer()));
        game.turn(game.getBoard().get(1));
        assertEquals("All slots have incrimented by 1", 1, game.getBoard().get(0).getBallCount());
    }

    @Test
    public void testAntiClockwiseMovement() {
        Game game = new Game(new Board(new Player("Mahesh"), new AIPlayer()));
        game.turn(game.getBoard().get(1));
        assertEquals("The board has not gone anti-clockwise", 1, game.getBoard().get(0).getBallCount() +
                game.getBoard().get(8).getBallCount());
    }

    // Come Back To This
//    @Test
//    public void testChangingOfTurn(){
//        Game game = new Game(new Board(new Player("Mahesh"), new AIPlayer()));
//        game.turn(game.getBoard().get(2));
//        assertEquals("Changing of turns is not working", true, game.getTurn() &&
//                game.getPlayer2Shells() < 51);
//    }



}