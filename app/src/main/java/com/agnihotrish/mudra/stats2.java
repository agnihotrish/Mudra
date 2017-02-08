package com.agnihotrish.mudra;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

public class stats2 extends ActionBarActivity {

    public SharedPreferences shrprf;
    public static final String SCORE_FILE = "scorefile";
    public static final String PLAYTWO = "playtwo";
    public SwipeRefreshLayout swipeLayout;
    Typeface tf;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_stats2);







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

        shrprf = getSharedPreferences(PLAYTWO, 0);
        SharedPreferences.Editor myeditor = shrprf.edit();



        count=shrprf.getInt("count",0);

        String name1=shrprf.getString("name1",null);
        int totmat1=shrprf.getInt("totmat1",0);
        int winmat1=shrprf.getInt("winmat1",0);
        int losemat1=totmat1-winmat1;

        String name2=shrprf.getString("name2",null);
        int totmat2=shrprf.getInt("totmat2",0);
        int winmat2=shrprf.getInt("winmat2",0);
        int losemat2=totmat2-winmat2;

        String name3=shrprf.getString("name3",null);
        int totmat3=shrprf.getInt("totmat3",0);
        int winmat3=shrprf.getInt("winmat3",0);
        int losemat3=totmat3-winmat3;

        String name4=shrprf.getString("name4",null);
        int totmat4=shrprf.getInt("totmat4",0);
        int winmat4=shrprf.getInt("winmat4",0);
        int losemat4=totmat4-winmat4;

        String name5=shrprf.getString("name5",null);
        int totmat5=shrprf.getInt("totmat5",0);
        int winmat5=shrprf.getInt("winmat5",0);
        int losemat5=totmat5-winmat5;

        String name6=shrprf.getString("name6",null);
        int totmat6=shrprf.getInt("totmat6",0);
        int winmat6=shrprf.getInt("winmat6",0);
        int losemat6=totmat6-winmat6;

        String name7=shrprf.getString("name7",null);
        int totmat7=shrprf.getInt("totmat7",0);
        int winmat7=shrprf.getInt("winmat7",0);
        int losemat7=totmat7-winmat7;

        String name8=shrprf.getString("name8",null);
        int totmat8=shrprf.getInt("totmat8",0);
        int winmat8=shrprf.getInt("winmat8",0);
        int losemat8=totmat8-winmat8;


        TextView en=(TextView)findViewById(R.id.name1);
        TextView em=(TextView)findViewById(R.id.em);
        TextView ew=(TextView)findViewById(R.id.ew);
        TextView el=(TextView)findViewById(R.id.el);

        TextView mn=(TextView)findViewById(R.id.name2);
        TextView mm=(TextView)findViewById(R.id.mm);
        TextView mw=(TextView)findViewById(R.id.mw);
        TextView ml=(TextView)findViewById(R.id.ml);


        TextView fn=(TextView)findViewById(R.id.name3);
        TextView fm=(TextView)findViewById(R.id.fm);
        TextView fw=(TextView)findViewById(R.id.fw);
        TextView fl=(TextView)findViewById(R.id.fl);

        TextView gn=(TextView)findViewById(R.id.name4);
        TextView gm=(TextView)findViewById(R.id.gm);
        TextView gw=(TextView)findViewById(R.id.gw);
        TextView gl=(TextView)findViewById(R.id.gl);

        TextView hn=(TextView)findViewById(R.id.name5);
        TextView hm=(TextView)findViewById(R.id.hm);
        TextView hw=(TextView)findViewById(R.id.hw);
        TextView hl=(TextView)findViewById(R.id.hl);

        TextView in=(TextView)findViewById(R.id.name6);
        TextView im=(TextView)findViewById(R.id.im);
        TextView iw=(TextView)findViewById(R.id.iw);
        TextView il=(TextView)findViewById(R.id.il);

        TextView jn=(TextView)findViewById(R.id.name7);
        TextView jm=(TextView)findViewById(R.id.jm);
        TextView jw=(TextView)findViewById(R.id.jw);
        TextView jl=(TextView)findViewById(R.id.jl);

        TextView kn=(TextView)findViewById(R.id.name8);
        TextView km=(TextView)findViewById(R.id.km);
        TextView kw=(TextView)findViewById(R.id.kw);
        TextView kl=(TextView)findViewById(R.id.kl);



        TextView mode=(TextView)findViewById(R.id.mode);
        TextView matches=(TextView)findViewById(R.id.matches);
        TextView won=(TextView)findViewById(R.id.won);
        TextView lost=(TextView)findViewById(R.id.lost);
        TextView easy=(TextView)findViewById(R.id.easy);
        TextView medium=(TextView)findViewById(R.id.medium);

        TextView stats=(TextView)findViewById(R.id.stats);
        TextView resettxt=(TextView)findViewById(R.id.resettxt);


         tf=Typeface.createFromAsset(getAssets(),"fonts/samar.TTF");
        mode.setTypeface(tf);
        matches.setTypeface(tf);
        won.setTypeface(tf);
        lost.setTypeface(tf);


        stats.setTypeface(tf);
        resettxt.setTypeface(tf);

        if(count>0) {
            en.setText(name1);
            em.setText(String.valueOf(totmat1));
            ew.setText(String.valueOf(winmat1));
            el.setText(String.valueOf(losemat1));
        }

        if(count>1) {
            mn.setText(name2);
            mm.setText(String.valueOf(totmat2));
            mw.setText(String.valueOf(winmat2));
            ml.setText(String.valueOf(losemat2));
        }

        if(count>2)
        {
            fn.setText(name3);
            fm.setText(String.valueOf(totmat3));
            fw.setText(String.valueOf(winmat3));
            fl.setText(String.valueOf(losemat3));
        }

        if(count>3)
        {
            gn.setText(name4);
            gm.setText(String.valueOf(totmat4));
            gw.setText(String.valueOf(winmat4));
            gl.setText(String.valueOf(losemat4));
        }
        if(count>4)
        {
            hn.setText(name5);
            hm.setText(String.valueOf(totmat5));
            hw.setText(String.valueOf(winmat5));
            hl.setText(String.valueOf(losemat5));
        }
        if(count>5)
        {
            in.setText(name6);
            im.setText(String.valueOf(totmat6));
            iw.setText(String.valueOf(winmat6));
            il.setText(String.valueOf(losemat6));
        }
        if(count>6)
        {
            jn.setText(name7);
            jm.setText(String.valueOf(totmat7));
            jw.setText(String.valueOf(winmat7));
            jl.setText(String.valueOf(losemat7));
        }
        if(count>7)
        {
            kn.setText(name8);
            km.setText(String.valueOf(totmat8));
            kw.setText(String.valueOf(winmat8));
            kl.setText(String.valueOf(losemat8));
        }
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
       shrprf = getSharedPreferences(PLAYTWO, 0);
        SharedPreferences.Editor myeditor = shrprf.edit();

        myeditor.putInt("count",0);

        myeditor.commit();

        /*Intent inte=new Intent(this,stats.class);
        startActivity(inte);
        overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);
        finish();*/

        TextView em=(TextView)findViewById(R.id.em);
        TextView ew=(TextView)findViewById(R.id.ew);
        TextView el=(TextView)findViewById(R.id.el);

        TextView mm=(TextView)findViewById(R.id.mm);
        TextView mw=(TextView)findViewById(R.id.mw);
        TextView ml=(TextView)findViewById(R.id.ml);

        TextView en=(TextView)findViewById(R.id.name1);
        TextView mn=(TextView)findViewById(R.id.name2);

        TextView mode=(TextView)findViewById(R.id.mode);
        TextView matches=(TextView)findViewById(R.id.matches);
        TextView won=(TextView)findViewById(R.id.won);
        TextView lost=(TextView)findViewById(R.id.lost);
        TextView easy=(TextView)findViewById(R.id.easy);
        TextView medium=(TextView)findViewById(R.id.medium);

        TextView stats=(TextView)findViewById(R.id.stats);
        TextView resettxt=(TextView)findViewById(R.id.resettxt);


        tf=Typeface.createFromAsset(getAssets(),"fonts/samar.TTF");
        mode.setTypeface(tf);
        matches.setTypeface(tf);
        won.setTypeface(tf);
        lost.setTypeface(tf);


        stats.setTypeface(tf);
        resettxt.setTypeface(tf);

        en.setText("Player 1");
        em.setText(String.valueOf(0));
        ew.setText(String.valueOf(0));
        el.setText(String.valueOf(0));

        mn.setText("Player 2");
        mm.setText(String.valueOf(0));
        mw.setText(String.valueOf(0));
        ml.setText(String.valueOf(0));


        TableRow row3=(TableRow)findViewById(R.id.row3);
        TableRow row4=(TableRow)findViewById(R.id.row4);
        TableRow row5=(TableRow)findViewById(R.id.row5);
        TableRow row6=(TableRow)findViewById(R.id.row6);
        TableRow row7=(TableRow)findViewById(R.id.row7);
        TableRow row8=(TableRow)findViewById(R.id.row8);

        row3.setVisibility(View.INVISIBLE);
        row4.setVisibility(View.INVISIBLE);
        row5.setVisibility(View.INVISIBLE);
        row6.setVisibility(View.INVISIBLE);
        row7.setVisibility(View.INVISIBLE);
        row8.setVisibility(View.INVISIBLE);

    }



}
