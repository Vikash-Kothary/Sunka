package com.teamhawk.sunka.logic;

import android.util.Log;

import com.teamhawk.sunka.ui.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Vikash Kothary on 15-Nov-15.
 */
public abstract class OnlineMultiplayer {
    protected static final String END = "END.";
    protected static final String WAITING = "WAITING.";
    protected String fromOpponent, fromUser = null;
    protected ServerSocket serverSocket;
    protected Socket clientSocket;
    protected PrintWriter out;
    protected BufferedReader in;

    protected int portNumber=4444;
    protected String ipAddress="10.0.2.2";
    private Object result;

    public OnlineMultiplayer(){
        serverSocket=null;
        clientSocket=null;
        out=null;
        in=null;
    }

    public abstract OnlineMultiplayer open() throws IOException;

    public void close() throws IOException{
        if(serverSocket!=null){
            serverSocket.close();
        }
        clientSocket.close();
        out.close();
        in.close();
    }

    public boolean checkEnd(){
        return fromUser.equals(END);
    }

    public String getResult() throws IOException {
        return fromOpponent;
    }

    public void setData(String userInput){
        if(userInput!=null){
            fromUser = userInput;
            Log.e(MainActivity.TAG, fromUser);
            out.println(fromUser);
        }
    }

    public boolean hasNextLine() throws IOException {
        fromOpponent = in.readLine();
        Log.e(MainActivity.TAG, fromOpponent);
        return fromOpponent!=null;
    }

//    public void logic() throws IOException {
//        String line;
//        while (!(line = in.readLine()).equals(END)) {
//            if(!line.equals(WAITING)){
//                resultFromOpponent=true;
//                fromOpponent = line;
//            }
//            System.out.println("Server: " + fromOpponent);
//
//            fromUser = getData();
//            if (fromUser != WAITING) {
//                System.out.println("Client: " + fromUser);
//                out.println(fromUser);
//            }
//        }
//    }
//
//    public String getData() {
//        return fromUser;
//    }
//
//    public void setData(String data){
//        this.fromUser = data;
//    }
//
//    public String getResult(){
//        return fromOpponent;
//    }
//
//    public boolean onResult(){
//        Log.e(MainActivity.TAG, "Result");
//        return resultFromOpponent;
//    }
}
