package com.teamhawk.sunka.ui;

    import android.database.Cursor;
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.support.v7.app.AppCompatActivity;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.TextView;

    import com.teamhawk.sunka.R;
    import com.teamhawk.sunka.logic.Player;
    import com.teamhawk.sunka.logic.Statistics;

    import java.util.ArrayList;
    import java.util.Iterator;

    /**
     * Created by mikey on 12/11/2015.
     */
    public class StatisticsFragment extends AppCompatActivity {
      int id=0;
        Statistics stat;
    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);



        Statistics stat = new Statistics(getContext());
        stat.open();
        for(Player player : stat.getEntries()) {
            TextView textView_playerName = (TextView)rootView.findViewById(R.id.tableRow1).findViewById(R.id.statistic_playerName);
            textView_playerName.setText(player.getPlayerName());
            TextView textView_rank = (TextView)rootView.findViewById(R.id.tableRow1).findViewById(R.id.first_RankingSlot);

            TextView textView_wins = (TextView)rootView.findViewById(R.id.tableRow1).findViewById(R.id.first_WinSlot);
            textView_wins.setText(player.getGamesWon());
            TextView textView_losses = (TextView)rootView.findViewById(R.id.tableRow1).findViewById(R.id.first_LoseSlot);
            textView_losses.setText(player.getGamesLost());
            TextView textView_highScore = (TextView)rootView.findViewById(R.id.tableRow1).findViewById(R.id.first_HsSlot);
            textView_highScore.setText(player.getHighScore());
            TextView textView_moveTime = (TextView)rootView.findViewById(R.id.tableRow1).findViewById(R.id.first_timeMakeMove);
            textView_moveTime.setText(player.getAverageMoveTime());
            TextView textView_gameTime = (TextView)rootView.findViewById(R.id.tableRow1).findViewById(R.id.first_timeWSlot);
            textView_gameTime.setText(player.getAverageGameTime());
       }

stat.close();

        return rootView;

    }

    */
        @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.statistics_layout);

            stat = new Statistics(this);
            stat.open();
            // for(Player player : stat.getEntries()) {

            // for(int y = 0; y<stat.getEntries().length; y++){




/*

        TextView textView_playerName = (TextView) findViewById(R.id.statistic_playerName1);
        System.out.println("it's saved" + stat.getEntries()[0]);
        textView_playerName.setText(stat.getEntries()[0].toString());
        TextView textView_playerName2 = (TextView) findViewById(R.id.statistic_playerName2);
        System.out.println("it's saved" + stat.getEntries()[1]);
        textView_playerName2.setText(stat.getEntries()[1].toString());
*/

        TextView textView_playerName = (TextView) findViewById(R.id.statistic_playerName1);
        textView_playerName.setText(stat.getEntries()[0].toString());
        TextView textView_Win1 = (TextView) findViewById(R.id.first_WinSlot);
        textView_Win1.setText(Integer.toString(stat.getEntries()[0].getGamesWon()));
            TextView textView_los2 = (TextView) findViewById(R.id.first_LoseSlot);
            textView_los2.setText(Integer.toString(stat.getEntries()[0].getGamesWon()));




        TextView textView_playerName2 = (TextView) findViewById(R.id.statistic_playerName2);
        textView_playerName2.setText(stat.getEntries()[1].toString());
            TextView textView_Win2 = (TextView) findViewById(R.id.second_WinSlot);
            textView_Win2.setText(Integer.toString(stat.getEntries()[1].getGamesWon()));
            TextView textView_los3 = (TextView) findViewById(R.id.second_LoseSlot);
            textView_los3.setText(Integer.toString(stat.getEntries()[1].getGamesWon()));






                stat.close();


            }

        }















