package com.devcamp;

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
			priority.setText(c.getString(c.getColumnIndex(Profile.PRIORITY)));
		}
		else{//puste pola
			
		}
		
	}

	public void onClick(View v) {
		
	}

}
