package com.devcamp;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class SettingsActivity extends ListActivity {
    
    
    static final String[] test  = new String[] { "silent", "test", "costam", "dupa"};
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, test));
    }
}