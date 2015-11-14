package com.teamhawk.sunka.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.graphics.Point;

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
                sql += KEY_ID + "INTEGER PRIMARY KEY AUTOINCREMENT";
                sql += ", " + KEY_PLAYERNAME + "TEXT NOT NULL";
                sql += ", " + KEY_WIN + "INTEGER NOT NULL";
                sql += ", " + KEY_LOSE + "INTEGER NOT NULL";
                sql += ", " + KEY_DRAW + "INTEGER NOT NULL";
                sql += ", " + KEY_HS + "INTEGER NOT NULL";
                sql += ", " + KEY_AT + "INTEGER NOT NULL";
                sql += ", " + KEY_ATT + "INTEGER NOT NULL";
            sql += ");";
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

    public long createEntry(Player player){
        deleteEntry(player.getPlayerName());
        ContentValues cv = new ContentValues();
        cv.put(KEY_PLAYERNAME, player.getPlayerName());
        cv.put(KEY_WIN, player.getGamesWon());
        cv.put(KEY_LOSE, player.getGamesLost());
        cv.put(KEY_DRAW, player.getGamesDrawn());
        cv.put(KEY_HS, player.getHighScore());
        cv.put(KEY_AT, player.getAverageMoveTime());
        cv.put(KEY_ATT, player.getAverageGameTime());
        return mDatabase.insert(DATABASE_TABLE, null, cv);
    }

    public Player[] getEntries() {
        String[] columns = {KEY_PLAYERNAME, KEY_WIN, KEY_LOSE, KEY_DRAW, KEY_HS, KEY_AT, KEY_ATT};
        Cursor c = mDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
        ArrayList<Player> entryList = new ArrayList<>();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            Player player = new Player(null);

            entryList.add(player);
        }
        Player[] entries = new Player[entryList.size()];
        for (int i=0;i<entryList.size();i++) entries[i] = entryList.get(i);
        return entries;
    }

    public void deleteEntry(String playerName){
        mDatabase.execSQL("DELETE FROM " + DATABASE_TABLE + " WHERE " + KEY_PLAYERNAME + "=\"" + playerName + "\";");
    }
}
