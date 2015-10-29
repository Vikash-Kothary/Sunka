package com.teamhawk.sunka.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.teamhawk.sunka.logic.*;
import com.teamhawk.sunka.logic.Player;
import com.teamhawk.sunka.R;
import com.teamhawk.sunka.ui.*;

/**
 * Created by zapatacajas on 27/10/2015.
 */
public class Statistics extends Fragment implements View.OnClickListener {


    private Player playerFirst;
    private Player playerSecond;
    private int Player1WinCounter;
    private int Player1LoseCounter;
    private int Player2WinCounter;
    private int Player2LoseCounter;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View rootView = inflater.inflate(R.layout.statistics_layout, container, false);



        LocalPlayerNamingFragment localP = new LocalPlayerNamingFragment();

        TextView names1 = (TextView) rootView.findViewById(R.id.statistic_playerName);

        String h =  localP.getName();

        names1.setText(h);

        TextView names2 = (TextView) rootView.findViewById(R.id.statistic_playerName2);


        String x =  localP.getName2();

        names2.setText(x);





        return rootView;


    }



    @Override
    public void onClick(View v) {


    }


/*
    if(player1>player2){

        Player1WinCounter++;
        Player2LoseCounter++;
    }
    if(player1<player2){

        Player2WinCounter++;
        Player1LoseCounter++;
    }



*/






}
