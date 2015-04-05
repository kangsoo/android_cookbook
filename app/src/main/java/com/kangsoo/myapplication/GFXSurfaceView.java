package com.kangsoo.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

import static android.view.WindowManager.LayoutParams.*;

/**
 * Created by bsnc on 2015-04-05.
 */
public class GFXSurfaceView extends Activity implements View.OnTouchListener {

    MyBringBackSurface ourSurface;
    float x, y, sX, fX, sY, fY, dX, dY, aniX, aniY, scaledX, scaledY;
    Bitmap greeballBitmap;
    Bitmap plusBitmap;
    PowerManager.WakeLock wL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //wake-lock
        /*
        <uses-permission android:name="android.permission.WAKE_LOCK"/>
         */
        PowerManager pwm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.getWindow().setFlags(FLAG_KEEP_SCREEN_ON,FLAG_KEEP_SCREEN_ON);

        //wL = pwm.newWakeLock(PowerManager.FULL_WAKE_LOCK,"whatever");
        //android.view.WindowManager.LayoutParams#FLAG_KEEP_SCREEN_ON

        super.onCreate(savedInstanceState);

        //wL.acquire();

        ourSurface = new MyBringBackSurface(this);
        ourSurface.setOnTouchListener(this);
        x = 0;
        y = 0;
        sX = 0;
        sY = 0;
        fX = 0;
        fY = 0;
        dX = dY = aniX = aniY = scaledX = scaledY = 0;

        greeballBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.greenball);
        plusBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plusimage);
        setContentView(ourSurface);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ourSurface.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //wL.release();
        ourSurface.pause();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        x = event.getX();
        y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                sX = event.getX();
                sY = event.getY();
                fX = fY = dX = dY = aniX = aniY = scaledX = scaledY = 0;
                break;

            case MotionEvent.ACTION_UP:
                fX = event.getX();
                fY = event.getY();
                dX = fX - sX;
                dY = fY - sY;

                scaledX = dX / 30;
                scaledY = dY / 30;
                x = 0;
                y = 0;
                break;
        }
        return true;
    }

    /**
     * Created by bsnc on 2015-04-05.
     */
    public class MyBringBackSurface extends SurfaceView implements Runnable {

        SurfaceHolder ourHolder;
        Boolean isRunning = false;
        Thread ourThread = null;

        public MyBringBackSurface(Context context) {
            super(context);
            ourHolder = getHolder();
        }

        public void resume(){
            isRunning = true;
            ourThread = new Thread(this);
            ourThread.start();
        }

        public void pause(){
            isRunning = false;
            while (true){
                try{
                    ourThread.join();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                break;
            }
            ourThread = null;
        }

        @Override
        public void run() {
            while (isRunning){
                if(!ourHolder.getSurface().isValid()){
                    continue;
                }
                //lock Canvas. because No other activity can access canvas
                Canvas canvas= ourHolder.lockCanvas();
                canvas.drawRGB(02,02,150);
                if(x != 0 && y != 0){
                    canvas.drawBitmap(greeballBitmap,x-(greeballBitmap.getWidth()/2),y-(greeballBitmap.getHeight()/2),null);
                }

                if(sX != 0 && sY != 0){
                    canvas.drawBitmap(plusBitmap,sX-(plusBitmap.getWidth()/2),sY-(plusBitmap.getHeight()/2),null);
                }

                if(fX != 0 && fY != 0){
                    canvas.drawBitmap(greeballBitmap,fX-(greeballBitmap.getWidth()/2)-aniX,fY-(greeballBitmap.getHeight()/2)-aniY,null);
                    canvas.drawBitmap(plusBitmap,fX-(plusBitmap.getWidth()/2),fY-(plusBitmap.getHeight()/2),null);
                }

                //animate scaled Length
                aniX = aniX + scaledX;
                aniY = aniY + scaledY;

                ourHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

}
