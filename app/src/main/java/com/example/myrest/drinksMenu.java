package com.example.myrest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class drinksMenu extends AppCompatActivity {
    private ListView lv;
    private FirebaseDatabase mydata;
    Item selectedItem; //item to be moved to order
    //User user;//user to get from previous activity for order
    static String user;
    DatabaseReference myref;
    ArrayList<Item> list = new ArrayList<>();
    //ArrayList<String> descList = new ArrayList<>();
    CustomListAdapter adapter;
    ArrayList<String> keyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_menu);

        user = getIntent().getExtras().getString("userID");
        mydata = FirebaseDatabase.getInstance();
        myref = mydata.getReference().child("menu").child("Drinks");

        lv = (ListView) findViewById(R.id.alcoholListView);
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item value = dataSnapshot.getValue(Item.class);
                list.add(value);
                keyList.add(dataSnapshot.getKey());
                // descList.add(value.getDescription());
                //arrayList.add(value.getName());
                adapter = new CustomListAdapter(drinksMenu.this, R.layout.main_layout, list);
                lv.setAdapter(adapter);
                //arrayAdapter = new ArrayAdapter<String>(alcoholMenu.this, android.R.layout.simple_list_item_1, descList);
                //lv.setAdapter(arrayAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item choice = list.get(position);
                Toast.makeText(drinksMenu.this, keyList.get(position), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplication(), orderActivity.class);
                intent.putExtra("userID", user);
                intent.putExtra("itemName", choice.getName());
                intent.putExtra("itemIMG", choice.getImageUrl());
                intent.putExtra("itemID", keyList.get(position));
                intent.putExtra("itemType", choice.getTypeOf());
                intent.putExtra("itemDesc", choice.getDescription());
                startActivity(intent);
            }
        });
    /*private ListView lv;
    private FirebaseDatabase mydata;
    DatabaseReference myref;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinks_menu);

        mydata = FirebaseDatabase.getInstance();
        myref = mydata.getReference().child("menu").child("Drinks");

        lv = (ListView) findViewById(R.id.drinksListView);
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item value = dataSnapshot.getValue(Item.class);
                arrayList.add(value.getName());
                arrayList.add(value.getDescription());
                //arrayList.add(value.getName());
                arrayAdapter = new ArrayAdapter<String>(drinksMenu.this, android.R.layout.simple_list_item_1, arrayList);
                lv.setAdapter(arrayAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }
}
