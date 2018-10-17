package com.example.sher.beautifultodolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChangeBackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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



    }
}
