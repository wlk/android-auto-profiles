package com.devcamp.provider;

import com.devcamp.provider.ProfileContract.Profile;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ProfileDatabase extends SQLiteOpenHelper{

  private static final String DB_NAME = "profiles.db";
  private static final int CURRENT_VERSION = 1;

  public ProfileDatabase(Context context) {
    super(context, DB_NAME, null, CURRENT_VERSION);
    Log.d("xxx", "create");
  }
  
  @Override
  public void onCreate(SQLiteDatabase db) {
    createTables(db);
  }
  
  interface Tables {
    String PROFILES = "profiles";
  }

  private void createTables(SQLiteDatabase db) {
    db.execSQL(
        "CREATE TABLE " + Tables.PROFILES + "( "
        + Profile._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + Profile.NAME + " TEXT, "
        + Profile.START + " INTEGER, "
        + Profile.STOP + " INTEGER, "
        + Profile.LONGITUDE + " INTEGER, "
        + Profile.LATITUDE + " INTEGER, "
        + Profile.RADIUS + " INTEGER, "
        + Profile.PRIORITY + " INTEGER, "
        + Profile.MODE + " INTEGER, "
        + Profile.IS_IN_LOCATION + " INTEGER DEFAULT 1, "
        + Profile.IS_IN_TIME + " INTEGER DEFAULT 1"
        + ")"
        );
    
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.d("xxx", "upgrade");
    
  }
  
  

}
