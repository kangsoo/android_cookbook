package com.kangsoo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.os.Handler;

public class ListViewSample extends Activity {

    boolean exitConfirmation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sample);

        // enables the activity icon as a 'home' button. required if "android:targetSdkVersion" > 14
        //getActionBar().setHomeButtonEnabled(true);


        String[] foods = {"SQLiteSample", "MainActivity", "LoginActivity",
                "CameraActivity", "GFXSurfaceView", "SoundStuff",
                "SlidingActivity", "TabsActivity", "SimpleBrowserActivity",
                "FlipperActivity", "SharedPrefActivity", "InternalDataFileStream"};

        //this is connect text and listview Item
        //ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);
        ListAdapter myAdapter = new CustomAdapter(this, foods);
        ListView lvListViewSample = (ListView) findViewById(R.id.lvListViewSample);

        lvListViewSample.setAdapter(myAdapter);

        lvListViewSample.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(ListViewSample.this, food, Toast.LENGTH_LONG).show();

                        try {
                            //get class from name String
                            Class ourClass = Class.forName("com.kangsoo.myapplication." + food.toString());
                            Intent itActivity = new Intent(ListViewSample.this, ourClass);
                            startActivity(itActivity);

                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                }
        );
    }

    @Override
    public void onBackPressed() {
        if (exitConfirmation) {
            super.onBackPressed();
            return;
        }

        this.exitConfirmation = true;
        Toast.makeText(this, "'뒤로'버튼을 한번더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                exitConfirmation = false;
            }
        }, 2000);
    }
}
