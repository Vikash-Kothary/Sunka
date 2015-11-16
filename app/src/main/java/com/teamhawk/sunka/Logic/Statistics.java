package com.teamhawk.sunka.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Point;
import android.util.Log;

import com.teamhawk.sunka.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mikey on 13/11/2015.
 */
public class Statistics {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME ="statisticsDatabase";
    private static final String DATABASE_TABLE ="highScoreTable";

    private static final String KEY_ID = "ID";
    private static final String KEY_PLAYERNAME ="player_name";
    private static final String KEY_WIN = "number_of_wins";
    private static final String KEY_LOSE = "number_of_losses";
    private static final String KEY_DRAW = "number_of_draws";
    private static final String KEY_HS = "high_score";
    private static final String KEY_AT = "average_time_taken_for_move";
    private static final String KEY_ATT = "average_total_game_time";

    private DatabaseHandler mHandler;
    private final Context mContext;
    private SQLiteDatabase mDatabase;

    private static class DatabaseHandler extends SQLiteOpenHelper{
        public DatabaseHandler(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);

        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            String sql = "CREATE TABLE " + DATABASE_TABLE + " (";
                sql += KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT";
                sql += ", " + KEY_PLAYERNAME + " TEXT NOT NULL";
                sql += ", " + KEY_WIN + " INTEGER ";
                sql += ", " + KEY_LOSE + " INTEGER";
                sql += ", " + KEY_DRAW + " INTEGER";
                sql += ", " + KEY_HS + " INTEGER";
                sql += ", " + KEY_AT + " INTEGER";
                sql += ", " + KEY_ATT + " INTEGER";
            sql += ");";
//            Log.e(MainActivity.TAG, sql);
            db.execSQL(sql);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String sql = "DROP TABLE IF EXISTS "+DATABASE_TABLE;
            db.execSQL(sql);
            onCreate(db);
        }
    }

    public Statistics(Context context){

        mContext = context;
    }

    public Statistics open(){
        mHandler = new DatabaseHandler(mContext);
        mDatabase = mHandler.getWritableDatabase();
        return this;
    }

    public void close(){
        mHandler.close();
    }

    public void createEntry(Player player){
        ContentValues cv = new ContentValues();
        cv.put(KEY_PLAYERNAME, player.getPlayerName());

        System.out.println("name created"+player.getPlayerName());
        cv.put(KEY_WIN, player.getGamesWon());
        cv.put(KEY_LOSE, player.getGamesLost());
        cv.put(KEY_DRAW, player.getGamesDrawn());
        cv.put(KEY_HS, player.getHighScore());
        cv.put(KEY_AT, player.getAverageMoveTime());
        cv.put(KEY_ATT, player.getAverageGameTime());

         mDatabase = mHandler.getWritableDatabase();

         mDatabase.insert(DATABASE_TABLE, null, cv);
System.out.println(player.getPlayerName());
        mDatabase.close();

    }

    public Player[] getEntries() {
        String[] columns = {KEY_ID, KEY_PLAYERNAME, KEY_WIN, KEY_LOSE, KEY_DRAW, KEY_HS, KEY_AT, KEY_ATT};
        Cursor c = mDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        ArrayList<Player> entryList = new ArrayList<>();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            Player player = new Player(null);
            player.setPlayerName(c.getString(c.getColumnIndex(KEY_PLAYERNAME)));
            player.setGamesWon(c.getInt(c.getColumnIndex(KEY_WIN)));
            player.setGamesLost(c.getInt(c.getColumnIndex(KEY_LOSE)));
            player.setGamesDrawn(c.getInt(c.getColumnIndex(KEY_DRAW)));
            player.setHighScore(c.getInt(c.getColumnIndex(KEY_HS)));
            player.setAverageMoveTimeInSeconds(c.getInt(c.getColumnIndex(KEY_AT)));
            player.setAverageGameTimeInSeconds(c.getInt(c.getColumnIndex(KEY_ATT)));
            entryList.add(player);
        }
        Player[] entries = new Player[entryList.size()];
        for (int i=0;i<entryList.size();i++) entries[i] = entryList.get(i);
        return entries;
    }

    public void deleteEntry(String playerName){
        mDatabase.execSQL("DELETE FROM " + DATABASE_TABLE + " WHERE " + KEY_PLAYERNAME + "=\"" + playerName + "\";");
    }

    public void updateEntry(Player player){
        ContentValues cv = new ContentValues();
        cv.put(KEY_PLAYERNAME, player.getPlayerName());
        cv.put(KEY_WIN, player.getGamesWon());
        cv.put(KEY_LOSE, player.getGamesLost());
        cv.put(KEY_DRAW, player.getGamesDrawn());
        cv.put(KEY_HS, player.getHighScore());
        cv.put(KEY_AT, player.getAverageMoveTime());
        cv.put(KEY_ATT, player.getAverageGameTime());
        mDatabase.update(DATABASE_TABLE, cv, KEY_PLAYERNAME + "="+ player.getPlayerName(), null);
    }
}
