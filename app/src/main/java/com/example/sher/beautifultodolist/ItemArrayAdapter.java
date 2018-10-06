package com.example.sher.beautifultodolist;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ItemArrayAdapter extends ArrayAdapter<String> {
    private int listItemLayout;
    private List<String> arrayList = new LinkedList<>();


    public ItemArrayAdapter(Context context, int layoutId,List<String> itemList){
        super(context,layoutId,itemList);
        listItemLayout = layoutId;
        arrayList = itemList;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        // get the data item for this position
        String item = getItem(position);

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);


        // Lookup view for data population
        TextView rowItem = (TextView) convertView.findViewById(R.id.row_item);
        // Populate the data into the template view using the data object
        String stringDetails = arrayList.get(position);
        char done = stringDetails.charAt(stringDetails.length()-1);
        Log.d("checl char",String.valueOf(done));
        if(done=='1'){
           // stringDetails = stringDetails.replace('1',' ');
            rowItem.setText(stringDetails);
            rowItem.setPaintFlags(rowItem.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        }else {
           // stringDetails = stringDetails.replace('0',' ');
            rowItem.setText(stringDetails);
        }
        //rowItem.setText(stringDetails);
        // Return the completed view to render on screen
        return convertView;
    }


    }
