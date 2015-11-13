package com.teamhawk.sunka.ui;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.teamhawk.sunka.R;
import com.teamhawk.sunka.logic.AIPlayer;
import com.teamhawk.sunka.logic.Board;
import com.teamhawk.sunka.logic.Game;
import com.teamhawk.sunka.logic.Player;
import com.teamhawk.sunka.logic.Slot;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    public static final String TAG = "com.teamhawk.sunka.log";
    public Game game;
    Chronometer chrom1;
    Chronometer chrom2;
    long elapsedTime1 = 0;
    long elapsedTime2 = 0;
    String currentTime1 = "";
    String currentTime2 = "";
    Boolean activity1 = false;
    Boolean activity2 = false;
    String player1Name;
    String player2Name;
    Player player1;
    Player player2;
    private Board slots;
    private HashMap<Button, Slot> buttonToSlot;
    private int player1ButtonClicked;
    private int player2ButtonClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name = getIntent().getStringExtra(Game.PLAYER1);
        player1 = new Player(name);


        if (getIntent().hasExtra(Game.PLAYER2)) {
            name = getIntent().getStringExtra(Game.PLAYER2);
            player2 = new Player(name);
        } else {
            player2 = new AIPlayer();
        }


        TextView textView_player1 = (TextView) findViewById(R.id.player1_Text);
        TextView textView_player2 = (TextView) findViewById(R.id.player2_Text);

        player1Name = player1.getPlayerName();
        player2Name = player2.getPlayerName();


        textView_player1.setText(player1Name = player1.getPlayerName());
        textView_player2.setText(player2Name = player2.getPlayerName());

        //Chronometer controls

        chrom1 = (Chronometer) findViewById(R.id.chronometer1);
        chrom2 = (Chronometer) findViewById(R.id.chronometer2);


        Button btn_statisticsPlayer = (Button) findViewById(R.id.options_Button);


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

        for (int i = 0; i < buttons.length; i++) {
            buttonToSlot.put(buttons[i], slots[i]);
            buttons[i].setText(Integer.toString(slots[i].getBallCount()));
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    game.turn(buttonToSlot.get(v));
                    for (int i = 0; i < buttons.length; i++) {
                        buttons[i].setText(Integer.toString(game.getBoard().getSlots()[i].getBallCount()));
                    }

                    for (int x = 0; x <= 6; x++) {

                        if (v == buttons[x]) {
                            player1ButtonClicked++;

                            player1.setButtonClicked(player1ButtonClicked);
                            chrom2.stop();
                            chrom2.setText(currentTime2);
                            activity2 = true;

                            if (!activity1) {
                                chrom1.setBase(SystemClock.elapsedRealtime());
                                chrom1.start();
                            } else {

                                chrom1.start();
                            }
                        }


                    }
                    for (int y = 9; y <= 15; y++) {

                        if (v == buttons[y]) {

                            player2ButtonClicked++;

                            player2.setButtonClicked(player2ButtonClicked);

                            chrom1.stop();
                            chrom1.setText(currentTime1);
                            activity1 = true;

                            if (!activity2) {
                                chrom2.setBase(SystemClock.elapsedRealtime());
                                chrom2.start();
                            } else {

                                chrom2.start();
                            }


                        }
                    }

                }


            });
        }


        chrom1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {


                if (!activity1) {

                    long minutes = ((SystemClock.elapsedRealtime() - chrom1.getBase()) / 1000) / 60;
                    long seconds = ((SystemClock.elapsedRealtime() - chrom1.getBase()) / 1000) % 60;
                    currentTime1 = minutes + ":" + seconds;
                    chronometer.setText(currentTime1);
                    elapsedTime1 = SystemClock.elapsedRealtime();

                } else {
                    long minutes = ((elapsedTime1 - chrom1.getBase()) / 1000) / 60;
                    long seconds = ((elapsedTime1 - chrom1.getBase()) / 1000) % 60;
                    currentTime1 = minutes + ":" + seconds;
                    chronometer.setText(currentTime1);
                    elapsedTime1 = elapsedTime1 + 1000;


                }

            }

        });

        chrom2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer2) {


                if (!activity2) {

                    long minutes = ((SystemClock.elapsedRealtime() - chrom2.getBase()) / 1000) / 60;
                    long seconds = ((SystemClock.elapsedRealtime() - chrom2.getBase()) / 1000) % 60;
                    currentTime2 = minutes + ":" + seconds;
                    chronometer2.setText(currentTime2);
                    elapsedTime2 = SystemClock.elapsedRealtime();

                } else {
                    long minutes = ((elapsedTime2 - chrom2.getBase()) / 1000) / 60;
                    long seconds = ((elapsedTime2 - chrom2.getBase()) / 1000) % 60;
                    currentTime2 = minutes + ":" + seconds;
                    chronometer2.setText(currentTime2);
                    elapsedTime2 = elapsedTime2 + 1000;


                }


            }
        });


        Button button1main = (Button) findViewById(R.id.options_Button);

        button1main.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View v) {

                                               Dialog dialog = new Dialog(MainActivity.this);
                                               dialog.setContentView(R.layout.dialog_layout);
                                               dialog.setTitle("The winner is:");
                                               dialog.setCancelable(true);

                                               TextView text = (TextView) dialog.findViewById(R.id.dialog_Winner);

                                               // text.setText(R.string.lots_of_text);

                                               Button exitButton = (Button) dialog.findViewById(R.id.exit_Option);
                                               exitButton.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {

/*
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
*/
                                                       Intent intent = new Intent(MainActivity.this, MenuFragment.class);
                                                       startActivity(intent);
                                                       finish();
                                                   }
                                               });

                                               Button rematchButton = (Button) dialog.findViewById(R.id.rematch_Option);
                                               rematchButton.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       game.rematch();
                                                       finish();
                                                   }
                                               });

                                               Button statisticsButton = (Button) dialog.findViewById(R.id.statistics_Option);
                                               statisticsButton.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(View v) {
                                                       Intent button_Intent = new Intent(getApplicationContext(), StatisticsActivity.class);
                                                       startActivity(button_Intent);
                                                   }
                                               });


                                               dialog.show();
                                           }
                                       }

        );


    }

}


