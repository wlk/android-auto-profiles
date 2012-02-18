package com.devcamp;

import android.app.IntentService;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.IBinder;
import com.devcamp.provider.ProfileContract;


/**
 * Created by IntelliJ IDEA.
 * User: yundt
 * Date: 18.02.2012
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class ProfileService extends IntentService {
    private AudioManager amanager;

    public ProfileService(String name) {
        super(name);
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri u = intent.getData();

        ContentResolver provider = getContentResolver();

        String[] proj = {
                ProfileContract.Profile.IS_IN_LOCATION,
                ProfileContract.Profile.IS_IN_TIME
        };

        Cursor c = provider.query(u, proj, null, null, null);
        c.moveToFirst();
        c.getString(c.getColumnIndex(ProfileContract.Profile.START));
    }



    @Override
    public void onStart(Intent intent, int startId) {
        amanager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        //amanager.setRingerMode(0x00000000);
    }

}
