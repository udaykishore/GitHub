package com.qualcomm.platformtools.dragonwear;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.AnalogClock;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.content.Context;
import android.content.SharedPreferences;

public class WatchFace extends Activity {

    public static final String SHARED_PREFERENCE = "shared_preference";

    private ImageView mBackground;
    private TextClock mClock;
    private AnalogClock mAnalogClock;
    private LinearLayout mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_face);

        mBackground = (ImageView) findViewById( R.id.watch_background );
        mContainer = (LinearLayout) findViewById( R.id.watch_container );
        mClock = (TextClock) findViewById( R.id.textClock );
        mAnalogClock = (AnalogClock) findViewById( R.id.analogClock );

    }
    @Override
    protected void onPause() {
        super.onPause();
        mBackground.setImageDrawable( null );
        mClock.setTextColor( getResources().getColor( android.R.color.white ) );
//        mAnalogClock.setBackgroundColor(getResources().getColor( android.R.color.holo_red_light ));
        mContainer.setBackgroundColor( getResources().getColor( android.R.color.black ) );
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences pref = getSharedPreferences( SHARED_PREFERENCE, Context.MODE_PRIVATE );

        if( pref.contains( Settings.SHARED_PREFERENCE_BG ) ) {
            String bgCode = pref.getString( Settings.SHARED_PREFERENCE_BG, "" );
            loadBgWatchFace(bgCode);
        } else {
            mBackground.setImageResource( R.drawable.dragoneye );
            mClock.setTextColor(getResources().getColor(android.R.color.white));
            mContainer.setBackgroundColor(getResources().getColor( android.R.color.black ) );
        }
    }

    private void loadBgWatchFace( String bgCode ) {
        if( "Eye".equals( bgCode ) ) {
            mBackground.setImageResource( R.drawable.dragoneye );
            mClock.setTextColor(getResources().getColor(android.R.color.holo_orange_light));
        } else if( "Logo".equals( bgCode ) ) {
            mBackground.setImageResource( R.drawable.logo );
            mClock.setTextColor(getResources().getColor(android.R.color.holo_blue_bright));
        } else {
            //Default to Eye
            mBackground.setImageResource( R.drawable.dragoneye );
            mClock.setTextColor(getResources().getColor(android.R.color.white));
        }

        mContainer.setBackgroundColor(getResources().getColor(android.R.color.black));
    }
}
