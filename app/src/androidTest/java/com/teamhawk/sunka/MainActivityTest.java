package com.teamhawk.sunka;

import android.test.ActivityInstrumentationTestCase2;

import com.teamhawk.sunka.ui.MainActivity;


// NEED ALL THIS FOR INSTRUMENTATION TESTING SEE KEATS LECTURE ON IT

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivityExists() {
        MainActivity mainActivity = getActivity();
        assertNotNull(mainActivity);
    }

}
