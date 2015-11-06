package com.teamhawk.sunka.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.teamhawk.sunka.logic.AIPlayer;
import com.teamhawk.sunka.logic.Board;
import com.teamhawk.sunka.logic.Game;
import com.teamhawk.sunka.logic.Player;
import com.teamhawk.sunka.logic.Slot;
import com.teamhawk.sunka.R;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "com.teamhawk.sunka.log";
    private Board slots;
    private Game game;
    private HashMap<Button, Slot> buttonToSlot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name = getIntent().getStringExtra(Game.PLAYER1);
        Player player1 = new Player(name);

        Player player2;
        if(getIntent().hasExtra(Game.PLAYER2)) {
            name = getIntent().getStringExtra(Game.PLAYER2);
            player2 = new Player(name);
        }else {
            player2 = new AIPlayer();
        }

        TextView textView_player1 = (TextView) findViewById(R.id.player1_Text);
        TextView textView_player2 = (TextView) findViewById(R.id.player2_Text);

        textView_player1.setText(player1.getPlayerName());
        textView_player2.setText(player2.getPlayerName());

        final Button[] buttons = {
                (Button) findViewById(R.id.p1_h),
                (Button) findViewById(R.id.p1_0),
                (Button) findViewById(R.id.p1_1),
                (Button) findViewById(R.id.p1_2),
                (Button) findViewById(R.id.p1_3),
                (Button) findViewById(R.id.p1_4),
                (Button) findViewById(R.id.p1_5),
                (Button) findViewById(R.id.p1_6),
                (Button) findViewById(R.id.p2_h),
                (Button) findViewById(R.id.p2_6),
                (Button) findViewById(R.id.p2_5),
                (Button) findViewById(R.id.p2_4),
                (Button) findViewById(R.id.p2_3),
                (Button) findViewById(R.id.p2_2),
                (Button) findViewById(R.id.p2_1),
                (Button) findViewById(R.id.p2_0)
        };

        game = new Game(new Board(player1, player2));

        Slot[] slots = game.getBoard().getSlots();

        buttonToSlot = new HashMap<>();

        for (int i=0;i<buttons.length;i++){
            buttonToSlot.put(buttons[i],slots[i]);
            buttons[i].setText(Integer.toString(slots[i].getBallCount()));
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game.turn(buttonToSlot.get(v));
                    for (int i=0;i<buttons.length;i++){
                        buttons[i].setText(Integer.toString(game.getBoard().getSlots()[i].getBallCount()));
                    }
                }
            });
        }
    }

    //Not used
    //Registers when a button is clicked 'changed'
//    public void clicked(View view){
//
//        int id = view.getId();
//        Resources res = view.getResources();
//        String idString = res.getResourceEntryName(id);
//
//        System.out.println(idString);
//
//        //Create another hash map, to allow easy reference to the UI buttons
//        HashMap buttons = new HashMap<String, Button>();
//        //Loop twice, once for each player
//        for (int i = 1; i < 3; i++) {
//            String playerString = "p" + Integer.toString(i) + "_";
//            //Loop 6 times, once for each of the main buttons
//            for (int j = 0; j < 6; j++) {
//                String buttonID = playerString + Integer.toString(j);
//                Button button = (Button) findViewById(getResources().getIdentifier(buttonID, "id", getPackageName()));
//                buttons.put(buttonID, button);
//            }
//            //Add the home button
//            String buttonID = playerString + "h";
//            Button button = (Button) findViewById(getResources().getIdentifier(buttonID, "id", getPackageName()));
//            buttons.put(buttonID, button);
//        }
//
//        //Refer to the clicked method in the slot object
//        Slot y = (Slot)slots.get(idString);
//        y.clicked(buttons, idString);
//    }

}
