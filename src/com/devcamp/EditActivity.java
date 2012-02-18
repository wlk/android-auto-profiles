package com.devcamp;

import android.content.ContentValues;
import android.widget.Button;
import com.devcamp.provider.ProfileContract.Profile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Spinner;
import com.devcamp.provider.ProfileProvider;

public class EditActivity extends Activity implements OnClickListener {
	EditText start_time;
	EditText end_time;
	EditText radius;
	Spinner mode;
	EditText priority;
	EditText latitude;
	EditText longtitude;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_layout);
		Intent i = getIntent();
		
		start_time = (EditText)findViewById(R.id.start_time);
		
		if(i.getAction().equals(Intent.ACTION_EDIT)){ // wypełnij pola
			Uri u = i.getData();
			Cursor c = managedQuery(u, null, null, null, null);
			c.moveToFirst();
			start_time.setText(c.getString(c.getColumnIndex(Profile.START)));
			end_time.setText(c.getString(c.getColumnIndex(Profile.STOP)));
			c.getString(c.getColumnIndex(Profile.LATITUDE));
			c.getString(c.getColumnIndex(Profile.LONGITUDE));
			radius.setText(c.getString(c.getColumnIndex(Profile.RADIUS)));
			c.getString(c.getColumnIndex(Profile.MODE));
			//priority.setText(c.getString(c.getColumnIndex(Profile.PRIORITY)));
		}
		else{//puste pola
			
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

        ProfileProvider provider = new ProfileProvider();

        if(i.getAction().equals(Intent.ACTION_EDIT)){
            provider.insert(u, values);
        } else {
            provider.update(u, values);
        }

        setProfileListeners();
    }

}
