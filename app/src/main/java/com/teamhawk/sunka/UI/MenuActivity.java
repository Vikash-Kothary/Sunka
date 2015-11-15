package com.teamhawk.sunka.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.teamhawk.sunka.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new MenuFragment()).commit();

        }
    }

}
