package com.teamhawk.sunka.ui;

import android.os.Parcel;
import android.os.Parcelable;

import com.teamhawk.sunka.logic.Game;

/**
 * Created by Vikash Kothary on 14-Nov-15.
 */
public class GameSaveState implements Parcelable {

    public static final String KEY = "Save state";
    private Game game;

    public GameSaveState(Game game){
        this.game = game;
    }

    public Game getGame(){
        return game;
    }

    protected GameSaveState(Parcel in) {
    }

    public static final Creator<GameSaveState> CREATOR = new Creator<GameSaveState>() {
        @Override
        public GameSaveState createFromParcel(Parcel in) {
            return new GameSaveState(in);
        }

        @Override
        public GameSaveState[] newArray(int size) {
            return new GameSaveState[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
