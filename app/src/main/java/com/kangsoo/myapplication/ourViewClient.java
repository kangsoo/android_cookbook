package com.kangsoo.myapplication;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by bsnc on 2015-04-05.
 */
public class ourViewClient extends WebViewClient {

    public ourViewClient() {
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
//        return super.shouldOverrideUrlLoading(view, url);
    }
}
