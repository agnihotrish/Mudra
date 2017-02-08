package com.agnihotrish.mudra;

import android.app.Activity;
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


public class dusra extends ActionBarActivity implements View.OnClickListener {

    int diflev;
    int k, l;
    int n, n2;
    int dp1[] = new int[100];
    int dp2[] = new int[100];
    TextView coins, result, comp, coinsleft;
    Button buto, butk, butl;
    ImageView img;

    int win,mauka;

    public SharedPreferences sharepref;
    public static final String SCORE_FILE = "scorefile";
    public MediaPlayer mp = new MediaPlayer();
    public MediaPlayer mp2 = new MediaPlayer();
    public MediaPlayer mp3 = new MediaPlayer();
Animation wob3;

    public int music;
    public int SFX;
public  int media_length;
    public static Activity fa2;

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
        setContentView(R.layout.activity_dusra);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.hide();




        //MainActivity.fa2.finish();

        Intent mIntent = getIntent();
        diflev = mIntent.getIntExtra("abcd", 1); //1 easy,2 mid,3 diff

        // final TextView txt2=(TextView)findViewById(R.id.txt2);
        //txt2.setText(String.valueOf(diflev));


        Intent inte2 = new Intent(this, MainActivity.class);
        fa2 = this;


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


        final Animation wob1 = AnimationUtils.loadAnimation(this, R.anim.wobble);
        final Animation wob2 = AnimationUtils.loadAnimation(this, R.anim.wobble);
       wob3 = AnimationUtils.loadAnimation(this, R.anim.wobble);



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


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                result.setText("Press a coin");


            }
        },1800);

        ///wobble ends///


        // DP magic here

        //int dp1[]=new int[n+1]; //0 means lose,1 means win
        //int dp2[]=new int[n+1];//stores coins to be removed if win,else 0

        dp1[0] = 0;
        dp2[0] = 1;

        dp1[1] = 1;
        dp2[1] = 1;

        dp1[k] = 1;
        dp2[k] = k;

        dp1[l] = 1;
        dp2[l] = l;

        int idx;
        int x, y;

        for (idx = 2; idx < 100; idx++) {
            x = 0;
            y = 1;

            if (idx == k || idx == l)
                continue;

            if (idx >= k && dp1[idx - k] == 0) {
                x = 1;
                y = k;

            }


            if (idx >= l && dp1[idx - l] == 0) {
                x = 1;
                y = l;

            }


            if (dp1[idx - 1] == 0) {
                x = 1;
                y = 1;
            }


            dp1[idx] = x;
            dp2[idx] = y;

        }

        //DP ends here


        buto = (Button) findViewById(R.id.buto);
        butk = (Button) findViewById(R.id.butk);
        butl = (Button) findViewById(R.id.butl);

        coins = (TextView) findViewById(R.id.coins);
        result = (TextView) findViewById(R.id.result);
        coinsleft = (TextView) findViewById(R.id.coinsleft);

        img=(ImageView) findViewById(R.id.img);

        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/samar.TTF");
        coinsleft.setTypeface(tf);
        //result.setTypeface(tf);
        //coins.setTypeface(tf);

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
        //getMenuInflater().inflate(R.menu.menu_dusra, menu);
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


    public void onClick(final View v) {
        //TextView coins=(TextView) findViewById(R.id.coins);
        //comp=(TextView) findViewById(R.id.comp);

        if (wob3.hasEnded()) {

            final Intent inte2 = new Intent(this, MainActivity.class);
        /*Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        v.startAnimation(shake);*/


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
                        result.setText("You removed 1 coin");

                        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                            @Override
                            public void run() {

                                coins.setText(String.valueOf(n2));
                                result.setText("");
                            }
                        }, 800);

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
                        result.setText("You removed " + String.valueOf(k) + " coins");

                        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                            @Override
                            public void run() {

                                coins.setText(String.valueOf(n2));
                                result.setText("");
                            }
                        }, 800);

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
                        result.setText("You removed " + String.valueOf(l) + " coins");

                        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                            @Override
                            public void run() {

                                coins.setText(String.valueOf(n2));
                                result.setText("");
                            }
                        }, 800);

                        check = 1;
                    }


                    break;
            }

            ///////////////////////////////////////  when you win!


            if (n2 == 0) {
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


                        coins.setText("You Win !!");
                        coinsleft.setText("");
                        result.setText("");
                        calcscore(1);
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

                result.setText("Press a valid button");


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
            } else {
                buto.setClickable(false);
                //buto.setBackgroundColor(Color.GRAY);
                butk.setClickable(false);
                //butk.setBackgroundColor(Color.GRAY);
                butl.setClickable(false);
                // butl.setBackgroundColor(Color.GRAY);
            }


            win = 0;

            if (check == 1 && n2 != 0) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        if (diflev == 3)
                            compturn3();
                        else if (diflev == 2)
                            compturn2();
                        else
                            compturn1();

                    }
                }, 1700);

            }

        }
    }

////////////////////////////////////////////computer's turn//////////////////////////////////


    void compturn1()   //////easy////////
    {



        if(n2!=0)
        {

        final int n3 = n2;
        final Intent inte2 = new Intent(this, MainActivity.class);

        if (n2 == 1)   ////optimal move///
        {
                win=1;
            ////testing delayed result //////////////////
            n2 = n2 - 1;
            result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

            new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                @Override
                public void run() {

                    coins.setText(String.valueOf(n2));
                    result.setText("");


                    if (n2 != 0) {
                        buto.setClickable(true);                        ///buttons now clickable again
                        //buto.setBackgroundColor(Color.LTGRAY);
                        butk.setClickable(true);
                        //butk.setBackgroundColor(Color.LTGRAY);
                        butl.setClickable(true);
                        //butl.setBackgroundColor(Color.LTGRAY);
                    }

                }
            }, 1000);


            ////end of testing///////////
        } else if (n2 == k)   ////optimal move///
        {

            win=1;
            ////testing delayed result //////////////////
            n2 = n2 - k;
            result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

            new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                @Override
                public void run() {

                    coins.setText(String.valueOf(n2));
                    result.setText("");


                    if (n2 != 0) {
                        buto.setClickable(true);                        ///buttons now clickable again
                        //buto.setBackgroundColor(Color.LTGRAY);
                        butk.setClickable(true);
                        //butk.setBackgroundColor(Color.LTGRAY);
                        butl.setClickable(true);
                        //butl.setBackgroundColor(Color.LTGRAY);
                    }

                }
            }, 1000);


            ////end of testing///////////

        } else if (n2 == l)   ////optimal move///
        {
            win=1;
            ////testing delayed result //////////////////
            n2 = n2 - l;
            result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

            new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                @Override
                public void run() {

                    coins.setText(String.valueOf(n2));
                    result.setText("");


                    if (n2 != 0) {
                        buto.setClickable(true);                        ///buttons now clickable again
                       // buto.setBackgroundColor(Color.LTGRAY);
                        butk.setClickable(true);
                        //butk.setBackgroundColor(Color.LTGRAY);
                        butl.setClickable(true);
                        //butl.setBackgroundColor(Color.LTGRAY);
                    }

                }
            }, 1000);


            ////end of testing///////////

        } else                    ///random move////
        {

            Random ra = new Random();
            int ch = ra.nextInt(4 - 1) + 1;


            switch (ch) {
                case 1:
                    if (n2 - 1 >= 0) {
                        ////testing delayed result //////////////////
                        n2 = n2 - 1;
                        result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                            @Override
                            public void run() {

                                coins.setText(String.valueOf(n2));
                                result.setText("");


                                if (n2 != 0) {
                                    buto.setClickable(true);                        ///buttons now clickable again
                                   // buto.setBackgroundColor(Color.LTGRAY);
                                    butk.setClickable(true);
                                   // butk.setBackgroundColor(Color.LTGRAY);
                                    butl.setClickable(true);
                                    //butl.setBackgroundColor(Color.LTGRAY);
                                }

                            }
                        }, 1000);


                        ////end of testing///////////

                    }
                    break;

                case 2:
                    if (n2 - k >= 0) {
                        ////testing delayed result //////////////////
                        n2 = n2 - k;
                        result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                            @Override
                            public void run() {

                                coins.setText(String.valueOf(n2));
                                result.setText("");


                                if (n2 != 0) {
                                    buto.setClickable(true);                        ///buttons now clickable again
                                    //buto.setBackgroundColor(Color.LTGRAY);
                                    butk.setClickable(true);
                                   // butk.setBackgroundColor(Color.LTGRAY);
                                    butl.setClickable(true);
                                   // butl.setBackgroundColor(Color.LTGRAY);
                                }

                            }
                        }, 1000);


                        ////end of testing///////////

                    }
                    break;

                case 3:
                    if (n2 - l >= 0) {
                        ////testing delayed result //////////////////
                        n2 = n2 - l;
                        result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                            @Override
                            public void run() {

                                coins.setText(String.valueOf(n2));
                                result.setText("");


                                if (n2 != 0) {
                                    buto.setClickable(true);                        ///buttons now clickable again
                                    //buto.setBackgroundColor(Color.LTGRAY);
                                    butk.setClickable(true);
                                   // butk.setBackgroundColor(Color.LTGRAY);
                                    butl.setClickable(true);
                                    //butl.setBackgroundColor(Color.LTGRAY);
                                }

                            }
                        }, 1000);


                        ////end of testing///////////

                    }
                    break;

            }

            if (n3 == n2) {
                ////testing delayed result //////////////////
                n2 = n2 - 1;
                result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                    @Override
                    public void run() {

                        coins.setText(String.valueOf(n2));
                        result.setText("");


                        if (n2 != 0) {
                            buto.setClickable(true);                        ///buttons now clickable again
                            //buto.setBackgroundColor(Color.LTGRAY);
                            butk.setClickable(true);
                            //butk.setBackgroundColor(Color.LTGRAY);
                            butl.setClickable(true);
                            //butl.setBackgroundColor(Color.LTGRAY);
                        }

                    }
                }, 1000);


                ////end of testing///////////

            }


        }


        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
            @Override
            public void run() {

                if (win==1 && n2 == 0) {

                    if(mp2.isPlaying())
                    {
                        mp2.stop();
                    }

                    if(SFX==1) {
                    try {
                        mp3.reset();
                        AssetFileDescriptor afd=getAssets().openFd("lose1.mp3");
                        mp3.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        mp3.prepare();
                        mp3.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }}


                    coins.setText("You lose !!");
                    coinsleft.setText("");
                    result.setText("");
                    calcscore(0);
                    buto.setVisibility(View.GONE);
                    butk.setVisibility(View.GONE);
                    butl.setVisibility(View.GONE);
                    img.setImageResource(R.drawable.comp);

                }

            }
        }, 1500);


        new Handler().postDelayed(new Runnable() {   ///back to main screen delayed after win
            @Override
            public void run() {

                if (win==1 && n2 == 0) {

                    startActivity(inte2);
                    finish();

                    //overridePendingTransition ( R.anim.leftin,0 );
                    overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
                   // overridePendingTransition(R.anim.cardin, R.anim.cardout);
                }

            }
        }, 2500);

    }}


    void compturn2()   //////medium////////
    {

        if(n2!=0)
        {

            win=1;
        final int n3 = n2;
        final Intent inte2 = new Intent(this, MainActivity.class);


            if (n2 == 1)   ////optimal move///
            {
                win=1;
                ////testing delayed result //////////////////
                n2 = n2 - 1;
                result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                    @Override
                    public void run() {

                        coins.setText(String.valueOf(n2));
                        result.setText("");


                        if (n2 != 0) {
                            buto.setClickable(true);                        ///buttons now clickable again
                            //buto.setBackgroundColor(Color.LTGRAY);
                            butk.setClickable(true);
                            //butk.setBackgroundColor(Color.LTGRAY);
                            butl.setClickable(true);
                            //butl.setBackgroundColor(Color.LTGRAY);
                        }

                    }
                }, 1000);


                ////end of testing///////////
            } else if (n2 == k)   ////optimal move///
            {

                win=1;
                ////testing delayed result //////////////////
                n2 = n2 - k;
                result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                    @Override
                    public void run() {

                        coins.setText(String.valueOf(n2));
                        result.setText("");


                        if (n2 != 0) {
                            buto.setClickable(true);                        ///buttons now clickable again
                            //buto.setBackgroundColor(Color.LTGRAY);
                            butk.setClickable(true);
                            //butk.setBackgroundColor(Color.LTGRAY);
                            butl.setClickable(true);
                            //butl.setBackgroundColor(Color.LTGRAY);
                        }

                    }
                }, 1000);


                ////end of testing///////////

            } else if (n2 == l)   ////optimal move///
            {
                win=1;
                ////testing delayed result //////////////////
                n2 = n2 - l;
                result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                    @Override
                    public void run() {

                        coins.setText(String.valueOf(n2));
                        result.setText("");


                        if (n2 != 0) {
                            buto.setClickable(true);                        ///buttons now clickable again
                            // buto.setBackgroundColor(Color.LTGRAY);
                            butk.setClickable(true);
                            //butk.setBackgroundColor(Color.LTGRAY);
                            butl.setClickable(true);
                            //butl.setBackgroundColor(Color.LTGRAY);
                        }

                    }
                }, 1000);


                ////end of testing///////////

            }




       else if (dp1[n2] == 1 && (n2 - dp2[n2] >= 0))//if optimal move possible
        {
            Random ra = new Random();
            int abc = ra.nextInt(3 - 1) + 1;

            if (abc == 1)  //optimal move
            {
                ////testing delayed result //////////////////
                n2 = n2 - dp2[n2];
                result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                    @Override
                    public void run() {

                        coins.setText(String.valueOf(n2));
                        result.setText("");


                        if (n2 != 0) {
                            buto.setClickable(true);                        ///buttons now clickable again
                            //buto.setBackgroundColor(Color.LTGRAY);
                            butk.setClickable(true);
                           // butk.setBackgroundColor(Color.LTGRAY);
                            butl.setClickable(true);
                            //butl.setBackgroundColor(Color.LTGRAY);
                        }

                    }
                }, 1000);


                ////end of testing///////////

            } else           //random move
            {

                Random rat = new Random();
                int xyz = rat.nextInt(4 - 1) + 1;

                switch (xyz) {
                    case 1:
                        if (n2 - 1 >= 0) {
                            ////testing delayed result //////////////////
                            n2 = n2 - 1;
                            result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                            new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                                @Override
                                public void run() {

                                    coins.setText(String.valueOf(n2));
                                    result.setText("");


                                    if (n2 != 0) {
                                        buto.setClickable(true);                        ///buttons now clickable again
                                       // buto.setBackgroundColor(Color.LTGRAY);
                                        butk.setClickable(true);
                                       //butk.setBackgroundColor(Color.LTGRAY);
                                        butl.setClickable(true);
                                       // butl.setBackgroundColor(Color.LTGRAY);
                                    }

                                }
                            }, 1000);


                            ////end of testing///////////

                        }
                        break;

                    case 2:
                        if (n2 - k >= 0) {
                            ////testing delayed result //////////////////
                            n2 = n2 - k;
                            result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                            new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                                @Override
                                public void run() {

                                    coins.setText(String.valueOf(n2));
                                    result.setText("");


                                    if (n2 != 0) {
                                        buto.setClickable(true);                        ///buttons now clickable again
                                       // buto.setBackgroundColor(Color.LTGRAY);
                                        butk.setClickable(true);
                                       // butk.setBackgroundColor(Color.LTGRAY);
                                        butl.setClickable(true);
                                        //butl.setBackgroundColor(Color.LTGRAY);
                                    }

                                }
                            }, 1000);


                            ////end of testing///////////

                        }
                        break;

                    case 3:
                        if (n2 - l >= 0) {
                            ////testing delayed result //////////////////
                            n2 = n2 - l;
                            result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                            new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                                @Override
                                public void run() {

                                    coins.setText(String.valueOf(n2));
                                    result.setText("");


                                    if (n2 != 0) {
                                        buto.setClickable(true);                        ///buttons now clickable again
                                       // buto.setBackgroundColor(Color.LTGRAY);
                                        butk.setClickable(true);
                                       // butk.setBackgroundColor(Color.LTGRAY);
                                        butl.setClickable(true);
                                        //butl.setBackgroundColor(Color.LTGRAY);
                                    }

                                }
                            }, 1000);


                            ////end of testing///////////

                        }
                        break;

                }

                if (n3 == n2) {
                    ////testing delayed result //////////////////
                    n2 = n2 - 1;
                    result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                    new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                        @Override
                        public void run() {

                            coins.setText(String.valueOf(n2));
                            result.setText("");


                            if (n2 != 0) {
                                buto.setClickable(true);                        ///buttons now clickable again
                                //buto.setBackgroundColor(Color.LTGRAY);
                                butk.setClickable(true);
                                //butk.setBackgroundColor(Color.LTGRAY);
                                butl.setClickable(true);
                               // butl.setBackgroundColor(Color.LTGRAY);
                            }

                        }
                    }, 1000);


                    ////end of testing///////////

                }
            }
        } else {   ///optimal move not possible
            Random rat = new Random();
            int xyz = rat.nextInt(4 - 1) + 1;

            switch (xyz) {
                case 1:
                    if (n2 - 1 >= 0) {
                        ////testing delayed result //////////////////
                        n2 = n2 - 1;
                        result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                            @Override
                            public void run() {

                                coins.setText(String.valueOf(n2));
                                result.setText("");


                                if (n2 != 0) {
                                    buto.setClickable(true);                        ///buttons now clickable again
                                   // buto.setBackgroundColor(Color.LTGRAY);
                                    butk.setClickable(true);
                                   // butk.setBackgroundColor(Color.LTGRAY);
                                    butl.setClickable(true);
                                   // butl.setBackgroundColor(Color.LTGRAY);
                                }

                            }
                        }, 1000);


                        ////end of testing///////////

                    }
                    break;

                case 2:
                    if (n2 - k >= 0) {
                        ////testing delayed result //////////////////
                        n2 = n2 - k;
                        result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                            @Override
                            public void run() {

                                coins.setText(String.valueOf(n2));
                                result.setText("");


                                if (n2 != 0) {
                                    buto.setClickable(true);                        ///buttons now clickable again
                                   // buto.setBackgroundColor(Color.LTGRAY);
                                    butk.setClickable(true);
                                    //butk.setBackgroundColor(Color.LTGRAY);
                                    butl.setClickable(true);
                                   // butl.setBackgroundColor(Color.LTGRAY);
                                }

                            }
                        }, 1000);


                        ////end of testing///////////

                    }
                    break;

                case 3:
                    if (n2 - l >= 0) {
                        ////testing delayed result //////////////////
                        n2 = n2 - l;
                        result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                            @Override
                            public void run() {

                                coins.setText(String.valueOf(n2));
                                result.setText("");


                                if (n2 != 0) {
                                    buto.setClickable(true);                        ///buttons now clickable again
                                    //buto.setBackgroundColor(Color.LTGRAY);
                                    butk.setClickable(true);
                                    //butk.setBackgroundColor(Color.LTGRAY);
                                    butl.setClickable(true);
                                    //butl.setBackgroundColor(Color.LTGRAY);
                                }

                            }
                        }, 1000);


                        ////end of testing///////////

                    }
                    break;

            }

            if (n3 == n2) {
                ////testing delayed result //////////////////
                n2 = n2 - 1;
                result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                    @Override
                    public void run() {

                        coins.setText(String.valueOf(n2));
                        result.setText("");


                        if (n2 != 0) {
                            buto.setClickable(true);                        ///buttons now clickable again
                            //buto.setBackgroundColor(Color.LTGRAY);
                            butk.setClickable(true);
                            //butk.setBackgroundColor(Color.LTGRAY);
                            butl.setClickable(true);
                           // butl.setBackgroundColor(Color.LTGRAY);
                        }

                    }
                }, 1000);


                ////end of testing///////////

            }
        }


        ///////////////////////////////


        new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
            @Override
            public void run() {

                if (win==1 && n2 == 0) {

                    if(mp2.isPlaying())
                    {
                        mp2.stop();
                    }

                    if(SFX==1) {
                    try {
                        mp3.reset();
                        AssetFileDescriptor afd=getAssets().openFd("lose2.mp3");
                        mp3.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                        mp3.prepare();
                        mp3.start();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }}

                    coins.setText("You lose !!");
                    coinsleft.setText("");
                    result.setText("");
                    calcscore(0);
                    buto.setVisibility(View.GONE);
                    butk.setVisibility(View.GONE);
                    butl.setVisibility(View.GONE);
                    img.setImageResource(R.drawable.comp);

                }

            }
        }, 1500);


        new Handler().postDelayed(new Runnable() {   ///back to main screen delayed after win
            @Override
            public void run() {

                if (win==1 && n2 == 0) {

                    startActivity(inte2);
                    finish();

                    //overridePendingTransition ( R.anim.leftin,0 );
                    overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
                    //overridePendingTransition(R.anim.cardin, R.anim.cardout);
                }

            }
        }, 2500);

    }}


    void compturn3()   //////difficult////////
    {

        if (n2 != 0) {

            win=1;
            final int n3 = n2;
            final Intent inte2 = new Intent(this, MainActivity.class);

            if (dp1[n2] == 1 && (n2 - dp2[n2] >= 0))   ////optimal move///
            {
                ////testing delayed result //////////////////
                n2 = n2 - dp2[n2];
                result.setText("Computer removed " + String.valueOf(n3 - n2) + " coins");

                new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                    @Override
                    public void run() {

                        coins.setText(String.valueOf(n2));
                        result.setText("");


                        if (n2 != 0) {
                            buto.setClickable(true);                        ///buttons now clickable again
                            //buto.setBackgroundColor(Color.LTGRAY);
                            butk.setClickable(true);
                            //butk.setBackgroundColor(Color.LTGRAY);
                            butl.setClickable(true);
                            //butl.setBackgroundColor(Color.LTGRAY);
                        }

                    }
                }, 1000);


                ////end of testing///////////

            } else                    ///reluctant move////
            {
                n2 = n2 - 1;
                // result.setText("Computer reluctantly removed 1 coin");
                result.setText("Computer removed 1 coin");
                //result.setTextColor(Color.GRAY);

                new Handler().postDelayed(new Runnable() {    ///delayed win  for comp
                    @Override
                    public void run() {

                        coins.setText(String.valueOf(n2));
                        result.setText("");


                        if (n2 != 0) {
                            buto.setClickable(true);                        ///buttons now clickable again
                            //buto.setBackgroundColor(Color.LTGRAY);
                            butk.setClickable(true);
                            //butk.setBackgroundColor(Color.LTGRAY);
                            butl.setClickable(true);
                            //butl.setBackgroundColor(Color.LTGRAY);
                        }

                    }
                }, 1000);


            }

               /* if(n2!=0) {
                    buto.setClickable(true);                        ///buttons now clickable again
                    buto.setBackgroundColor(Color.LTGRAY);
                    butk.setClickable(true);
                    butk.setBackgroundColor(Color.LTGRAY);
                    butl.setClickable(true);
                    butl.setBackgroundColor(Color.LTGRAY);
                }*/


            new Handler().postDelayed(new Runnable() {    ///delayed computer wins!
                @Override
                public void run() {

                    if (win==1 && n2 == 0) {

                        if(mp2.isPlaying())
                        {
                            mp2.stop();
                        }

                        if(SFX==1) {
                        try {
                            mp3.reset();
                            AssetFileDescriptor afd=getAssets().openFd("lose3.mp3");
                            mp3.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                            mp3.prepare();
                            mp3.start();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }}


                        coins.setText("You lose !!");
                        coinsleft.setText("");
                        result.setText("");
                        calcscore(0);
                        buto.setVisibility(View.GONE);
                        butk.setVisibility(View.GONE);
                        butl.setVisibility(View.GONE);
                        img.setImageResource(R.drawable.comp);

                    /*em++;
                    myeditor.putInt("MATCHES",em);
                    myeditor.commit();*/


                    }

                }
            }, 1500);


            new Handler().postDelayed(new Runnable() {   ///back to main screen delayed after win
                @Override
                public void run() {

                    if (win==1 && n2 == 0) {

                        startActivity(inte2);
                        finish();

                        //overridePendingTransition ( R.anim.leftin,0 );
                        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);

                    }

                }
            }, 2500);

        }
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


    public void calcscore(int val)  //val 1 means player won
    {
        sharepref = getSharedPreferences(SCORE_FILE, 0);
        SharedPreferences.Editor myeditor = sharepref.edit();

        int em = sharepref.getInt("em", 0); //easy matches played
        int ew = sharepref.getInt("ew", 0);//easy matches won
        int mm = sharepref.getInt("mm", 0);
        int mw = sharepref.getInt("mw", 0);
        int dm = sharepref.getInt("dm", 0);
        int dw = sharepref.getInt("dw", 0);

        switch(diflev)
        {
            case 1:  //easy
                em++;
                if(val==1) //you won
                ew++;
                myeditor.putInt("em", em);
                myeditor.putInt("ew", ew);
                break;

            case 2: //medium
                mm++;
                if(val==1)
                    mw++;
                myeditor.putInt("mm", mm);
                myeditor.putInt("mw", mw);
                break;

            case 3: //difficult
                dm++;
                if(val==1)
                    dw++;
                myeditor.putInt("dm", dm);
                myeditor.putInt("dw", dw);
                break;
        }

        myeditor.commit();
        //Toast.makeText(dusra.this,String.valueOf(matches), Toast.LENGTH_SHORT).show();

    }

}