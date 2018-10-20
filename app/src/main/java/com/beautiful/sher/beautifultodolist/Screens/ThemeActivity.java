package com.beautiful.sher.beautifultodolist.Screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.beautiful.sher.beautifultodolist.R;

public class ThemeActivity extends AppCompatActivity {

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


        Log.d("check ","String got:  "+themeString);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme);



        RadioButton greenThemeButton = (RadioButton) findViewById(R.id.greenColorTheme);
        RadioButton yellowThemeButton = (RadioButton) findViewById(R.id.goldenYellowColorTheme);
        RadioButton goldenBrownThemeButton = (RadioButton) findViewById(R.id.goldenBrownColorTheme);
        RadioButton royalBlueThemeButton = (RadioButton) findViewById(R.id.royalBlueColorTheme);

        final RadioButton[] radioButtons = new RadioButton[4];
        radioButtons[0]= greenThemeButton;
        radioButtons[1]= yellowThemeButton;
        radioButtons[2]= goldenBrownThemeButton;
        radioButtons[3]= royalBlueThemeButton;


        for(int i=0;i<radioButtons.length;i++){
            final int j  =i;
            radioButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String themeName = radioButtons[j].getText().toString();
                    Log.d("check","Clicked: "+themeName);
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ThemeActivity.this);
                    SharedPreferences.Editor editor  = preferences.edit();
                    editor.putString("Theme",themeName);
                    editor.apply();
                    editor.commit();

                    Intent intent = new Intent(ThemeActivity.this, ThemeActivity.class);

                    for(int k=0;k<radioButtons.length;k++){
                        if(k!=j){
                            radioButtons[k].setChecked(false);
                        }
                    }
                    startActivity(intent);
                    finish();
                }
            });
        }


    }
}
