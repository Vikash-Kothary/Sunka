package com.teamhawk.sunka.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.teamhawk.sunka.logic.Game;
import com.teamhawk.sunka.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MenuFragment extends Fragment implements View.OnClickListener {

    public MenuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        Button btn_singlePlayer = (Button) rootView.findViewById(R.id.singplayer_button);
        Button btn_localPlay = (Button) rootView.findViewById(R.id.localPlay_button);
        Button btn_onlinePlay = (Button) rootView.findViewById(R.id.onlinePlay_button);
       // Button btn_statisticsPlayer = (Button) rootView.findViewById(statistics_button);

        btn_singlePlayer.setOnClickListener(this);
        btn_localPlay.setOnClickListener(this);
        btn_onlinePlay.setOnClickListener(this);
     //   btn_statisticsPlayer.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.singplayer_button:
                // Create fragment and give it an argument specifying the article it should show
//                ArticleFragment newFragment = new ArticleFragment();
//                Bundle args = new Bundle();
//                args.putInt(ArticleFragment.ARG_POSITION, position);
//                newFragment.setArguments(args);

                Intent intent_singlep = new Intent(getActivity(), MainActivity.class);
                intent_singlep.putExtra(Game.PLAYER1, "Player");
                startActivity(intent_singlep);
                break;
            case R.id.localPlay_button:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new LocalPlayerNamingFragment()).addToBackStack(null)
                        .commit();
                break;
            case R.id.onlinePlay_button:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new OnlinePlayerNamingFragment()).addToBackStack(null)
                        .commit();
                break;
           /* case statistics_button:
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new StatisticsActivity()).addToBackStack(null)
                        .commit();
                break;*/
        }
    }
}
