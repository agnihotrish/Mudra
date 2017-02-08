package com.agnihotrish.mudra;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class splash extends ActionBarActivity {

    public static Activity fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_splash);

        TextView mudra=(TextView)findViewById(R.id.mudra);
        ImageView coinpic=(ImageView)findViewById(R.id.coinpic);

        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/samar.TTF");
        mudra.setTypeface(tf);

        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.elastic);
        Animation anim2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);

        mudra.startAnimation(anim);
        coinpic.startAnimation(anim2);




        fa = this;
        final Intent intes=new Intent(this,choose.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //finish();
                startActivity(intes);
                //overridePendingTransition ( R.anim.leftin,R.anim.leftout );
               overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
            }
        }, 2000);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
