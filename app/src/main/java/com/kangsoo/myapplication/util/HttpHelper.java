package com.kangsoo.myapplication.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by bsnc on 2015-04-07.
 */
public class HttpHelper {

    public String resultData = "";

    private MyCallBackEventListener mListener;

//    EventListener OnPostResult = new EventListener();

    public HttpHelper() {
    }

    public HttpHelper(String url) {
        new HttpAsyncTask().execute(url);
    }

    public void setKangooEventListener(MyCallBackEventListener listener){
        mListener = listener;
    }

    public void setUrl(String url){
        new HttpAsyncTask().execute(url);
    }

    public static String GET(String url) {
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        StringBuffer resultString = new StringBuffer("");
        String nl = System.getProperty("line.separator");

        while ((line = bufferedReader.readLine()) != null) {
            resultString.append(line + nl);
            //result += line;
        }

        bufferedReader.close();
        ;
        inputStream.close();
        return resultString.toString();

    }

    public boolean isConnected(Context c) {

        ConnectivityManager connMgr = (ConnectivityManager) c.getSystemService(c.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            //etResponse.setText(result);
            mListener.onPostResult(result);
        }
    }

}
