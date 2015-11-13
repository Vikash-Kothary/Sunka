package com.teamhawk.sunka.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
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
    private static ImageButton[] buttons = new ImageButton[16];
    public static ImageView imageCoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton[] buttons = {
                (ImageButton) findViewById(R.id.p1_h),
                (ImageButton) findViewById(R.id.p1_0),
                (ImageButton) findViewById(R.id.p1_1),
                (ImageButton) findViewById(R.id.p1_2),
                (ImageButton) findViewById(R.id.p1_3),
                (ImageButton) findViewById(R.id.p1_4),
                (ImageButton) findViewById(R.id.p1_5),
                (ImageButton) findViewById(R.id.p1_6),
                (ImageButton) findViewById(R.id.p2_h),
                (ImageButton) findViewById(R.id.p2_6),
                (ImageButton) findViewById(R.id.p2_5),
                (ImageButton) findViewById(R.id.p2_4),
                (ImageButton) findViewById(R.id.p2_3),
                (ImageButton) findViewById(R.id.p2_2),
                (ImageButton) findViewById(R.id.p2_1),
                (ImageButton) findViewById(R.id.p2_0)
        };
        for (int i = 0; i < buttons.length; i++) {
            this.buttons[i] = buttons[i];
        }
        String name = getIntent().getStringExtra(Game.PLAYER1);
        Player player1 = new Player(name);

        Player player2;
        if (getIntent().hasExtra(Game.PLAYER2)) {
            name = getIntent().getStringExtra(Game.PLAYER2);
            player2 = new Player(name);
        } else {
            player2 = new AIPlayer();
        }

        TextView textView_player1 = (TextView) findViewById(R.id.player1_Text);
        TextView textView_player2 = (TextView) findViewById(R.id.player2_Text);

        textView_player1.setText(player1.getPlayerName());
        textView_player2.setText(player2.getPlayerName());


        game = new Game(new Board(player1, player2));

        Slot[] slots = game.getBoard().getSlots();

        buttonToSlot = new HashMap<>();

//        for (int i=0;i<buttons.length;i++){
//            buttonToSlot.put(buttons[i],slots[i]);
//            buttons[i].setText(Integer.toString(slots[i].getBallCount()));
//            buttons[i].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    game.turn(buttonToSlot.get(v));
//                    for (int i=0;i<buttons.length;i++){
//                        buttons[i].setText(Integer.toString(game.getBoard().getSlots()[i].getBallCount()));
//                    }
//                }
//            });
//        }
    }


    @Override
    public void onWindowFocusChanged(boolean loaded) {
        super.onWindowFocusChanged(loaded);
        if (loaded) {
            ViewGroup  comtainer = (ViewGroup)findViewById(R.id.container);
            ViewGroup game_board = (ViewGroup)findViewById(R.id.board);
            ViewGroup coinView1 = (ViewGroup) findViewById(R.id.coinView1);
//            float x = buttons[4].getX();
//            float y = buttons[4].getY();
//
//
//            ImageView coinImage = new ImageView(layoutSunkaBoard.getContext());
//            coinImage.setImageResource(R.drawable.coin);
//            imageCoin = (ImageView)findViewById(R.id.coinImage);
//            int inmgw = R.drawable.coin;
            //imageCoin.setBackground(getDrawable(inmgw));
           // imageCoin.setY(y);
            // imageCoin.setX(x);

           // imageCoin.animate().translationXBy(buttons[4].getX()).translationYBy(buttons[4].getY()).setDuration(0);



           // for(ImageButton button : buttons){
             //   for( int i=0;i<Integer.parseInt(button.getText().toString());i++){
                int min_numbCoin = 7;
               // for (int i = 0 ; i < 7 ; i++) {
                    ImageView coinImage = new ImageView(coinView1.getContext());
                    coinImage.setImageResource(R.drawable.coin);
                    ImageButton button = buttons[14];
                    Log.d("button", button.toString());

                   // coinImage.animate().translationXBy(button.getX()).translationYBy(button.getY()).setDuration(0);
                    coinImage.setX(button.getX() - (game_board.getX() - coinView1.getX()));
                    coinImage.setY(button.getY()-(comtainer.getY() - game_board.getY()));
                    Log.d("cordinates", "" + button.getX() + "" + button.getY());

//                    coinImage.setX(button.getX());
//                    coinImage.setY(button.getY());
                    coinView1.addView(coinImage);
                }
                }

     //       int density = (int) getResources().getDisplayMetrics().density;
   //         Log.d("dpi", "" + density);
 //           Log.d("Test_cordinates", "" + xforbutton1 + " " + yforbutton1);

        }




