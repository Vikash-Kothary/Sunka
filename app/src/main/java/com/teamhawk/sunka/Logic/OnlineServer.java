package com.teamhawk.sunka.logic;

import android.util.Log;

import com.teamhawk.sunka.ui.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Vikash Kothary on 15-Nov-15.
 */
public class OnlineServer extends OnlineMultiplayer {



    public OnlineServer() {
    }

    @Override
    public OnlineServer open() throws IOException {
        serverSocket = new ServerSocket(portNumber);
        Log.e(MainActivity.TAG, "new Server");
        clientSocket = serverSocket.accept();
        Log.e(MainActivity.TAG, "new Client");
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        return this;
    }




}
