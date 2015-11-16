package com.teamhawk.sunka.logic;

import android.util.Log;

import com.teamhawk.sunka.ui.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Vikash Kothary on 15-Nov-15.
 */
public class OnlineClient extends OnlineMultiplayer {


    private String userName;

    public OnlineClient(){
    }

    @Override
    public OnlineClient open() throws IOException {
        clientSocket = new Socket(ipAddress, portNumber);
        Log.e(MainActivity.TAG, "new Socket");
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return this;
    }




}
