package com.kangsoo.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SharedPrefActivity extends ActionBarActivity implements View.OnClickListener {

    Button saveButton;
    Button readButton;
    EditText etSaveSharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);

        saveButton = (Button) findViewById(R.id.btnSavePref);
        readButton = (Button) findViewById(R.id.btnReadPref);
        etSaveSharedPref = (EditText) findViewById(R.id.etSharedPref);

        saveButton.setOnClickListener(this);
        readButton.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shared_pref, menu);
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

        switch(v.getId()){
            case R.id.btnSavePref:

                SharedPreferences sharedPref = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();

                editor.putString("username", etSaveSharedPref.getText().toString());
                editor.apply();

                Toast.makeText(this, "saved!", Toast.LENGTH_LONG).show();

                break;

            case R.id.btnReadPref:
                SharedPreferences readSharedPref = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);

                String username = readSharedPref.getString("username","");

                Toast.makeText(this, username, Toast.LENGTH_LONG).show();

                break;

        }
    }
}
