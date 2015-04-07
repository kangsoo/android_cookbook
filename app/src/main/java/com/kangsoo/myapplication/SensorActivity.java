package com.kangsoo.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

// to-do : Tutorial 128~
public class SensorActivity extends ActionBarActivity implements SensorEventListener {

    SensorManager sm;
    float x, y, sensorX, sensorY;
    Bitmap ball;
    MyBringBackSurface ourBringBackSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new MyBringBackSurface(this));

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
            Sensor ss = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
            sm.registerListener(this, ss, SensorManager.SENSOR_DELAY_NORMAL);
        }

        ball = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        x = y = sensorY = sensorY = 0;

        ourBringBackSurface = new MyBringBackSurface(this);
        ourBringBackSurface.resume();

        setContentView(ourBringBackSurface);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sensorX = event.values[0];
        sensorY = event.values[1];

    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public class MyBringBackSurface extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder;
        Thread ourThread = null;
        boolean isRunning = false;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder();
        }

        public void pause() {
            isRunning = false;
            while (true) {

                try {
                    ourThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            }

        }

        public void resume() {
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        @Override
        public void run() {
            while (isRunning) {
                if (!ourHolder.getSurface().isValid())
                    continue;

                Canvas canvas = ourHolder.lockCanvas();
                canvas.drawRGB(02, 02, 150);
                float centerX = canvas.getWidth() / 2;
                float centerY = canvas.getHeight() / 2;
                canvas.drawBitmap(ball, centerX + sensorX * 20,
                        centerY + sensorY * 20, null);
                ourHolder.unlockCanvasAndPost(canvas);

            }

        }
    }
}
