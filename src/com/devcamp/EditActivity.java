package com.devcamp;

import com.devcamp.provider.ProfileContract.Profile;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class EditActivity extends Activity implements OnClickListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_layout);
		Intent i = getIntent();
		
		if(i.getAction().equals(Intent.ACTION_EDIT)){ // wypełnij pola
			Uri u = i.getData();
			Cursor c = managedQuery(u, null, null, null, null);
			c.moveToFirst();
			//c.getString(c.getColumnIndex(Profile.))
		}
		//puste pola
		
	}

	public void onClick(View v) {
		
	}

}
