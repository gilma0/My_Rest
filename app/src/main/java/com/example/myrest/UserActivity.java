package com.example.myrest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    public static final String user="names";
    ListView typeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        typeList=(ListView)findViewById(R.id.typeList);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Starters");
        arrayList.add("Main");
        arrayList.add("Deserts");
        arrayList.add("Drinks");
        arrayList.add("Alcohol");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
        typeList.setAdapter(arrayAdapter);
        typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(UserActivity.this,startersMenu.class);
                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(UserActivity.this,mainMenu.class);
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(UserActivity.this,desertsMenu.class);
                    startActivity(intent);
                }
                if(position == 3){
                    Intent intent = new Intent(UserActivity.this,drinksMenu.class);
                    startActivity(intent);
                }
                if(position == 4){
                    Intent intent = new Intent(UserActivity.this,alcoholMenu.class);
                    startActivity(intent);
                }
            }
        });
    }
}
