package com.example.spaceinvander.View;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

public class ScoreTask {
    final String BASE_URL = "http://p3b.labftis.net/api.php";
    ActivityInterface ui;
    Context context;
    Gson gson;

    public ScoreTask(Context context, ActivityInterface ui){
        this.context=context;
        this.ui=ui;
        this.gson=new Gson();
    }

    public void executeGet(final int npm){
        RequestQueue queue = Volley.newRequestQueue(this.context);
        String url=BASE_URL+"?api_key"+npm;
        StringRequest req=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                processResult(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "");
            }
        });
        queue.add(req);
    }

    public void processResult(String response){
        Log.d("masuk", "");
        int idxHighScore = response.indexOf("value");
        int idxLast = idxHighScore+8;
        int ct = 0;
        while (true){
            if((int)response.charAt(idxLast+ct)-48<=9 && (int)response.charAt(idxLast+ct)-48>=0){
                ct++;
            }
            else{
                break;
            }
        }
        String highScore = response.substring(idxLast,idxLast+ct);
        this.ui.sendResult(highScore);
    }
}
