package com.teamhawk.sunka.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.teamhawk.sunka.R;
import com.teamhawk.sunka.logic.Game;

import java.io.IOException;

/**
 * A placeholder fragment containing a simple view.
 */
public class OnlinePlayerNamingFragment extends Fragment implements View.OnClickListener {

    private EditText editView_name;
    private static final int portNumber = 1025;

    public OnlinePlayerNamingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_naming_online, container, false);

        editView_name = (EditText) rootView.findViewById(R.id.onlinePlayerName_Edit);

        Button htn_host = (Button) rootView.findViewById(R.id.button_host);
        htn_host.setOnClickListener(this);
        Button htn_join = (Button) rootView.findViewById(R.id.button_join);
        htn_join.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        String playerName = editView_name.getText().toString();
        intent.putExtra(Game.PLAYER1, playerName);

        SocketAsyncTask socket = null;
        try {
            socket = new SocketAsyncTask(v.getId()== R.id.button_host);
            socket.execute("");
            if(socket.getMultiplayer()!=null){
                Log.e(MainActivity.TAG, "multiplayer exists");
            }
//                    .setData(playerName);
//            intent.putExtra(Game.PLAYER2, socket.opponent.getResult() );
//            socket.opponent.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        startActivity(intent);
    }
}
