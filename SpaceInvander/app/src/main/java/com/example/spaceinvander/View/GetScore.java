package com.example.spaceinvander.View;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetScore extends AsyncTask<Void,String,String> {
    final String Base_URL = "";
    final String KEY = "key";
    final String PAGE = "page";

    public GetScore(){

    }

    @Override
    protected String doInBackground(Void... voids) {
        String res ="";
        Uri buildUri = Uri.parse(Base_URL).buildUpon()
                        .appendQueryParameter(KEY,"2017730050")
                        .appendQueryParameter(PAGE,"1")
                        .build();
        URL reqestURL = null;
        HttpURLConnection conn = null;
        InputStream inputStream = null;
        try{
            reqestURL = new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try{
            conn = (HttpURLConnection)reqestURL.openConnection();
            conn.setReadTimeout(10000);
            conn.setRequestMethod("GET");
            conn.connect();

            int response = conn.getResponseCode();

            if(response==200){
                inputStream = conn.getInputStream();
                res = convertToString(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                conn.disconnect();
                if(inputStream!=null){
                    inputStream.close();
                }
                if(conn!=null){
                    conn.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    private String convertToString(InputStream inputStream){
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try{
            while ((line = br.readLine())!=null){
                builder.append(line);
            }
            if(builder.length()==0){
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    @Override
    protected void onPostExecute(String result){

    }

}
