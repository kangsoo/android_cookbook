package com.kangsoo.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by bsnc on 2015-04-05.
 */
public class InternalDataFileStream extends Activity implements View.OnClickListener {

    Button saveButton;
    Button readButton;
    EditText etSaveSharedPref;
    FileOutputStream fos;
    FileInputStream fis;
    String fileNameforFileStream = "InternalString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_pref);

        saveButton = (Button) findViewById(R.id.btnSavePref);
        readButton = (Button) findViewById(R.id.btnReadPref);
        etSaveSharedPref = (EditText) findViewById(R.id.etSharedPref);

        saveButton.setOnClickListener(this);
        readButton.setOnClickListener(this);

        try {
            fos = openFileOutput(fileNameforFileStream,Context.MODE_PRIVATE);

            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btnSavePref:
                OutputFileStream();
                break;

            case R.id.btnReadPref:
                new loadFileStream().execute(fileNameforFileStream);
                break;

        }
    }

    private void OutputFileStream() {
        String dataString = etSaveSharedPref.getText().toString();

        //saving Data Via Fileoutput
        try {
            fos = openFileOutput(fileNameforFileStream, Context.MODE_PRIVATE);
            fos.write(dataString.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        etSaveSharedPref.setText("");
    }

    public class loadFileStream extends AsyncTask<String, Integer, String>{

        ProgressDialog pdialog;

        @Override
        protected void onPreExecute() {
           // super.onPreExecute();
            pdialog = new ProgressDialog(InternalDataFileStream.this);
            pdialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            //pdialog.setProgressDrawable(getResources().getDrawable(R.drawable.greenball));
            pdialog.setMax(100);
            pdialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            etSaveSharedPref.setText(s);
        }

        @Override
        protected String doInBackground(String... params) {
            String collected = null;

            for(int i =0; i<20; i++){
                publishProgress(5);
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            pdialog.dismiss();

            try {
                fis = openFileInput(fileNameforFileStream);
                byte[] dataArray = new byte[fis.available()];

                while (fis.read(dataArray) != -1){
                    collected = new String(dataArray);
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);
            pdialog.incrementProgressBy(values[0]);
        }
    }
}
