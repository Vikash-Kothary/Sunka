package com.teamhawk.sunka;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.teamhawk.sunka.logic.Board;
import com.teamhawk.sunka.logic.Game;
import com.teamhawk.sunka.logic.MainSlot;
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
        Game game = new Game(new Board(null, null));
        //Had to change the checkballs() in Game class to Public from Private for testing purposes
        game.checkBalls(game.getBoard().get(1));
        //Had to change the ballsinplay in Game class to Public from Private for testing purposes
        assertEquals("96 shells failed", 98, game.ballsInPlay);
    }


}