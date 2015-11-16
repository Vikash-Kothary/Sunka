package com.teamhawk.sunka.ui;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import com.teamhawk.sunka.logic.OnlineClient;
import com.teamhawk.sunka.logic.OnlineMultiplayer;
import com.teamhawk.sunka.logic.OnlineServer;

import java.io.IOException;

/**
 * Created by Vikash Kothary on 15-Nov-15.
 */
public class SocketAsyncTask extends AsyncTask<String, String, OnlineMultiplayer> {

    private boolean isHost;
    private OnlineMultiplayer opponent;
    private String userInput;
    private String result;

    public SocketAsyncTask(boolean host) throws IOException {
        this.isHost = host;
    }

    @Override
    protected OnlineMultiplayer doInBackground(String... params) {
        Log.e(MainActivity.TAG, "background");
        try {
            if(isHost){
                host();
            }else{
                join();
            }
        } catch (IOException e) {

            Log.e(MainActivity.TAG, "error: " + e.toString());
        }
        try {
            logic();
        } catch (IOException e) {
            Log.e(MainActivity.TAG, "error2: " + e.toString());
        }
        return opponent;
    }

    @Override
    protected void onPostExecute(OnlineMultiplayer onlineMultiplayer) {
        super.onPostExecute(onlineMultiplayer);

    }

    private void host() throws IOException {
//        if(opponent==null)
            opponent = new OnlineServer();
    }

    private void join() throws IOException {
//        if(opponent==null)
            opponent = new OnlineClient();
    }

    private void logic() throws IOException {
        opponent.open();
        Log.e(MainActivity.TAG, "open");
        while(opponent.hasNextLine()){
            result = opponent.getResult();
            Log.e(MainActivity.TAG, "result: "+result);
            opponent.setData(userInput);
            Log.e(MainActivity.TAG, "input: "+userInput);
            if(opponent.checkEnd()) break;
        }
    }

    public void close() throws IOException {
        if(opponent!=null){
            opponent.close();
        }
    }

    public OnlineMultiplayer getMultiplayer(){
        return opponent;
    }
}
