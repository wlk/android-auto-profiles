package com.devcamp.provider;

import com.devcamp.ProfileService;
import com.devcamp.provider.ProfileContract.Profile;
import com.devcamp.provider.ProfileDatabase.Tables;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class ProfileProvider extends ContentProvider {

  private SQLiteOpenHelper mDatabase;

  private static final UriMatcher sUriMatcher = buildUriMatcher();

  private static final int PROFILES = 1;

  private static final int PROFILES_ID = 2;

  private SQLiteDatabase db;

  @Override
  public boolean onCreate() {
    final Context context = getContext();
    mDatabase = new ProfileDatabase(context);
    db = mDatabase.getWritableDatabase();
    return true;
  }

  private static UriMatcher buildUriMatcher() {
    final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

    matcher.addURI(ProfileContract.AUTHORITY, "profiles", PROFILES);
    matcher.addURI(ProfileContract.AUTHORITY, "profiles/#", PROFILES_ID);
    return matcher;
  }

  @Override
  public int delete(Uri uri, String selection, String[] selectionArgs) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getType(Uri uri) {
    int match = sUriMatcher.match(uri);
    switch (match) {
    case PROFILES:
      return Profile.CONTENT_TYPE;

    case PROFILES_ID:
      return Profile.CONTENT_ITEM_TYPE;

    }
    return null;
  }

  @Override
  public Uri insert(Uri uri, ContentValues values) {

    switch (sUriMatcher.match(uri)) {
    case PROFILES:
      db.insertOrThrow(Tables.PROFILES, null, values);
      break;

    default:
      break;
    }

    return null;

  }

  @Override
  public Cursor query(Uri uri, String[] projection, String selection,
      String[] selectionArgs, String sortOrder) {
    SQLiteDatabase db = mDatabase.getReadableDatabase();

    switch (sUriMatcher.match(uri)) {
    case PROFILES:
      return db.query(Tables.PROFILES, projection, selection, selectionArgs, null, null,
          sortOrder);

    case PROFILES_ID:
      long id = ContentUris.parseId(uri);
      return db.query(Tables.PROFILES, projection, Profile._ID + "=?",
          new String[] { String.valueOf(id) }, null, null, sortOrder);

    default:
      break;
    }
    return null;
  }

  @Override
  public int update(Uri uri, ContentValues values, String selection,
      String[] selectionArgs) {

    switch (sUriMatcher.match(uri)) {
    case PROFILES_ID:
      long id = ContentUris.parseId(uri);
      return db.update(Tables.PROFILES, values, Profile._ID + "=?",
          new String[] { String.valueOf(id) });

    }
    return 0;

  }
}
