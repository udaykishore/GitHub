package com.qualcomm.platformtools.dragonwear;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Settings extends Activity {

    public static final String SHARED_PREFERENCE_BG = "shared_preference_bg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        final SettingsObj settings = new SettingsObj();
        ListView listView = (ListView) findViewById( R.id.list );
        ArrayAdapter<String> adapter = new ArrayAdapter( this, android.R.layout.simple_list_item_1, settings.settingsList );
        listView.setAdapter( adapter );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences pref = getSharedPreferences(WatchFace.SHARED_PREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString(SHARED_PREFERENCE_BG, settings.settingsCodeList.get(position));
                editor.commit();
                finish();
            }

        });
    }
    public class SettingsObj {
        public List<String> settingsList = new ArrayList<String>();
        public List<String> settingsCodeList = new ArrayList<String>();

        public SettingsObj() {
            settingsList.add( "Eye BG" );
            settingsCodeList.add( "Eye" );
            settingsList.add( "Logo BG" );
            settingsCodeList.add( "Logo" );
        }
    }

}
