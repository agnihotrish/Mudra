package com.agnihotrish.mudra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.IOException;

public class main2 extends ActionBarActivity {

    //public static Activity fa2;
    public MediaPlayer mp = new MediaPlayer();

    public SharedPreferences sharepref;
    public static final String SCORE_FILE = "scorefile";
    int music;
    int SFX;
    String name1,name2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main2);

        // fa2 = this;

        ////////////first run stuff////////////////

        SharedPreferences userPrefs = getSharedPreferences("UserPrefs", 0);
        Boolean firstUse = userPrefs.getBoolean("firstUse2", true);

        if(firstUse)
        {



            Intent inte=new Intent(this,help2.class);

            startActivity(inte);
            // overridePendingTransition (0,0 );
            overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);


            //Toast.makeText(MainActivity.this, "First time use!!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = userPrefs.edit();
            editor.putBoolean("firstUse2", false);
            editor.commit();
        }

        //////////first run ends here///////////////



        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();
        music= sharepref.getInt("music", 1); //enabled by default
        SFX=sharepref.getInt("SFX",1);



        ////ads////////////

        final AdView mAdView = (AdView) findViewById(R.id.adView3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //ads end here//////





        final TextView txt=(TextView)findViewById(R.id.leveltxt);
        Button butsub = (Button) findViewById(R.id.start);
        Button butstat=(Button)findViewById(R.id.butstat);

        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/samar.TTF");
        txt.setTypeface(tf);


        final EditText etname1 = (EditText) findViewById(R.id.etname1);
        final EditText etname2 = (EditText) findViewById(R.id.etname2);


        etname1.setOnFocusChangeListener( new View.OnFocusChangeListener() {

            public void onFocusChange( View v, boolean hasFocus ) {
                if( hasFocus ) {
                    mAdView.setVisibility(View.GONE);
                    etname1.setText( "", TextView.BufferType.EDITABLE );
                }

                else
                {

                    if((etname1.getText().toString()).equals(""))
                        etname1.setText( "Player 1", TextView.BufferType.EDITABLE );
                }
            }

        } );

        etname2.setOnFocusChangeListener( new View.OnFocusChangeListener() {

            public void onFocusChange( View v, boolean hasFocus ) {
                if( hasFocus ) {
                    mAdView.setVisibility(View.GONE);
                    etname2.setText( "", TextView.BufferType.EDITABLE );
                }

                else
                {

                    if((etname2.getText().toString()).equals(""))
                        etname2.setText( "Player 2", TextView.BufferType.EDITABLE );
                }
            }

        } );

////touch to remove focus////////////

        FrameLayout touchInterceptor = (FrameLayout)findViewById(R.id.touchInterceptor);
        touchInterceptor.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (etname1.isFocused() || etname2.isFocused()) {
                        Rect outRect = new Rect();
                        etname1.getGlobalVisibleRect(outRect);
                        etname2.getGlobalVisibleRect(outRect);
                        if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                            etname1.clearFocus();
                            etname2.clearFocus();
                            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                            mAdView.setVisibility(View.VISIBLE);

                        }
                    }
                }
                return false;
            }
        });

    }



    public void OnSubmit(View view) {

        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();
        music= sharepref.getInt("music", 1); //enabled by default
        SFX=sharepref.getInt("SFX",1);



        //analytics//

        Tracker t = ((AnalyticsHelper) getApplication()).getTracker(AnalyticsHelper.TrackerName.APP_TRACKER);
        t.send(new HitBuilders.EventBuilder()
                .setCategory("Button")
                .setAction("Click")
                .setLabel("2Players")
                .build());
        //analytics ends//


        ///sounds//
        if(SFX==1) {
            if (mp.isPlaying()) {
                mp.stop();
            }

            try {
                mp.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("butt.mp3");
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //sounds end//


        EditText etname1 = (EditText) findViewById(R.id.etname1);
        EditText etname2 = (EditText) findViewById(R.id.etname2);

        etname1.clearFocus();
        etname2.clearFocus();

        if((etname1.getText().toString()).equals(""))
            etname1.setText( "Player 1", TextView.BufferType.EDITABLE );

        if((etname2.getText().toString()).equals(""))
            etname2.setText( "Player 2", TextView.BufferType.EDITABLE );


        name1 = etname1.getText().toString();
        name2 = etname2.getText().toString();

        Bundle extras = new Bundle();

        Intent inte=new Intent(this,player2.class);
        extras.putString("n1",name1);
        extras.putString("n2",name2);
        inte.putExtras(extras);
        startActivity(inte);
        // overridePendingTransition (0,0 );
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        EditText etname1 = (EditText) findViewById(R.id.etname1);
        EditText etname2 = (EditText) findViewById(R.id.etname2);

        etname1.clearFocus();
        etname2.clearFocus();

        switch (item.getItemId()) {
            case R.id.action_about:
                // About option clicked.

                final Intent intest=new Intent(this,egg.class);
                startActivity(intest);
                overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

                return true;

            case R.id.action_settings:
                // Settings option clicked.

                final Intent intestz=new Intent(this,options.class);
                startActivity(intestz);
                overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }



    }


    public void GoStat(View view)
    {

        EditText etname1 = (EditText) findViewById(R.id.etname1);
        EditText etname2 = (EditText) findViewById(R.id.etname2);

        etname1.clearFocus();
        etname2.clearFocus();

        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();
        music= sharepref.getInt("music", 1); //enabled by default
        SFX=sharepref.getInt("SFX",1);

        ///sounds//
        if(SFX==1) {
            if (mp.isPlaying()) {
                mp.stop();
            }

            try {
                mp.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("butt.mp3");
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //sounds end//


        Intent intest=new Intent(this,stats2.class);
        startActivity(intest);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }


    public void gohelp(View view) {


        EditText etname1 = (EditText) findViewById(R.id.etname1);
        EditText etname2 = (EditText) findViewById(R.id.etname2);

        etname1.clearFocus();
        etname2.clearFocus();

        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();
        music= sharepref.getInt("music", 1); //enabled by default
        SFX=sharepref.getInt("SFX",1);
        ///sounds//
        if(SFX==1) {
            if (mp.isPlaying()) {
                mp.stop();
            }

            try {
                mp.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("butt.mp3");
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //sounds end//

        Intent inte=new Intent(this,help2.class);

        startActivity(inte);
        // overridePendingTransition (0,0 );
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }




    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
    }



}
