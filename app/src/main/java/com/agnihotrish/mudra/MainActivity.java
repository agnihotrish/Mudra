package com.agnihotrish.mudra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.IOException;

public class MainActivity extends ActionBarActivity {

    //public static Activity fa2;
    public MediaPlayer mp = new MediaPlayer();

    public SharedPreferences sharepref;
    public static final String SCORE_FILE = "scorefile";
    int music;
    int SFX;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);

       // fa2 = this;

        ////////////first run stuff////////////////

        SharedPreferences userPrefs = getSharedPreferences("UserPrefs", 0);
        Boolean firstUse = userPrefs.getBoolean("firstUse", true);

        if(firstUse)
        {



            Intent inte=new Intent(this,help.class);

            startActivity(inte);
            // overridePendingTransition (0,0 );
            overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);


            //Toast.makeText(MainActivity.this, "First time use!!", Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor editor = userPrefs.edit();
            editor.putBoolean("firstUse", false);
            editor.commit();
        }

        //////////first run ends here///////////////



        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();
        music= sharepref.getInt("music", 1); //enabled by default
        SFX=sharepref.getInt("SFX",1);



        ////ads////////////

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //ads end here//////


      


        final TextView txt=(TextView)findViewById(R.id.leveltxt);
        Button butsub = (Button) findViewById(R.id.start);
        Button butstat=(Button)findViewById(R.id.butstat);

        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/samar.TTF");
        txt.setTypeface(tf);






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
                .setLabel("1Player")
                .build());
        //analytics ends//


            int passvalue =1;

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



        RadioButton easy=(RadioButton)findViewById(R.id.easy);
        RadioButton medium=(RadioButton)findViewById(R.id.medium);
        RadioButton difficult=(RadioButton)findViewById(R.id.difficult);

        if(easy.isChecked()==true)
            passvalue=1;
        else if(medium.isChecked()==true)
            passvalue=2;
        else if(difficult.isChecked()==true)
            passvalue=3;



        Intent inte=new Intent(this,dusra.class);
        inte.putExtra("abcd",passvalue);
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


        Intent intest=new Intent(this,stats.class);
        startActivity(intest);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }


    public void gohelp(View view) {

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

        Intent inte=new Intent(this,help.class);

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
