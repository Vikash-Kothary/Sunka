package com.teamhawk.sunka.UI;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.teamhawk.sunka.Logic.AIPlayer;
import com.teamhawk.sunka.Logic.Board;
import com.teamhawk.sunka.Logic.Game;
import com.teamhawk.sunka.Logic.Player;
import com.teamhawk.sunka.Logic.Slot;
import com.teamhawk.sunka.R;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Board slots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView_player1 = (TextView) findViewById(R.id.player1_Text);
        TextView textView_player2 = (TextView) findViewById(R.id.player2_Text);

        Button[] shells = {
                (Button) findViewById(R.id.p1_h),
                (Button) findViewById(R.id.p1_0),
                (Button) findViewById(R.id.p1_1),
                (Button) findViewById(R.id.p1_2),
                (Button) findViewById(R.id.p1_3),
                (Button) findViewById(R.id.p1_4),
                (Button) findViewById(R.id.p1_5),
                (Button) findViewById(R.id.p1_6),
                (Button) findViewById(R.id.p2_h),
                (Button) findViewById(R.id.p2_0),
                (Button) findViewById(R.id.p2_1),
                (Button) findViewById(R.id.p2_2),
                (Button) findViewById(R.id.p2_3),
                (Button) findViewById(R.id.p2_4),
                (Button) findViewById(R.id.p2_5),
                (Button) findViewById(R.id.p2_6),
        };

        slots = new Board();

        for (int i=0;i<shells.length;i++){
            shells[i].setText(Integer.toString(7));
//            shells[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    clicked(v);
//                }
//            });
        }

        String name = getIntent().getStringExtra(Game.PLAYER1);
        Player player1 = new Player(name);
        textView_player1.setText(player1.getPlayerName());

        if(getIntent().hasExtra(Game.PLAYER2)) {
            name = getIntent().getStringExtra(Game.PLAYER2);
            Player player2 = new Player(name);
            textView_player2.setText(player2.getPlayerName());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Registers when a button is clicked 'changed'
    public void clicked(View view){

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
        y.clicked(buttons, idString);
    }

}
