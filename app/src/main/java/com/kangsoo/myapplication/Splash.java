package com.kangsoo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 */
public class Splash extends Activity {

    Button btnExitSplash;
    Intent intentMainActivity;
    MediaPlayer mpSplashSong;

    @Override
    protected void onPause() {
        super.onPause();
        mpSplashSong.release();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Play Background Music
        mpSplashSong = MediaPlayer.create(this, R.drawable.splashmusic);
        mpSplashSong.start();

        //androidmanifest.xml's intent-filter action name
        intentMainActivity = new Intent("com.kangsoo.myapplication.LISTVIEWSAMPLE");

        //Don't wait 5 second. and Music
        btnExitSplash = (Button) findViewById(R.id.dummy_button);

        btnExitSplash.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();
                    //startActivity(intentMainActivity);
                }
            }
        );

        //Thread Count 5 seconds and then Start Main Activity
        Thread timer = new Thread(){

            public void run(){

                try{
                    //5 seconds
                    sleep(5000);

                }catch(InterruptedException e){
                    //What's wrong?
                    e.printStackTrace();

                }finally {
                    mpSplashSong.release();
                    startActivity(intentMainActivity);
                }
            }
        };

        //Start Thread
        timer.start();
    }
}
