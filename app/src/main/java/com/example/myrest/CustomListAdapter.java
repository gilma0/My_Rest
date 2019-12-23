package com.example.myrest;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<Item> {

    ArrayList<Item> items;
    Context context;
    int resource;

    public CustomListAdapter(Context context, int resource, ArrayList<Item> items){
        super(context, resource, items);
        this.context = context;
        this.items = items;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext()
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.main_layout, null, true);
        }
        Item item = getItem(position);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.foodImg);
        Picasso.get().load(item.getImageUrl()).fit().into(imageView);

        TextView txtName = (TextView) convertView.findViewById(R.id.foodName);
        txtName.setText(item.getName()); // was name

        TextView txtDesc = (TextView) convertView.findViewById(R.id.foodDesc);
        txtDesc.setText(item.getDescription());

        return convertView;
    }
}
