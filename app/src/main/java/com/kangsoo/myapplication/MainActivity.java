package com.kangsoo.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;


public class MainActivity extends ActionBarActivity {

    private final String TAG = "debugMessage";
    MyService sampleMyService;
    boolean isBound = false;

    public void showTime(){
        String currentTime = sampleMyService.getCurrentTime();
        TextView myText = (TextView) findViewById(R.id.tvBoundService);
        Log.i(TAG, "showTime method called~");
        myText.setText(currentTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Intent it = new Intent(this, MyService.class);
        bindService(it,myConnection, Context.BIND_AUTO_CREATE);

        Button myButton = (Button) findViewById(R.id.btnBoundService);
        Log.i(TAG, "onCreate. BindService");

        myButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTime();
                    }
                }
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MyService.MyLocalBinder binder = (MyService.MyLocalBinder) service;

            sampleMyService = binder.getService();
            Log.i(TAG, "ServiceConnection Binder GetService. iBound~");
            isBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            Log.i(TAG, "iBound~false");

        }
    };
}
