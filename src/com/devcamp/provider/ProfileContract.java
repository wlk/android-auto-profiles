package com.devcamp.provider;

import android.net.Uri;
import android.provider.BaseColumns;

public class ProfileContract {

  /** The authority for the Profile provider */
  public static final String AUTHORITY = "com.devcamp.provider.profiles";

  public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);

  public static final String PATH_PROFILES = "profiles";

  interface ProfileColumns {
    String NAME = "name";
    String START = "start";
    String STOP = "stop";
    String LONGITUDE = "longitude";
    String LATITUDE = "latitude";
    String RADIUS = "radius";
    String MODE = "mode";
    String PRIORITY = "priority";
  }

  public static class Profile implements ProfileColumns, BaseColumns {
    
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.devcamp.profile";
    public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.devcamp.profile";
    
    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
        .appendPath(PATH_PROFILES).build();
    
    public static final Uri buildProfileUri(long id){
      return CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build();
    }
    
    

  }

}
