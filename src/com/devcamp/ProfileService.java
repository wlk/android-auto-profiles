package com.devcamp;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.net.Uri;
import android.os.IBinder;


/**
 * Created by IntelliJ IDEA.
 * User: yundt
 * Date: 18.02.2012
 * Time: 15:39
 * To change this template use File | Settings | File Templates.
 */
public class ProfileService extends IntentService {
    private AudioManager amanager;

    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Uri u = intent.getData();
        Cursor c = managedQuery(u, null, null, null, null);
        c.moveToFirst();
    }



    @Override
    public void onStart(Intent intent, int startId) {
        amanager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
        //amanager.setRingerMode(0x00000000);
    }

}
