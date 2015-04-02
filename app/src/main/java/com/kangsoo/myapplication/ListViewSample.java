package com.kangsoo.myapplication;

import android.app.Activity;
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


public class ListViewSample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_sample);

        String[] foods = {"Bacon","Ham","Tuna","Candy","Meatball","Potato"};

        //this is connect text and listview Item
        //ListAdapter myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, foods);
        ListAdapter myAdapter = new CustomAdapter(this, foods);
        ListView lvListViewSample = (ListView) findViewById(R.id.lvListViewSample);

        lvListViewSample.setAdapter(myAdapter);

        lvListViewSample.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String food = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(ListViewSample.this, food, Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}