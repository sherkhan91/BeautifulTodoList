package com.beautiful.sher.beautifultodolist.Screens.ListAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.beautiful.sher.beautifultodolist.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter<String> {
    private int listItemLayout;
    private List<String> arrayList = new LinkedList<>();
    private Activity activity = null;



    public ItemArrayAdapter(Activity context, int layoutId,List<String> itemList){
        super(context,layoutId,itemList);
        listItemLayout = layoutId;
        arrayList = itemList;
        activity = context;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        // get the data item for this position
        String item = getItem(position);

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        // Lookup view for data population
        TextView rowItem = (TextView) convertView.findViewById(R.id.row_item);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String imageName = preferences.getString("Image","");
        if(imageName.equalsIgnoreCase("image1")) {
            rowItem.setTextColor(Color.WHITE);
        }else if(imageName.equalsIgnoreCase("image2")){
            rowItem.setTextColor(Color.BLACK);
        }else if(imageName.equalsIgnoreCase("image3")){
            rowItem.setTextColor(Color.BLACK);
        } else if(imageName.equalsIgnoreCase("image4")){
            rowItem.setTextColor(Color.BLACK);
        }else if(imageName.equalsIgnoreCase("image5")){
            rowItem.setTextColor(Color.WHITE);
        }else if(imageName.equalsIgnoreCase("image6")){
            rowItem.setTextColor(Color.BLACK);
        }else if(imageName.equalsIgnoreCase("image7")){
            rowItem.setTextColor(Color.BLACK);
        }else if(imageName.equalsIgnoreCase("image8")){
            rowItem.setTextColor(Color.WHITE);
        }else if(imageName.equalsIgnoreCase("image9")){
            rowItem.setTextColor(Color.WHITE);
        }else if(imageName.equalsIgnoreCase("imagewhite_default")){
            rowItem.setTextColor(Color.BLACK);
        }





        // Populate the data into the template view using the data object
        String stringDetails = arrayList.get(position);
        char done = stringDetails.charAt(stringDetails.length()-1);
        if(done=='1'){
            stringDetails = stringDetails.replace('1',' ');
            rowItem.setText(stringDetails);
            rowItem.setPaintFlags(rowItem.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
            stringDetails = stringDetails.replace('0',' ');
            rowItem.setText(stringDetails);
        }
        //rowItem.setText(stringDetails);
        // Return the completed view to render on screen
        return convertView;
    }


    }
