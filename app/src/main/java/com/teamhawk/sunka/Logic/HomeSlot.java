package com.teamhawk.sunka.logic;

/**
 * Created by Vikash Kothary on 23-Oct-15.
 */
public class HomeSlot extends Slot{

    public HomeSlot(int id) {
        super(id);
        setBallCount(0);
    }

    public void setFriends(Slot next) {
        this.next = next;
    }

    @Override
    public boolean isHomeSlot() {
        return true;
    }
}
