package com.devcamp;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class SettingsActivity extends ListActivity {

	static final String[] test = new String[] { "silent", "test", "costam",
			"dupa" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

        View footer = getLayoutInflater().inflate(R.layout.list_footer, null);

        ListView lv = getListView();
        lv.setTextFilterEnabled(true);

        lv.addFooterView(footer);

        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, test));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(getApplicationContext(), EditActivity.class);
				i.putExtra("profile_index", 1);
				
//				Toast.makeText(getApplicationContext(),
//						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}

		});
	}
}