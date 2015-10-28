package com.teamhawk.sunka.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.teamhawk.sunka.logic.Game;
import com.teamhawk.sunka.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class OnlinePlayerNamingFragment extends Fragment implements View.OnClickListener {

    private EditText editView_name;

    public OnlinePlayerNamingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_naming_online, container, false);

        editView_name = (EditText) rootView.findViewById(R.id.onlinePlayerName_Edit);

        Button btn_submit = (Button) rootView.findViewById(R.id.submit_Button);
        btn_submit.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit_Button:
                Intent intent_singlep = new Intent(getActivity(), MainActivity.class);
                String playerName = editView_name.getText().toString();
                intent_singlep.putExtra(Game.PLAYER1, playerName);
                startActivity(intent_singlep);
                break;
        }
    }
}
