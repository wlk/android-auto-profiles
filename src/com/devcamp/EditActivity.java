package com.devcamp;

import com.devcamp.provider.ProfileContract.Profile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class EditActivity extends Activity implements OnClickListener {
	EditText start_time;
	EditText end_time;
	EditText radius;
	Spinner mode; //aby pobrać użyj getPosition lub coś podobnego
	EditText priority;
	EditText latitude;
	EditText longtitude;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_layout);
		Intent i = getIntent();

		start_time = (EditText) findViewById(R.id.start_time);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.priorities,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mode.setAdapter(adapter);

		if (i.getAction().equals(Intent.ACTION_EDIT)) { // wypełnij pola
			Uri u = i.getData();
			Cursor c = managedQuery(u, null, null, null, null);
			c.moveToFirst();
			start_time.setText(c.getString(c.getColumnIndex(Profile.START)));
			end_time.setText(c.getString(c.getColumnIndex(Profile.STOP)));
			latitude.setText(c.getString(c.getColumnIndex(Profile.LATITUDE)));
			longtitude.setText(c.getString(c.getColumnIndex(Profile.LONGITUDE)));
			radius.setText(c.getString(c.getColumnIndex(Profile.RADIUS)));
			
			String modeString = c.getString(c.getColumnIndex(Profile.MODE));
			Integer m = Integer.valueOf(modeString);
			mode.setSelection(m);
			
			//priority.setText(c.getString(c.getColumnIndex(Profile.PRIORITY)));
		} else {// puste pola

		}

	}

	public void onClick(View v) {

	}

}
