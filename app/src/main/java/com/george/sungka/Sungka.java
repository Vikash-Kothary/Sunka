package com.george.sungka;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//I did
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import java.util.HashMap;

public class Sungka extends AppCompatActivity {

    //The hash map for retaining all the logical 'slots'.
    private HashMap slots = new HashMap<String, Slot>();
    private boolean turn = true;


    public Sungka(){}

    //Registers when a button is clicked 'changed'
    public void change(View view){

        int id = view.getId();
        Resources res = view.getResources();
        String idString = res.getResourceEntryName(id);

        System.out.println(idString);

        //Create another hash map, to allow easy reference to the UI buttons
        HashMap buttons = new HashMap<String, Button>();
        //Loop twice, once for each player
        for (int i = 1; i < 3; i++) {
            String playerString = "p" + Integer.toString(i) + "_";
            //Loop 6 times, once for each of the main buttons
            for (int j = 0; j < 6; j++) {
                String buttonID = playerString + Integer.toString(j);
                Button button = (Button) findViewById(getResources().getIdentifier(buttonID, "id", getPackageName()));
                buttons.put(buttonID, button);
            }
            //Add the home button
            String buttonID = playerString + "h";
            Button button = (Button) findViewById(getResources().getIdentifier(buttonID, "id", getPackageName()));
            buttons.put(buttonID, button);
        }

        //Refer to the clicked method in the slot object
        Slot y = (Slot)slots.get(idString);
        turn = y.clicked(buttons, slots, idString, turn);

        if (turn){
            boolean isEmpty = true;
            for (Slot entry : ((HashMap<String,Slot>)slots).values())
            {
                if (entry.getId().contains("p1") && !entry.getId().contains("h")){
                    isEmpty=entry.isEmpty()&&isEmpty;
                }
            }
            if(isEmpty) turn=!turn;
        }else{
            boolean isEmpty = true;
            for (Slot entry : ((HashMap<String,Slot>)slots).values())
            {
                if (entry.getId().contains("p2")&& !entry.getId().contains("h")){
                    isEmpty=entry.isEmpty()&&isEmpty;
                }
            }
            if(isEmpty) turn=!turn;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sungka);

        //All of this stuff is messy, I would like to change it. i.e loops or iterators.
        //Create a sufficient quantity of slot objects
        Slot p1_h = new Slot();
        Slot p2_h = new Slot();

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

        //Initialise them
        p1_h.Initialise("p1_h", 0, p2_0);
        p2_h.Initialise("p2_h", 0, p1_0);

        p1_0.Initialise("p1_0", 4, p1_1);
        p1_1.Initialise("p1_1", 4, p1_2);
        p1_2.Initialise("p1_2", 4, p1_3);
        p1_3.Initialise("p1_3", 4, p1_4);
        p1_4.Initialise("p1_4", 4, p1_5);
        p1_5.Initialise("p1_5", 4, p1_h);

        p2_0.Initialise("p2_0", 4, p2_1);
        p2_1.Initialise("p2_1", 4, p2_2);
        p2_2.Initialise("p2_2", 4, p2_3);
        p2_3.Initialise("p2_3", 4, p2_4);
        p2_4.Initialise("p2_4", 4, p2_5);
        p2_5.Initialise("p2_5", 4, p2_h);

        //pop them into the hash map
        slots.put("p1_h", p1_h);
        slots.put("p1_0", p1_0);
        slots.put("p1_1", p1_1);
        slots.put("p1_2", p1_2);
        slots.put("p1_3", p1_3);
        slots.put("p1_4", p1_4);
        slots.put("p1_5", p1_5);
        slots.put("p2_h", p2_h);
        slots.put("p2_0", p2_0);
        slots.put("p2_1", p2_1);
        slots.put("p2_2", p2_2);
        slots.put("p2_3", p2_3);
        slots.put("p2_4", p2_4);
        slots.put("p2_5", p2_5);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sungka, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
