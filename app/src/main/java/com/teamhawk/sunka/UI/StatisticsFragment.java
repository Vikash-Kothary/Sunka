package com.teamhawk.sunka.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamhawk.sunka.R;
import com.teamhawk.sunka.logic.Player;
import com.teamhawk.sunka.logic.Statistics;

/**
 * Created by mikey on 12/11/2015.
 */
public class StatisticsFragment extends Fragment {

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
}















