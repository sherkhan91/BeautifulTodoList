package com.example.sher.beautifultodolist;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }
}
