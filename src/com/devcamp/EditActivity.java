package com.devcamp;

import android.content.ContentValues;
import android.widget.Button;
import com.devcamp.provider.ProfileContract.Profile;

import android.app.Activity;
import android.app.AlarmManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.devcamp.provider.ProfileProvider;

public class EditActivity extends Activity implements OnClickListener {
	EditText start_time;
	EditText end_time;
	EditText radius;
	Spinner mode; // aby pobrać użyj getPosition lub coś podobnego
	EditText priority;
	EditText latitude;
	EditText longtitude;
	
	Cursor c;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.edit_layout);

		Intent i = getIntent();

		start_time = (EditText) findViewById(R.id.start_time);
		end_time = (EditText) findViewById(R.id.end_time);
		radius = (EditText) findViewById(R.id.radius);
		priority = (EditText) findViewById(R.id.priority);
		latitude = (EditText) findViewById(R.id.latitude);
		longtitude = (EditText) findViewById(R.id.longtitude);
		mode = (Spinner) findViewById(R.id.mode);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.priorities, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mode.setAdapter(adapter);

		if (i.getAction().equals(Intent.ACTION_EDIT)) { // wypełnij pola
			Uri u = i.getData();
			c = managedQuery(u, null, null, null, null);
			c.moveToFirst();
			start_time.setText(c.getString(c.getColumnIndex(Profile.START)));
			end_time.setText(c.getString(c.getColumnIndex(Profile.STOP)));
			latitude.setText(c.getString(c.getColumnIndex(Profile.LATITUDE)));
			longtitude.setText(c.getString(c.getColumnIndex(Profile.LONGITUDE)));
			radius.setText(c.getString(c.getColumnIndex(Profile.RADIUS)));

			String modeString = c.getString(c.getColumnIndex(Profile.MODE));
			Integer m = Integer.valueOf(modeString);
			mode.setSelection(m);

			priority.setText(c.getString(c.getColumnIndex(Profile.PRIORITY)));

            c.close();
		} else {// puste pola
			
		}

		final Button b = (Button) findViewById(R.id.save_profile_button);
		b.setOnClickListener(this);
	}

	public void onClick(View v) {
		saveProfile();
		finish();
	}

	public void setProfileListeners() {

	}

	public void saveProfile() {
        Intent i = getIntent();
        Uri u = i.getData();

        ContentValues values = new ContentValues();
        
        
        values.put(Profile.START, start_time.getText().toString());
        values.put(Profile.STOP, end_time.getText().toString());
        values.put(Profile.LATITUDE, latitude.getText().toString());
        values.put(Profile.LONGITUDE, longtitude.getText().toString());
        values.put(Profile.RADIUS, radius.getText().toString());
        values.put(Profile.MODE, mode.getSelectedItemPosition());

        //ProfileProvider provider = new ProfileProvider();
        ContentResolver provider = getContentResolver();

        if(i.getAction().equals(Intent.ACTION_INSERT)){
            provider.insert(u, values);
        } 
        if(i.getAction().equals(Intent.ACTION_EDIT)){
        	provider.update(u, values, null, null); //TODO czy tu ma być null, null ?
        }
        
        //TODO przy zmianie stanu zarejestrować observery

        setProfileListeners();
    }

//	private void registerLocationObserver(double longitude, double latitude, int r) {
//		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//		locationManager.addProximityAlert(latitude, longitude, r, -1, proximityIntent);
//		IntentFilter filter = new IntentFilter(PROX_ALERT_INTENT);
//		registerReceiver(new ProximityIntentReceiver(), filter);
//	}
//
//	private void registerTimeObserver(String start_time, String end_time) {
//		Intent myIntent = new Intent(AndroidAlarmService.this, MyAlarmService.class);
//		pendingIntent = PendingIntent.getService(AndroidAlarmService.this, 0, myIntent, 0);
//
//		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTimeInMillis(System.currentTimeMillis());
//		calendar.add(Calendar.SECOND, 10);
//		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 5 * 1000, pendingIntent);
//	}

}
