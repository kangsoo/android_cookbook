package com.kangsoo.myapplication;

import android.app.Activity;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

/**
 * Created by bsnc on 2015-04-05.
 */
public class SoundStuff extends Activity implements View.OnClickListener, View.OnLongClickListener{

    MediaPlayer mpShoot, mpBackGround;
    SoundPool.Builder sp21;
    int explosion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View v = new View(this);
        v.setOnClickListener(this);
        v.setOnLongClickListener(this);
        setContentView(v);

        mpShoot = MediaPlayer.create(this, R.drawable.shootmusic);
        mpBackGround = MediaPlayer.create(this, R.drawable.splashmusic);

/*
        sp21 = new SoundPool.Builder();
        sp21.setMaxStreams(5);

        sp21.setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build());
*/

    }

    @Override
    protected void onPause() {
        super.onPause();
        mpShoot.release();
        mpBackGround.release();
    }

    @Override
    public void onClick(View v) {
        mpShoot.start();
    }

    @Override
    public boolean onLongClick(View v) {
        mpBackGround.start();
        return false;
    }
}
