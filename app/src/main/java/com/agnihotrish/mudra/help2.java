package com.agnihotrish.mudra;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class help2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_help2);

        TextView heading=(TextView)findViewById(R.id.heading);
        Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/samar.TTF");
        heading.setTypeface(tf);

        TextView txt1=(TextView)findViewById(R.id.txt1);
        TextView txt2=(TextView)findViewById(R.id.txt2);
        TextView txt3=(TextView)findViewById(R.id.txt3);

        txt1.setText("The winner of the game is the player(you or your opponent),who succeeds in removing the last coin(s).\n" +
                        "\n"+
                        "The value of the coins which the players can remove are two random numbers along with 1.\n"
        );


        txt3.setText("Press the coin button whose value you want to remove.\n" +
                        "\n"+
                        "Then,wait for your opponent to do the same.\n"+"\nPlease note:The statistics page supports upto 8 players.Perform a statistics reset if you wish to add new players after reaching the limit.\n"+"\nPress the menu button on the home page to access other options."
        );

        txt2.setText("Optionally,the two players can fill in their names in the text fields provided.\n"+"\nPress the START GAME! button to begin a match.\n"
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
