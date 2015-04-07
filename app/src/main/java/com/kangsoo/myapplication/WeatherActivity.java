package com.kangsoo.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kangsoo.myapplication.util.HttpHelper;
import com.kangsoo.myapplication.util.MyCallBackEventListener;


public class WeatherActivity extends ActionBarActivity {

    EditText etCity;
    EditText etState;
    TextView tvWeather;
    Button btnWeather;
    Button btnDisplay;
    HttpHelper webHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        etCity = (EditText) findViewById(R.id.etCity);
        etState = (EditText) findViewById(R.id.etState);
        tvWeather = (TextView) findViewById(R.id.tvWeachter);
        btnWeather = (Button) findViewById(R.id.btnGetWeather);
        btnDisplay = (Button) findViewById(R.id.btnWeatherDisplay);

        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "http://api.openweathermap.org/data/2.5/weather?q=Seoul,kr";
                String urlXML = "http://api.openweathermap.org/data/2.5/weather?q=Seoul&mode=xml";

                webHelper = new HttpHelper(url);
                webHelper.setKangooEventListener(new MyCallBackEventListener() {
                    @Override
                    public void onPostResult(String result) {
                        tvWeather.setText("onPostResult Direct:" + result);
                    }
                });
                if(webHelper.isConnected(WeatherActivity.this)){
//                    tvWeather.setText(webHelper.resultData);
                }
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                tvWeather.setText(webHelper.resultData);
            }
        });
    }
}
