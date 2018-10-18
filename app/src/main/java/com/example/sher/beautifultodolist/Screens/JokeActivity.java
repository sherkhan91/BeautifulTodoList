package com.example.sher.beautifultodolist.Screens;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sher.beautifultodolist.Common.CommonUtils;
import com.example.sher.beautifultodolist.Updater.FetchJoke;
import com.example.sher.beautifultodolist.R;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String themeString = preferences.getString("Theme","");
        if(themeString.equalsIgnoreCase("Green Theme"))
            setTheme(R.style.green);
        else if(themeString.equalsIgnoreCase("Yellow Theme"))
            setTheme(R.style.yellow);
        else if(themeString.equalsIgnoreCase("Dark brown Theme"))
            setTheme(R.style.darkbrown);
        else
            setTheme(R.style.AppTheme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        if(!CommonUtils.isInternetConnected(this)) {
            TextView actualJoke = (TextView) findViewById(R.id.actualjoke);
            actualJoke.setText("Oooops! Internet Connectivity problem! please try again...");
        }else {
            FetchJoke.getNewJoke(this);
        }


       // TextView actualJoke =(TextView) findViewById(R.id.actualjoke);

        Button jokeButton = (Button) findViewById(R.id.newJokeButton);
        jokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CommonUtils.isInternetConnected(JokeActivity.this)) {
                    FetchJoke.getNewJoke(JokeActivity.this);
                } else{
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(JokeActivity.this);
                    alertDialog.setTitle("Internet Connection Problem!");
                    alertDialog.setMessage("Sorry, No Internet Connection, Please try again.... ");
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    alertDialog.create();
                    alertDialog.show();
                }

            }
        });

    }
}
