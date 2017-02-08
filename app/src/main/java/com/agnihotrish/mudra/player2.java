package com.agnihotrish.mudra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Random;


public class player2 extends ActionBarActivity implements View.OnClickListener {




    int k, l;
    int n, n2;

    TextView coins, result, coinsleft;
    Button buto, butk, butl;
    ImageView img;

    int win,mauka; //win 0 means player 1 ,win 1 means player 2

    public SharedPreferences sharepref,shrprf;
    public static final String SCORE_FILE = "scorefile";
    public static final String PLAYTWO = "playtwo";
    public MediaPlayer mp = new MediaPlayer();
    public MediaPlayer mp2 = new MediaPlayer();
    public MediaPlayer mp3 = new MediaPlayer();
    Animation wob3;

    public int music;
    public int SFX;

    String oname1,oname2,name1,name2;
    public  int media_length;

    @Override
    protected void onResume(){
        super.onResume();
        mp2.seekTo(media_length);
        mp2.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();

        mp2.pause();
        media_length = mp2.getCurrentPosition();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player2);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
             oname1 = extras.getString("n1");
            oname2 = extras.getString("n2");
        }


        //name manipulations//

        final StringBuilder res = new StringBuilder(oname1.length());
        String[] words = oname1.split("\\s");
        for(int i=0,l=words.length;i<l;++i) {
            if(i>0) res.append(" ");
            res.append(Character.toUpperCase(words[i].charAt(0)))
                    .append(words[i].substring(1));

        }

        name1= String.valueOf(res);

        final StringBuilder res2 = new StringBuilder(oname2.length());
        String[] word = oname2.split("\\s");
        for(int i=0,l=word.length;i<l;++i) {
            if(i>0) res2.append(" ");
            res2.append(Character.toUpperCase(word[i].charAt(0)))
                    .append(word[i].substring(1));

        }
        name2= String.valueOf(res2);

        //name manipulation ends//



        ///wobble////
         butl=(Button)findViewById(R.id.butl);
        butk=(Button)findViewById(R.id.butk);
        buto=(Button)findViewById(R.id.buto);
        final Animation wob1 = AnimationUtils.loadAnimation(this, R.anim.wobble);
        final Animation wob2 = AnimationUtils.loadAnimation(this, R.anim.wobble);
       wob3= AnimationUtils.loadAnimation(this, R.anim.wobble);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


               // wob1.reset();
               // wob1.setFillAfter(true);
                butl.startAnimation(wob1);
            }
        },100);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               // wob2.reset();
               // wob2.setFillAfter(true);
               butk.startAnimation(wob2);
            }
        },800);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

               // wob3.reset();
               // wob3.setFillAfter(true);
                buto.startAnimation(wob3);



            }
        },1200);
        ///wobble ends///

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                result.setText("Press a coin\n"+name1);


            }
        },1800);



        ////check option/////////

        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();
        music= sharepref.getInt("music", 1); //enabled by default
        SFX=sharepref.getInt("SFX",1);
        ///option checking ends///////


        ////sounds here///////

        if(music==1) {

            if (mp2.isPlaying()) {
                mp2.stop();
            }

            try {
                mp2.reset();
                AssetFileDescriptor afd;
                afd = getAssets().openFd("easymus.mp3");
                mp2.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp2.prepare();
                mp2.setLooping(true);
                mp2.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        ///sounds ends//////



        Random r = new Random();
        n = r.nextInt(51 - 35) + 35;
        n2 = n;
        k = r.nextInt(6 - 2) + 2;
        l = r.nextInt(10 - 6) + 6;


        ///win lose music decider////

        Random rand = new Random();
        mauka = rand.nextInt(6 - 1) + 1;

        ////decider ends here///////



        coins = (TextView) findViewById(R.id.coins);
        result = (TextView) findViewById(R.id.result);
        coinsleft = (TextView) findViewById(R.id.coinsleft);

        img=(ImageView) findViewById(R.id.img);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/samar.TTF");
        coinsleft.setTypeface(tf);

        coins.setText(String.valueOf(n2));
        butk.setText(String.valueOf(k));
        butl.setText(String.valueOf(l));



        buto.setOnClickListener(this);
        butk.setOnClickListener(this);
        butl.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_player2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


                return super.onOptionsItemSelected(item);

    }




    public void finish()
    {
        super.finish();
        if(mp2.isPlaying())
        {
            mp2.stop();
        }
        overridePendingTransition(R.anim.activity_open_scale, R.anim.activity_close_translate);
    }

    @Override
    public void onClick(View v) {

        if(wob3.hasEnded())
        {
        final Intent inte2 = new Intent(this, main2.class);

        int check = 0;


        switch (v.getId()) {
            case R.id.buto:
                //Toast.makeText(dusra.this, "1", Toast.LENGTH_SHORT).show();

                if (n2 - 1 >= 0) {
                    n2 = n2 - 1;


                    //sounds///
                    if (SFX == 1) {
                        if (mp.isPlaying()) {
                            mp.stop();
                        }

                        try {
                            mp.reset();
                            AssetFileDescriptor afd;
                            afd = getAssets().openFd("1coin.mp3");
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


                    ////testing delayed//////////////
                    Animation ghoom = AnimationUtils.loadAnimation(this, R.anim.ghoom);
                    v.startAnimation(ghoom);

                    if (win == 0)
                        result.setText(name1 + " removed 1 coin");
                    else
                        result.setText(name2 + " removed 1 coin");
                    win = 1 - win;

                    new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                        @Override
                        public void run() {

                            coins.setText(String.valueOf(n2));
                            if (n2 != 0) {
                                if (win == 0)
                                    result.setText("Press a coin " + name1);
                                else
                                    result.setText("Press a coin " + name2);
                            } else
                                result.setText("");
                        }
                    }, 1000);

                    check = 1;
                    ////end of testing///////////////

                }


                break;


            case R.id.butk:
                //Toast.makeText(dusra.this,String.valueOf(k),Toast.LENGTH_SHORT).show();

                if (n2 - k >= 0) {
                    Animation ghoom = AnimationUtils.loadAnimation(this, R.anim.ghoom);
                    v.startAnimation(ghoom);
                    n2 = n2 - k;


                    //sounds///
                    if (SFX == 1) {
                        if (mp.isPlaying()) {
                            mp.stop();
                        }

                        try {
                            mp.reset();
                            AssetFileDescriptor afd;
                            afd = getAssets().openFd("2coin.mp3");
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


                    ////testing delayed//////////////
                    if (win == 0)
                        result.setText(name1 + " removed " + String.valueOf(k) + " coins");
                    else
                        result.setText(name2 + " removed " + String.valueOf(k) + " coins");

                    win = 1 - win;

                    new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                        @Override
                        public void run() {

                            coins.setText(String.valueOf(n2));
                            if (n2 != 0) {
                                if (win == 0)
                                    result.setText("Press a coin " + name1);
                                else
                                    result.setText("Press a coin " + name2);
                            } else
                                result.setText("");
                        }
                    }, 1000);

                    check = 1;

                }


                break;


            case R.id.butl:
                //Toast.makeText(dusra.this,String.valueOf(l),Toast.LENGTH_SHORT).show();

                if (n2 - l >= 0) {
                    Animation ghoom = AnimationUtils.loadAnimation(this, R.anim.ghoom);
                    v.startAnimation(ghoom);
                    n2 = n2 - l;


                    //sounds///
                    if (SFX == 1) {
                        if (mp.isPlaying()) {
                            mp.stop();
                        }

                        try {
                            mp.reset();
                            AssetFileDescriptor afd;
                            afd = getAssets().openFd("3coin.mp3");
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


                    ////testing delayed//////////////
                    if (win == 0)
                        result.setText(name1 + " removed " + String.valueOf(l) + " coins");
                    else
                        result.setText(name2 + " removed " + String.valueOf(l) + " coins");
                    win = 1 - win;

                    new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                        @Override
                        public void run() {

                            coins.setText(String.valueOf(n2));
                            if (n2 != 0) {
                                if (win == 0)
                                    result.setText("Press a coin " + name1);
                                else
                                    result.setText("Press a coin " + name2);
                            } else
                                result.setText("");
                        }
                    }, 1000);

                    check = 1;
                }


                break;
        }

        ///////////////////////////////////////  when you win!


        if (n2 == 0) {

            win = 1 - win;


            buto.setClickable(false);
            butk.setClickable(false);
            butl.setClickable(false);

            if (win == 0)
                calcscore(1);
            else
                calcscore(2);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {


                    if (SFX == 1) {
                        if (mp2.isPlaying()) {
                            mp2.stop();
                        }


                        try {
                            mp3.reset();
                            AssetFileDescriptor afd = getAssets().openFd("win1.mp3");

                            if (mauka == 1)
                                afd = getAssets().openFd("win1.mp3");
                            else if (mauka == 2)
                                afd = getAssets().openFd("win2.mp3");
                            else if (mauka == 3)
                                afd = getAssets().openFd("win3.mp3");
                            else if (mauka == 4)
                                afd = getAssets().openFd("win4.mp3");
                            else if (mauka == 5)
                                afd = getAssets().openFd("win5.mp3");

                            mp3.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                            mp3.prepare();
                            mp3.start();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                    if (win == 0)
                        coins.setText(name1 + " Wins !!");
                    else
                        coins.setText(name2 + " Wins !!");
                    coinsleft.setText("");

                    result.setText("");

                    buto.setVisibility(View.GONE);
                    butk.setVisibility(View.GONE);
                    butl.setVisibility(View.GONE);
                    img.setImageResource(R.drawable.hcoin);
                }
            }, 1300);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    //setContentView(R.layout.activity_main); //where <next> is you target activity :)
                    startActivity(inte2);
                    finish();

                    //overridePendingTransition ( R.anim.leftin,0 );
                    overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
                }
            }, 2500);
        }

        ////////////////////////////////////////
        if (check == 0) {

            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            v.startAnimation(shake);

            if (win == 0)
                result.setText("Press a valid button\n" + name1);
            else
                result.setText("Press a valid button\n" + name2);


            //sounds///
            if (SFX == 1) {
                if (mp.isPlaying()) {
                    mp.stop();
                }

                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd("error.mp3");
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


            Vibrator myVib;
            myVib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
            myVib.vibrate(80);

            // result.setTextColor(Color.GRAY);
            coins.setText(String.valueOf(n2));
        }
    }

    }


    public void calcscore(int val)  //val 1 means player 1 won,2 means player 2 won
    {
        shrprf = getSharedPreferences(PLAYTWO, 0);
        SharedPreferences.Editor myeditor = shrprf.edit();

        int count=shrprf.getInt("count",0); //get the total number of unique player entries
        int i;
        int check1=0; //check if player 1 entry exists
        int check2=0; //check if player 2 entry exists

        for(i=1;i<=count;i++) { //for all entries

            String dbname=shrprf.getString("name"+Integer.toString(i), null);
            if( dbname!=null)
            {
                if(dbname.equalsIgnoreCase(name1))
                {
                    check1=1;

                    int total_matches = shrprf.getInt("totmat"+Integer.toString(i), 0);
                    myeditor.putInt("totmat"+Integer.toString(i),total_matches+1);
                    if(val==1)
                    {
                        int player_one= shrprf.getInt("winmat"+Integer.toString(i), 0);//easy matches won
                            myeditor.putInt("winmat"+Integer.toString(i),player_one+1);
                    }
                }


                else if(dbname.equalsIgnoreCase(name2))
                {

                    check2=1;

                    int total_matches = shrprf.getInt("totmat"+Integer.toString(i), 0);
                    myeditor.putInt("totmat"+Integer.toString(i),total_matches+1);
                    if(val==2)
                    {
                        int player_one= shrprf.getInt("winmat"+Integer.toString(i), 0);//easy matches won
                        myeditor.putInt("winmat"+Integer.toString(i),player_one+1);
                    }
                }
            }
        }

        if(check1==0) //add new entry
        {
            count++;
            myeditor.putInt("count",count);
            myeditor.putString("name"+Integer.toString(count),name1);

            myeditor.putInt("totmat"+Integer.toString(count),1);
            if(val==1)
            {
                myeditor.putInt("winmat"+Integer.toString(count),1);
            }
            else
                myeditor.putInt("winmat"+Integer.toString(count),0);
        }

        if(check2==0) //add new entry
        {

        count++;
            myeditor.putInt("count",count);
            myeditor.putString("name"+Integer.toString(count),name2);

            myeditor.putInt("totmat"+Integer.toString(count),1);
            if(val==2)
            {
                myeditor.putInt("winmat"+Integer.toString(count),1);
            }
            else
                myeditor.putInt("winmat"+Integer.toString(count),0);
        }



        myeditor.commit();
        //Toast.makeText(dusra.this,String.valueOf(matches), Toast.LENGTH_SHORT).show();

    }


}
