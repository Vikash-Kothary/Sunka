package com.teamhawk.sunka.Logic;

import android.content.res.Resources;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Testing a potential board layout
 */
public class Board extends HashMap<String, Slot> {

    public final static int p1_h = 10;
    public final static int p1_1 = 11;
    public final static int p1_2 = 12;
    public final static int p1_3 = 13;
    public final static int p1_4 = 14;
    public final static int p1_5 = 15;
    public final static int p1_6 = 16;
    public final static int p1_7 = 17;
    public final static int p2_h = 20;
    public final static int p2_1 = 21;
    public final static int p2_2 = 22;
    public final static int p2_3 = 23;
    public final static int p2_4 = 24;
    public final static int p2_5 = 25;
    public final static int p2_6 = 26;
    public final static int p2_7 = 27;

    public Board() {
        Slot p1_h = new HomeSlot();
        Slot p2_h = new HomeSlot();

        Slot p1_5 = new Slot();
        Slot p1_4 = new Slot();
        Slot p1_3 = new Slot();
        Slot p1_2 = new Slot();
        Slot p1_1 = new Slot();
        Slot p1_0 = new Slot();

        Slot p2_5 = new Slot();
        Slot p2_4 = new Slot();
        Slot p2_3 = new Slot();
        Slot p2_2 = new Slot();
        Slot p2_1 = new Slot();
        Slot p2_0 = new Slot();

        p1_h.init(this.p1_h, 0, p2_0);
        p2_h.init(this.p2_h, 0, p1_0);

        p1_0.init(this.p1_6, 4, p1_1);
        p1_1.init(this.p1_1, 4, p1_2);
        p1_2.init(this.p1_2, 4, p1_3);
        p1_3.init(this.p1_3, 4, p1_4);
        p1_4.init(this.p1_4, 4, p1_5);
        p1_5.init(this.p1_5, 4, p1_h);

        p2_0.init(this.p2_6, 4, p2_1);
        p2_1.init(this.p2_1, 4, p2_2);
        p2_2.init(this.p2_2, 4, p2_3);
        p2_3.init(this.p2_3, 4, p2_4);
        p2_4.init(this.p2_4, 4, p2_5);
        p2_5.init(this.p2_5, 4, p2_h);

        this.put(Integer.toString(this.p1_h), p1_h);
        this.put(Integer.toString(this.p1_6), p1_0);
        this.put(Integer.toString(this.p1_1), p1_1);
        this.put(Integer.toString(this.p1_2), p1_2);
        this.put(Integer.toString(this.p1_3), p1_3);
        this.put(Integer.toString(this.p1_4), p1_4);
        this.put(Integer.toString(this.p1_5), p1_5);
        this.put(Integer.toString(this.p2_h), p2_h);
        this.put(Integer.toString(this.p2_6), p2_0);
        this.put(Integer.toString(this.p2_1), p2_1);
        this.put(Integer.toString(this.p2_2), p2_2);
        this.put(Integer.toString(this.p2_3), p2_3);
        this.put(Integer.toString(this.p2_4), p2_4);
        this.put(Integer.toString(this.p2_5), p2_5);
    }



}
