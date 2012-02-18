package com.devcamp;

import com.devcamp.provider.ProfileContract.Profile;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class SettingsActivity extends ListActivity implements OnClickListener {


	static final String[] from = new String[] { Profile.NAME };


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Cursor c = managedQuery(Profile.CONTENT_URI, null, null, null, null);

		View footer = getLayoutInflater().inflate(R.layout.list_footer, null);

		ListView lv = getListView();
		lv.setTextFilterEnabled(true);

		lv.addFooterView(footer);

		final Button b = (Button) findViewById(R.id.add_profile_button);
		b.setOnClickListener(this);

		int[] to = new int[] { R.id.list_item_id };

		setListAdapter(new SimpleCursorAdapter(this, R.layout.list_item, c,
				from, to));

		lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent();
				i.setData(Profile.CONTENT_URI.buildUpon().appendPath(String.valueOf(id)).build());
				i.setAction(Intent.ACTION_EDIT);
				startActivity(i);
			}

		});
	}

	public void onClick(View v) {
		Intent i = new Intent(Intent.ACTION_INSERT, Profile.CONTENT_URI);
		startActivity(i);
		

	}
}