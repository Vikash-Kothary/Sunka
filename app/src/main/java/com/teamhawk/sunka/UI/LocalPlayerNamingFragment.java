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
public class LocalPlayerNamingFragment extends Fragment implements View.OnClickListener {

    private EditText editText_name1;
    private EditText editText_name2;

    private String name;
    private String name2;
    public String getName2() {
        return name2;
    }

    public String getName() {
        return name;
    }

    public LocalPlayerNamingFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player_naming_local, container, false);

        editText_name1 = (EditText) rootView.findViewById(R.id.player1Name_Edit);
        editText_name2 = (EditText) rootView.findViewById(R.id.player2Name_Edit);

        Button btn_confirm = (Button) rootView.findViewById(R.id.confirm_Button);
        btn_confirm.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm_Button:
                Intent intent_singlep = new Intent(getActivity(), MainActivity.class);



                name = editText_name1.getText().toString();
                intent_singlep.putExtra(Game.PLAYER1, name);

                name2 = editText_name2.getText().toString();
                intent_singlep.putExtra(Game.PLAYER2, name2);

                startActivity(intent_singlep);
                break;
        }
    }
}
