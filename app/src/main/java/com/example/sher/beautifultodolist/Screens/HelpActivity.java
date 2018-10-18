package com.example.sher.beautifultodolist.Screens;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sher.beautifultodolist.R;

public class HelpActivity extends AppCompatActivity {
    String themeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        themeString = preferences.getString("Theme","");
        changeThemeStart();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);


        TextView link = (TextView) findViewById(R.id.linkedIn);

    }
    public void changeThemeStart(){

        if(themeString.equalsIgnoreCase("Green Theme"))
            setTheme(R.style.green);
        else if(themeString.equalsIgnoreCase("Yellow Theme"))
            setTheme(R.style.yellow);
        else if(themeString.equalsIgnoreCase("Dark brown Theme"))
            setTheme(R.style.darkbrown);
        else
            setTheme(R.style.AppTheme);
    }
}
