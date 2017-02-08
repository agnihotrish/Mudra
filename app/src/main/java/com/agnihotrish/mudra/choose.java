package com.agnihotrish.mudra;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class choose extends ActionBarActivity {


    public MediaPlayer mp = new MediaPlayer();

    public SharedPreferences sharepref;
    public static final String SCORE_FILE = "scorefile";
    int music;
    int SFX;

    GoogleAnalytics tracker;

    Timer mTmr = null;
    TimerTask mTsk = null;
    int mScrWidth, mScrHeight;
    android.graphics.PointF mBallPos,mBallPos2,mBallPos3, mBallSpd,mBallSpd2,mBallSpd3;
    ImageButton img,img2,img3;


    @Override
    protected void onResume() {
        super.onResume();
        img= (ImageButton)findViewById(R.id.imageButton2);
        img2= (ImageButton)findViewById(R.id.imageButton3);
        img3= (ImageButton)findViewById(R.id.imageButton4);
       //FrameLayout touchinter = (FrameLayout)findViewById(R.id.touchInter);
        ///bouncing coin/////

        /*findViewById(R.id.imageButton2).clearAnimation();
        TranslateAnimation translation;
        translation = new TranslateAnimation(0f, 0F, 0f, getDisplayHeight());
        translation.setStartOffset(100);
        translation.setDuration(2000);
        translation.setFillAfter(true);
        translation.setInterpolator(new BounceInterpolator());
        findViewById(R.id.imageButton2).startAnimation(translation);
        translation.setRepeatMode(Animation.REVERSE);
        translation.setRepeatCount(Animation.INFINITE);*/


        //create timer to move ball to new position
        mTmr = new Timer();
        mTsk = new TimerTask() {
            public void run() {


                //move ball based on current speed
                mBallPos.x += mBallSpd.x-2;
                mBallPos.y += mBallSpd.y-3;
                mBallPos2.x += mBallSpd2.x+2;
                mBallPos2.y += mBallSpd2.y-2;
                mBallPos3.x += mBallSpd3.x+2;
                mBallPos3.y += mBallSpd3.y+2;
                //if ball goes off screen, reposition to opposite side of screen
                if (mBallPos.x > mScrWidth) mBallPos.x = 0;
                if (mBallPos.y > mScrHeight) mBallPos.y = 0;
                if (mBallPos.x < 0) mBallPos.x = mScrWidth;
                if (mBallPos.y < 0) mBallPos.y = mScrHeight;


                if (mBallPos2.x > mScrWidth) mBallPos2.x = 0;
                if (mBallPos2.y > mScrHeight) mBallPos2.y = 0;
                if (mBallPos2.x < 0) mBallPos2.x = mScrWidth;
                if (mBallPos2.y < 0) mBallPos2.y = mScrHeight;


                if (mBallPos3.x > mScrWidth) mBallPos3.x = 0;
                if (mBallPos3.y > mScrHeight) mBallPos3.y = 0;
                if (mBallPos3.x < 0) mBallPos3.x = mScrWidth;
                if (mBallPos3.y < 0) mBallPos3.y = mScrHeight;
                //update ball class instance

                if(Integer.valueOf(android.os.Build.VERSION.SDK)>=11) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            img.setX(mBallPos.x);
                            img.setY(mBallPos.y);
                            img2.setX(mBallPos2.x);
                            img2.setY(mBallPos2.y);
                            img3.setX(mBallPos3.x);
                            img3.setY(mBallPos3.y);

                        }
                    });

                }

              /*  //redraw ball. Must run in background thread to prevent thread lock.
                RedrawHandler.post(new Runnable() {
                    public void run() {
                        mBallView.invalidate();
                    }});*/
            }}; // TimerTask

        mTmr.schedule(mTsk, 15, 15); //start timer
        // super.onResume();
    } // onResume





    ////coin ends/////


    @Override
    public void onPause() //app moved to background, stop background threads
    {
        mTmr.cancel(); //kill\release timer (our only background thread)
        mTmr = null;
        mTsk = null;
        super.onPause();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        GoogleAnalytics.getInstance(choose.this).reportActivityStart(this);
    }





    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        GoogleAnalytics.getInstance(choose.this).reportActivityStop(this);
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        splash.fa.finish();

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        //analytics
        Tracker t = ((AnalyticsHelper) getApplication()).getTracker(AnalyticsHelper.TrackerName.APP_TRACKER);
        t.setScreenName("Home");
        t.send(new HitBuilders.AppViewBuilder().build());
        //analytics ends


        TextView choosetxt=(TextView)findViewById(R.id.choosetxt);
        Button butset=(Button)findViewById(R.id.butset);
        Button butabout=(Button)findViewById(R.id.butabout);
        Button butexit=(Button)findViewById(R.id.butexit);
        Button play1=(Button)findViewById(R.id.play1);
        Button play2=(Button)findViewById(R.id.play2);
        AdView mAdView = (AdView) findViewById(R.id.adView2);
        choosetxt.bringToFront();
        butset.bringToFront();
        butabout.bringToFront();
        butexit.bringToFront();
        play1.bringToFront();
        play2.bringToFront();
        mAdView.bringToFront();
        choosetxt.invalidate();
        butset.invalidate();
        butabout.invalidate();
        butexit.invalidate();
        play1.invalidate();
        play2.invalidate();
        mAdView.invalidate();


        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/samar.TTF");
        choosetxt.setTypeface(tf);



        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        ///trying move class here////////

        img= (ImageButton)findViewById(R.id.imageButton2);
        img2= (ImageButton)findViewById(R.id.imageButton3);
        img3= (ImageButton)findViewById(R.id.imageButton4);



        //get screen dimensions
        Display display = getWindowManager().getDefaultDisplay();
        mScrWidth = display.getWidth();
        mScrHeight = display.getHeight();
        mBallPos = new android.graphics.PointF();
        mBallPos2 = new android.graphics.PointF();
        mBallPos3 = new android.graphics.PointF();
        mBallSpd = new android.graphics.PointF();
        mBallSpd2 = new android.graphics.PointF();
        mBallSpd3 = new android.graphics.PointF();


        if(Integer.valueOf(android.os.Build.VERSION.SDK)>=11) {
            //create variables for ball position and speed
            mBallPos.x = img.getX();
            mBallPos.y = img.getY();
            mBallPos2.x = img2.getX();
            mBallPos2.y = img2.getY();
            mBallPos3.x = img3.getX();
            mBallPos3.y = img3.getY();
            mBallSpd.x = 0;
            mBallSpd.y = 0;
            mBallSpd2.x = 0;
            mBallSpd2.y = 0;
            mBallSpd3.x = 0;
            mBallSpd3.y = 0;


            //listener for accelerometer, use anonymous class for simplicity
            ((SensorManager) getSystemService(Context.SENSOR_SERVICE)).registerListener(
                    new SensorEventListener() {
                        @Override
                        public void onSensorChanged(SensorEvent event) {
                            //set ball speed based on phone tilt (ignore Z axis)
                            mBallSpd.x = -event.values[0];
                            mBallSpd.y = event.values[1];

                            mBallSpd2.x = event.values[0];
                            mBallSpd2.y = event.values[1];

                            mBallSpd3.x = event.values[0];
                            mBallSpd3.y = -event.values[1];
                            //timer event will redraw ball
                        }

                        @Override
                        public void onAccuracyChanged(Sensor sensor, int accuracy) {
                        } //ignore this event
                    },
                    ((SensorManager) getSystemService(Context.SENSOR_SERVICE))
                            .getSensorList(Sensor.TYPE_ACCELEROMETER).get(0), SensorManager.SENSOR_DELAY_NORMAL);

//////////////end of move class////////////
        }
    }

    private int getDisplayHeight() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_choose, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return super.onOptionsItemSelected(item);

    }


    public void goset(View view)
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

        final Intent intestz=new Intent(this,options.class);
        startActivity(intestz);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }

    public void goabout(View view)
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

        final Intent intest=new Intent(this,egg.class);
        startActivity(intest);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }

    public void goexit(View view)
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

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


    public void choose1(View view)
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

        Intent inte=new Intent(this,MainActivity.class);

        startActivity(inte);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }

    public void choose2(View view)
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

        Intent inte=new Intent(this,main2.class);

        startActivity(inte);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
    }




    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
    }

}
