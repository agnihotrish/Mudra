package com.agnihotrish.mudra;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;


public class options extends ActionBarActivity {


    public SharedPreferences sharepref;
    public static final String SCORE_FILE = "scorefile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        TextView opt=(TextView)findViewById(R.id.opt);
        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/samar.TTF");
        opt.setTypeface(tf);

        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();
        int music= sharepref.getInt("music", 1); //enabled by default
        int SFX=sharepref.getInt("SFX",1);

        ToggleButton butmusic=(ToggleButton)findViewById(R.id.butmusic);
        ToggleButton butsfx=(ToggleButton)findViewById(R.id.butsfx);

        if(music==1)
            butmusic.setChecked(true);
        else
            butmusic.setChecked(false);

            if(SFX==1)
                butsfx.setChecked(true);
            else
                butsfx.setChecked(false);



    }



    public void onToggleMusic(View view) {
        // Is the toggle on?

        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();

        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            myeditor.putInt("music", 1); //enable music
        }

        else {
            myeditor.putInt("music", 0);
        }

        myeditor.commit();
    }



    public void onToggleSFX(View view) {
        // Is the toggle on?

        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();

        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            myeditor.putInt("SFX", 1);
        }

        else {
            myeditor.putInt("SFX", 0);
        }
        myeditor.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_options, menu);
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



    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
    }
}
