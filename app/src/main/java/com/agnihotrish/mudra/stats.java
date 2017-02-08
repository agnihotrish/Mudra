package com.agnihotrish.mudra;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class stats extends ActionBarActivity {

    public SharedPreferences sharepref;
    public static final String SCORE_FILE = "scorefile";
    public SwipeRefreshLayout swipeLayout;
    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_stats);







     /////swipe fun starts////////


        final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeView.setColorScheme(android.R.color.holo_orange_dark);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeView.setRefreshing(true);

                ( new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);
                        resetstat();
                    }
                }, 500);
            }
        });


        /////swipe fun ends///////////

        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();

        int emv = sharepref.getInt("em", 0); //easy matches played
        int ewv = sharepref.getInt("ew", 0);//easy matches won
        int mmv = sharepref.getInt("mm", 0);
        int mwv = sharepref.getInt("mw", 0);
        int dmv = sharepref.getInt("dm", 0);
        int dwv = sharepref.getInt("dw", 0);


        int elv=emv-ewv;
        int mlv=mmv-mwv;
        int dlv=dmv-dwv;

        int totmv=emv+mmv+dmv;
        int totwv=ewv+mwv+dwv;
        int totlv=elv+mlv+dlv;

        TextView em=(TextView)findViewById(R.id.em);
        TextView ew=(TextView)findViewById(R.id.ew);
        TextView el=(TextView)findViewById(R.id.el);

        TextView mm=(TextView)findViewById(R.id.mm);
        TextView mw=(TextView)findViewById(R.id.mw);
        TextView ml=(TextView)findViewById(R.id.ml);

        TextView dm=(TextView)findViewById(R.id.dm);
        TextView dw=(TextView)findViewById(R.id.dw);
        TextView dl=(TextView)findViewById(R.id.dl);

        TextView totm=(TextView)findViewById(R.id.totm);
        TextView totw=(TextView)findViewById(R.id.totw);
        TextView totl=(TextView)findViewById(R.id.totl);


        TextView mode=(TextView)findViewById(R.id.mode);
        TextView matches=(TextView)findViewById(R.id.matches);
        TextView won=(TextView)findViewById(R.id.won);
        TextView lost=(TextView)findViewById(R.id.lost);
        TextView easy=(TextView)findViewById(R.id.easy);
        TextView medium=(TextView)findViewById(R.id.medium);
        TextView difficult=(TextView)findViewById(R.id.difficult);
        TextView total=(TextView)findViewById(R.id.total);
        TextView stats=(TextView)findViewById(R.id.stats);
        TextView resettxt=(TextView)findViewById(R.id.resettxt);


        tf=Typeface.createFromAsset(getAssets(),"fonts/samar.TTF");
        mode.setTypeface(tf);
        matches.setTypeface(tf);
        won.setTypeface(tf);
        lost.setTypeface(tf);
        easy.setTypeface(tf);
        medium.setTypeface(tf);
        difficult.setTypeface(tf);
        total.setTypeface(tf);
        stats.setTypeface(tf);
        resettxt.setTypeface(tf);

        em.setText(String.valueOf(emv));
        ew.setText(String.valueOf(ewv));
        el.setText(String.valueOf(elv));

        mm.setText(String.valueOf(mmv));
        mw.setText(String.valueOf(mwv));
        ml.setText(String.valueOf(mlv));

        dm.setText(String.valueOf(dmv));
        dw.setText(String.valueOf(dwv));
        dl.setText(String.valueOf(dlv));

        totm.setText(String.valueOf(totmv));
        totw.setText(String.valueOf(totwv));
        totl.setText(String.valueOf(totlv));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_stats, menu);
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

    public void resetstat()
    {
        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();
        myeditor.putInt("em", 0);
        myeditor.putInt("ew", 0);
        myeditor.putInt("mm", 0);
        myeditor.putInt("mw", 0);
        myeditor.putInt("dm", 0);
        myeditor.putInt("dw", 0);
        myeditor.commit();

        /*Intent inte=new Intent(this,stats.class);
        startActivity(inte);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        finish();*/


        int emv = sharepref.getInt("em", 0); //easy matches played
        int ewv = sharepref.getInt("ew", 0);//easy matches won
        int mmv = sharepref.getInt("mm", 0);
        int mwv = sharepref.getInt("mw", 0);
        int dmv = sharepref.getInt("dm", 0);
        int dwv = sharepref.getInt("dw", 0);


        int elv=emv-ewv;
        int mlv=mmv-mwv;
        int dlv=dmv-dwv;

        int totmv=emv+mmv+dmv;
        int totwv=ewv+mwv+dwv;
        int totlv=elv+mlv+dlv;

        TextView em=(TextView)findViewById(R.id.em);
        TextView ew=(TextView)findViewById(R.id.ew);
        TextView el=(TextView)findViewById(R.id.el);

        TextView mm=(TextView)findViewById(R.id.mm);
        TextView mw=(TextView)findViewById(R.id.mw);
        TextView ml=(TextView)findViewById(R.id.ml);

        TextView dm=(TextView)findViewById(R.id.dm);
        TextView dw=(TextView)findViewById(R.id.dw);
        TextView dl=(TextView)findViewById(R.id.dl);

        TextView totm=(TextView)findViewById(R.id.totm);
        TextView totw=(TextView)findViewById(R.id.totw);
        TextView totl=(TextView)findViewById(R.id.totl);


        TextView mode=(TextView)findViewById(R.id.mode);
        TextView matches=(TextView)findViewById(R.id.matches);
        TextView won=(TextView)findViewById(R.id.won);
        TextView lost=(TextView)findViewById(R.id.lost);
        TextView easy=(TextView)findViewById(R.id.easy);
        TextView medium=(TextView)findViewById(R.id.medium);
        TextView difficult=(TextView)findViewById(R.id.difficult);
        TextView total=(TextView)findViewById(R.id.total);
        TextView stats=(TextView)findViewById(R.id.stats);

        tf=Typeface.createFromAsset(getAssets(),"fonts/samar.TTF");
        mode.setTypeface(tf);
        matches.setTypeface(tf);
        won.setTypeface(tf);
        lost.setTypeface(tf);
        easy.setTypeface(tf);
        medium.setTypeface(tf);
        difficult.setTypeface(tf);
        total.setTypeface(tf);
        stats.setTypeface(tf);

        em.setText(String.valueOf(emv));
        ew.setText(String.valueOf(ewv));
        el.setText(String.valueOf(elv));

        mm.setText(String.valueOf(mmv));
        mw.setText(String.valueOf(mwv));
        ml.setText(String.valueOf(mlv));

        dm.setText(String.valueOf(dmv));
        dw.setText(String.valueOf(dwv));
        dl.setText(String.valueOf(dlv));

        totm.setText(String.valueOf(totmv));
        totw.setText(String.valueOf(totwv));
        totl.setText(String.valueOf(totlv));

        TextView resettxt=(TextView)findViewById(R.id.resettxt);
        resettxt.setText("");


    }



}
