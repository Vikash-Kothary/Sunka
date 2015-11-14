package com.teamhawk.sunka.ui;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.teamhawk.sunka.R;
import com.teamhawk.sunka.logic.AIPlayer;
import com.teamhawk.sunka.logic.Board;
import com.teamhawk.sunka.logic.Game;
import com.teamhawk.sunka.logic.Player;
import com.teamhawk.sunka.logic.Slot;
import com.teamhawk.sunka.logic.Statistics;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "com.teamhawk.sunka.log";
    public static ImageView imageCoin;
    public Game game;
    //    Chronometer chrom1;
//    Chronometer chrom2;
//    long elapsedTime1 = 0;
//    long elapsedTime2 = 0;
//    String currentTime1 = "";
//    String currentTime2 = "";
//    boolean activity1 = false;
//    boolean activity2 = false;
//    String player1Name;
//    String player2Name;
//    Player player1;
//    Player player2;
    private Board slots;
    //    private HashMap<ImageButton, Slot> buttonToSlot;
    private HashMap<Button, Slot> buttonToSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView_player1 = (TextView) findViewById(R.id.player1_Text);
        TextView textView_player2 = (TextView) findViewById(R.id.player2_Text);
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
//        ImageButton[] buttons = {
//                (ImageButton) findViewById(R.id.p1_h),
//                (ImageButton) findViewById(R.id.p1_0),
//                (ImageButton) findViewById(R.id.p1_1),
//                (ImageButton) findViewById(R.id.p1_2),
//                (ImageButton) findViewById(R.id.p1_3),
//                (ImageButton) findViewById(R.id.p1_4),
//                (ImageButton) findViewById(R.id.p1_5),
//                (ImageButton) findViewById(R.id.p1_6),
//                (ImageButton) findViewById(R.id.p2_h),
//                (ImageButton) findViewById(R.id.p2_6),
//                (ImageButton) findViewById(R.id.p2_5),
//                (ImageButton) findViewById(R.id.p2_4),
//                (ImageButton) findViewById(R.id.p2_3),
//                (ImageButton) findViewById(R.id.p2_2),
//                (ImageButton) findViewById(R.id.p2_1),
//                (ImageButton) findViewById(R.id.p2_0)
//        };

//        chrom1 = (Chronometer) findViewById(R.id.chronometer1);
//        chrom2 = (Chronometer) findViewById(R.id.chronometer2);

        if (savedInstanceState != null && savedInstanceState.containsKey(GameSaveState.KEY)) {
            game = ((GameSaveState) savedInstanceState.getParcelable(GameSaveState.KEY)).getGame();

            textView_player1.setText(game.getPlayer1().getPlayerName());
            textView_player2.setText(game.getPlayer2().getPlayerName());
        } else {


            String name = getIntent().getStringExtra(Game.PLAYER1);
            Player player1 = new Player(name);

            Player player2;
            if (getIntent().hasExtra(Game.PLAYER2)) {
                name = getIntent().getStringExtra(Game.PLAYER2);
                player2 = new Player(name);
            } else {
                player2 = new AIPlayer();
            }

            textView_player1.setText(player1.getPlayerName());
            textView_player2.setText(player2.getPlayerName());

            game = new Game(new Board(player1, player2));

        }
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
//                        timerCode(v, buttons);
                    }
                    String winner = game.checkWinner();
                    if(winner!=null){
                        if(winner!="tie"){
                            Statistics stats = new Statistics(getApplicationContext());
                            stats.open();
                            stats.createEntry(game.getPlayer(winner));
                            stats.close();

//                            Dialog dialog = new Dialog(getApplicationContext());
//                            dialog.setContentView(R.layout.dialog_game_over);
//                            dialog.setTitle("The winner is:");
//                            dialog.setCancelable(true);
//                            dialog.show();
                        }
                    }
                }
            });
        }

        //       int density = (int) getResources().getDisplayMetrics().density;
        //         Log.d("dpi", "" + density);
        //           Log.d("Test_cordinates", "" + xforbutton1 + " " + yforbutton1);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(GameSaveState.KEY, new GameSaveState(game));
    }

    @Override
    protected void onPause() {
        super.onPause();
        onSaveInstanceState(new Bundle());
    }

    //    @Override
//    public void onWindowFocusChanged(boolean loaded) {
//        super.onWindowFocusChanged(loaded);
//        if (loaded) {
//            ViewGroup comtainer = (ViewGroup)findViewById(R.id.container);
//            ViewGroup game_board = (ViewGroup)findViewById(R.id.board);
//            ViewGroup coinView1 = (ViewGroup) findViewById(R.id.coinView1);
////            float x = buttons[4].getX();
////            float y = buttons[4].getY();
////
////
////            ImageView coinImage = new ImageView(layoutSunkaBoard.getContext());
////            coinImage.setImageResource(R.drawable.coin);
////            imageCoin = (ImageView)findViewById(R.id.coinImage);
////            int inmgw = R.drawable.coin;
//            //imageCoin.setBackground(getDrawable(inmgw));
//            // imageCoin.setY(y);
//            // imageCoin.setX(x);
//
//            // imageCoin.animate().translationXBy(buttons[4].getX()).translationYBy(buttons[4].getY()).setDuration(0);
//
//
//
//            // for(ImageButton button : buttons){
//            //   for( int i=0;i<Integer.parseInt(button.getText().toString());i++){
//            int min_numbCoin = 7;
//            // for (int i = 0 ; i < 7 ; i++) {
//            ImageView coinImage = new ImageView(coinView1.getContext());
//            coinImage.setImageResource(R.drawable.coin);
//            ImageButton button = (ImageButton) buttonToSlot.keySet().toArray()[14];
//            Log.d("button", button.toString());
//
//            // coinImage.animate().translationXBy(button.getX()).translationYBy(button.getY()).setDuration(0);
//            coinImage.setX(button.getX() - (game_board.getX() - coinView1.getX()));
//            coinImage.setY(button.getY()-(comtainer.getY() - game_board.getY()));
//            Log.d("cordinates", "" + button.getX() + "" + button.getY());
//
////                    coinImage.setX(button.getX());
////                    coinImage.setY(button.getY());
//            coinView1.addView(coinImage);
//        }
//    }
//        chrom1.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
//            @Override
//            public void onChronometerTick(Chronometer chronometer) {
//
//
//                if (!activity1) {
//
//                    long minutes = ((SystemClock.elapsedRealtime() - chrom1.getBase()) / 1000) / 60;
//                    long seconds = ((SystemClock.elapsedRealtime() - chrom1.getBase()) / 1000) % 60;
//                    currentTime1 = minutes + ":" + seconds;
//                    chronometer.setText(currentTime1);
//                    elapsedTime1 = SystemClock.elapsedRealtime();
//
//                } else {
//                    long minutes = ((elapsedTime1 - chrom1.getBase()) / 1000) / 60;
//                    long seconds = ((elapsedTime1 - chrom1.getBase()) / 1000) % 60;
//                    currentTime1 = minutes + ":" + seconds;
//                    chronometer.setText(currentTime1);
//                    elapsedTime1 = elapsedTime1 + 1000;
//
//
//                }
//
//            }
//
//        });
//
//        chrom2.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
//            @Override
//            public void onChronometerTick(Chronometer chronometer2) {
//
//
//                if (!activity2) {
//
//                    long minutes = ((SystemClock.elapsedRealtime() - chrom2.getBase()) / 1000) / 60;
//                    long seconds = ((SystemClock.elapsedRealtime() - chrom2.getBase()) / 1000) % 60;
//                    currentTime2 = minutes + ":" + seconds;
//                    chronometer2.setText(currentTime2);
//                    elapsedTime2 = SystemClock.elapsedRealtime();
//
//                } else {
//                    long minutes = ((elapsedTime2 - chrom2.getBase()) / 1000) / 60;
//                    long seconds = ((elapsedTime2 - chrom2.getBase()) / 1000) % 60;
//                    currentTime2 = minutes + ":" + seconds;
//                    chronometer2.setText(currentTime2);
//                    elapsedTime2 = elapsedTime2 + 1000;
//
//
//                }
//
//
//            }
//        });
//        Button button1main = (Button) findViewById(R.id.options_Button);
//        button1main.setOnClickListener(new View.OnClickListener(){
//                                           @Override
//                                           public void onClick(View v) {
//                                               Dialog dialog = new Dialog(MainActivity.this);
//                                               dialog.setContentView(R.layout.dialog_game_over);
//                                               dialog.setTitle("The winner is:");
//                                               dialog.setCancelable(true);
//
//                                               TextView text = (TextView) dialog.findViewById(R.id.dialog_Winner);
//                                               // text.setText(R.string.lots_of_text);
//
//                                               Button exitButton = (Button) dialog.findViewById(R.id.exit_Option);
//                                               exitButton.setOnClickListener(new View.OnClickListener() {
//                                                   @Override
//                                                   public void onClick(View v) {
//
///*
//                        Intent intent = new Intent(Intent.ACTION_MAIN);
//                        intent.addCategory(Intent.CATEGORY_HOME);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//*/
//                                                       Intent intent = new Intent(MainActivity.this, MenuFragment.class);
//                                                       startActivity(intent);
//                                                       finish();
//                                                   }
//                                               });
//
//                                               Button rematchButton = (Button) dialog.findViewById(R.id.rematch_Option);
//                                               rematchButton.setOnClickListener(new View.OnClickListener() {
//                                                   @Override
//                                                   public void onClick(View v) {
//                                                       game.rematch();
//                                                       finish();
//                                                   }
//                                               });
//
//                                               Button statisticsButton = (Button) dialog.findViewById(R.id.statistics_Option);
//                                               statisticsButton.setOnClickListener(new View.OnClickListener() {
//                                                   @Override
//                                                   public void onClick(View v) {
//                                                       Intent button_Intent = new Intent(getApplicationContext(), StatisticsActivity.class);
//                                                       startActivity(button_Intent);
//                                                   }
//                                               });
//
//
//                                               dialog.show();
//                                           }
//                                       }
//
//        );


}

//    public void timerCode(View v, Button[] buttons) {
//        for (int x = 0; x <= 6; x++) {
//
//            if (v == buttons[x]) {
//                player1ButtonClicked++;
//
//                player1.setButtonClicked(player1ButtonClicked);
//                chrom2.stop();
//                chrom2.setText(currentTime2);
//                activity2 = true;
//
//                if (!activity1) {
//                    chrom1.setBase(SystemClock.elapsedRealtime());
//                    chrom1.start();
//                } else {
//
//                    chrom1.start();
//                }
//            }
//
//
//        }
//        for (int y = 9; y <= 15; y++) {
//
//            if (v == buttons[y]) {
//
//                player2ButtonClicked++;
//
//                player2.setButtonClicked(player2ButtonClicked);
//
//                chrom1.stop();
//                chrom1.setText(currentTime1);
//                activity1 = true;
//
//                if (!activity2) {
//                    chrom2.setBase(SystemClock.elapsedRealtime());
//                    chrom2.start();
//                } else {
//
//                    chrom2.start();
//                }
//
//
//            }
//        }

//    }

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


