package com.teamhawk.sunka.logic;

/**
 * Created by George on 30/10/2015.
 */
public class MainSlot extends Slot{

    public MainSlot(int id) {
        super(id);
        setBallCount(7);
    }

        public void setFriends(Slot next, Slot opposite, Slot home) {
        this.next = next;
        this.opposite = opposite;
        this.home = home;
    }
}
