package com.example.sher.beautifultodolist.Updater;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import android.app.Activity;

import com.example.sher.beautifultodolist.R;

public class FetchJoke {
    private static String jokeURL = "https://api.chucknorris.io/jokes/random";
    private static Activity activity;


    public static void getNewJoke(Activity context){
        activity = context;
        new FetchJoke.jokeUpdater().execute();

    }

    private static class jokeUpdater extends AsyncTask<Void, Void,String>{

        String joke = "joke";
        public void onPreExecute(){
            super.onPreExecute();
        }

        public String doInBackground(Void...params){
            OkHttpClient httpClient = new OkHttpClient();
            Request request  =  new Request.Builder().url(jokeURL).build();
            try {

                Response response = httpClient.newCall(request).execute();
                JSONObject reader = new JSONObject(response.body().string());
                joke = reader.getString("value");
                Log.d("joke: ",joke);
            }catch (IOException e){
                e.printStackTrace();
            }catch(JSONException je){
                je.printStackTrace();
            }
            return joke;
        }

        public void onPostExecute(String result){
           // super.onPostExecute(result);
            TextView jokeTextView  = (TextView) activity.findViewById(R.id.actualjoke);
            jokeTextView.setText(result);
        }
    }
}
