package com.teamhawk.sunka.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.teamhawk.sunka.R;
import com.teamhawk.sunka.logic.AIPlayer;
import com.teamhawk.sunka.logic.Board;
import com.teamhawk.sunka.logic.Game;
import com.teamhawk.sunka.logic.Player;
import com.teamhawk.sunka.logic.Slot;

import java.util.HashMap;

/**
 * Created by mikey on 12/11/2015.
 */
public class StatisticsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.statistics_layout, container, false);

        TextView names1 = (TextView) rootView.findViewById(R.id.statistic_playerName);
        TextView names2 = (TextView) rootView.findViewById(R.id.statistic_playerName2);

        return rootView;

    }
}















