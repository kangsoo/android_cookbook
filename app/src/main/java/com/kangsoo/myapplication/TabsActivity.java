package com.kangsoo.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TextView;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;


public class TabsActivity extends ActionBarActivity implements View.OnClickListener {

    TabHost th;
    TextView showResult;
    long startTime, stopTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        //reference TabHost Control
        th = (TabHost) findViewById(R.id.tbhTabsActivity);

        Button newTab = (Button) findViewById(R.id.btnAddNewTab);
        Button bStart = (Button) findViewById(R.id.btnStartWatch);
        Button bStop = (Button) findViewById(R.id.btnStopWatch);

        showResult = (TextView) findViewById(R.id.tvTabActivityResult);

        newTab.setOnClickListener(this);
        bStart.setOnClickListener(this);
        bStop.setOnClickListener(this);

        th.setup();

        //Create TabSpec
        TabHost.TabSpec tspec = th.newTabSpec("tag1");
        tspec.setContent(R.id.tab1);
        tspec.setIndicator("Stop Watch");
        th.addTab(tspec);

        tspec = th.newTabSpec("tag2");
        tspec.setContent(R.id.tab2);
        tspec.setIndicator("See a Movie");
        th.addTab(tspec);

        tspec = th.newTabSpec("tag3");
        tspec.setContent(R.id.tab3);
        tspec.setIndicator("Listen to Music");
        th.addTab(tspec);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tabs, menu);
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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnAddNewTab:
                TabHost.TabSpec ourSpec = th.newTabSpec("myTab1");
                ourSpec.setContent(new TabHost.TabContentFactory(){

                    @Override
                    public View createTabContent(String tag) {

                        TextView text = new TextView(TabsActivity.this);
                        text.setText("Oh~ my God~~~");
                        return (text);
                    }
                });
                ourSpec.setIndicator("New");
                th.addTab(ourSpec);
                break;

            case R.id.btnStartWatch:
                startTime = System.currentTimeMillis();

                break;
            case R.id.btnStopWatch:
                stopTime = System.currentTimeMillis();

                long diffTime = stopTime - startTime;
                int millis = (int) diffTime;
                int seconds = (int) diffTime / 1000;
                int minutes = seconds / 60;

                millis = millis % 100;
                seconds = seconds % 60;

//                showResult.setText(Long.toString(diffTime));
                showResult.setText(String.format("%d:%02d:%02d",minutes,seconds, millis));
                break;
        }
    }
}
