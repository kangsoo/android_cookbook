package com.kangsoo.myapplication;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;


public class SimpleBrowserActivity extends ActionBarActivity implements View.OnClickListener {

    WebView wb;
    EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_browser);

        etUrl = (EditText) findViewById(R.id.etUrl);

        Button btnGo = (Button) findViewById(R.id.btnGo);
        Button btnBack = (Button) findViewById(R.id.btnGoBack);
        Button btnForward = (Button) findViewById(R.id.btnGoForward);
        Button btnRefresh = (Button) findViewById(R.id.btnGoRefresh);
        Button btnHistory = (Button) findViewById(R.id.btnGoHistory);
        btnGo.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnForward.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnRefresh.setOnClickListener(this);

        wb = (WebView)findViewById(R.id.wbBrowser);

        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setLoadWithOverviewMode(true);
        wb.getSettings().setUseWideViewPort(true);

        wb.setWebViewClient(new ourViewClient());
        try{
            wb.loadUrl("http://www.naver.com");
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simple_browser, menu);
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
            case R.id.btnGo:
                wb.loadUrl(etUrl.getText().toString());
                //keyboard Hide
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etUrl.getWindowToken(),0);
                break;

            case R.id.btnGoBack:
                if(wb.canGoBack()){
                    wb.goBack();
                }
                break;

            case R.id.btnGoForward:
                if(wb.canGoForward()){
                    wb.goForward();
                }
                break;

            case R.id.btnGoHistory:
                wb.clearHistory();
                break;

            case R.id.btnGoRefresh:
                wb.reload();
                break;

        }
    }
}
