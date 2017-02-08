package com.agnihotrish.mudra;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class help extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_help);

        TextView heading=(TextView)findViewById(R.id.heading);
        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/samar.TTF");
        heading.setTypeface(tf);

        TextView txt1=(TextView)findViewById(R.id.txt1);
        TextView txt2=(TextView)findViewById(R.id.txt2);
        TextView txt3=(TextView)findViewById(R.id.txt3);

        txt1.setText("The winner of the game is the player(you or the computer),who succeeds in removing the last coin(s).\n" +
                "\n"+
                "The value of the coins which you can remove are two random numbers along with 1.\n"
                );


        txt2.setText("Press the coin button whose value you want to remove.\n" +
                        "\n"+
                        "Then,wait for the computer to play its chance.\n"
        );

        txt3.setText("In the difficult mode the computer plays optimally and is quite hard to beat.\n" +"\n"+
                        "The easy mode should be a cakewalk and the medium mode lies between the other two modes.\n"

                +"\n Press the menu button on the home page to access other options.\n"
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_help, menu);
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
