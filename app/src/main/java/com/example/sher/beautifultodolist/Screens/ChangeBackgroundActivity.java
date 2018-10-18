package com.example.sher.beautifultodolist.Screens;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sher.beautifultodolist.R;

public class ChangeBackgroundActivity extends AppCompatActivity {
    String themeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        themeString = preferences.getString("Theme","");
        changeThemeStart();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_background);

        ImageView imageView1 = (ImageView) findViewById(R.id.image1);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangeBackgroundActivity.this);
                SharedPreferences.Editor  editor = preferences.edit();
                editor.putString("Image","image1");
                editor.apply();
                editor.commit();
                startActivity(new Intent(ChangeBackgroundActivity.this, MainActivity.class));
                finish();
            }
        });

        ImageView imageView2 = (ImageView) findViewById(R.id.image2);
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangeBackgroundActivity.this);
                SharedPreferences.Editor  editor = preferences.edit();
                editor.putString("Image","image2");
                editor.apply();
                editor.commit();
                startActivity(new Intent(ChangeBackgroundActivity.this, MainActivity.class));
                finish();
            }
        });


        ImageView imageView3 = (ImageView) findViewById(R.id.image3);
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangeBackgroundActivity.this);
                SharedPreferences.Editor  editor = preferences.edit();
                editor.putString("Image","image3");
                editor.apply();
                editor.commit();
                startActivity(new Intent(ChangeBackgroundActivity.this, MainActivity.class));
                finish();
            }
        });


        ImageView imageView4 = (ImageView) findViewById(R.id.image4);
        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangeBackgroundActivity.this);
                SharedPreferences.Editor  editor = preferences.edit();
                editor.putString("Image","image4");
                editor.apply();
                editor.commit();
                startActivity(new Intent(ChangeBackgroundActivity.this, MainActivity.class));
                finish();
            }
        });

        ImageView imageView5 = (ImageView) findViewById(R.id.image5);
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangeBackgroundActivity.this);
                SharedPreferences.Editor  editor = preferences.edit();
                editor.putString("Image","image5");
                editor.apply();
                editor.commit();
                startActivity(new Intent(ChangeBackgroundActivity.this, MainActivity.class));
                finish();
            }
        });


        ImageView imageView6 = (ImageView) findViewById(R.id.image6);
        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangeBackgroundActivity.this);
                SharedPreferences.Editor  editor = preferences.edit();
                editor.putString("Image","image6");
                editor.apply();
                editor.commit();
                startActivity(new Intent(ChangeBackgroundActivity.this, MainActivity.class));
                finish();
            }
        });

        ImageView imageView7 = (ImageView) findViewById(R.id.image7);
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangeBackgroundActivity.this);
                SharedPreferences.Editor  editor = preferences.edit();
                editor.putString("Image","image7");
                editor.apply();
                editor.commit();
                startActivity(new Intent(ChangeBackgroundActivity.this, MainActivity.class));
                finish();
            }
        });


        ImageView imageView8 = (ImageView) findViewById(R.id.image8);
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangeBackgroundActivity.this);
                SharedPreferences.Editor  editor = preferences.edit();
                editor.putString("Image","image8");
                editor.apply();
                editor.commit();
                startActivity(new Intent(ChangeBackgroundActivity.this, MainActivity.class));
                finish();
            }
        });

        ImageView imageView9 = (ImageView) findViewById(R.id.image9);
        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangeBackgroundActivity.this);
                SharedPreferences.Editor  editor = preferences.edit();
                editor.putString("Image","image9");
                editor.apply();
                editor.commit();
                startActivity(new Intent(ChangeBackgroundActivity.this, MainActivity.class));
                finish();
            }
        });

        ImageView imageViewDefault = (ImageView) findViewById(R.id.whiteimage_default);
        imageViewDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(ChangeBackgroundActivity.this);
                SharedPreferences.Editor  editor = preferences.edit();
                editor.putString("Image","imagewhite_default");
                editor.apply();
                editor.commit();
                startActivity(new Intent(ChangeBackgroundActivity.this, MainActivity.class));
                finish();
            }
        });


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
