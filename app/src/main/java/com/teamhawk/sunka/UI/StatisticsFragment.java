package com.teamhawk.sunka.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teamhawk.sunka.R;

/**
 * Created by mikey on 12/11/2015.
 */
public class StatisticsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);

        TextView names1 = (TextView) rootView.findViewById(R.id.statistic_playerName);
        TextView names2 = (TextView) rootView.findViewById(R.id.statistic_playerName2);

        return rootView;

    }
}















