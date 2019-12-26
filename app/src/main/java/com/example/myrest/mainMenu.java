package com.example.myrest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//import com.firebase.ui.database.FirebaseListAdapter;
//import com.firebase.ui.database.FirebaseListOptions;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;

public class mainMenu extends AppCompatActivity {
    private ListView lv;
    private FirebaseDatabase mydata;
    User user;//user to get from previous activity for order
    Item selectedItem; //item to be moved to order
    DatabaseReference myref;
    ArrayList<Item> list = new ArrayList<>();
    //ArrayList<String> descList = new ArrayList<>();
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_menu);

        mydata = FirebaseDatabase.getInstance();
        myref = mydata.getReference().child("menu").child("Main");

        lv = (ListView) findViewById(R.id.alcoholListView);
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item value = dataSnapshot.getValue(Item.class);
                list.add(value);
                // descList.add(value.getDescription());
                //arrayList.add(value.getName());
                adapter = new CustomListAdapter(mainMenu.this, R.layout.main_layout, list);
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
    /*private ListView lv;
    private FirebaseDatabase mydata;
    DatabaseReference myref;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;
    //private FirebaseListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mydata = FirebaseDatabase.getInstance();
        myref = mydata.getReference().child("menu").child("Main");

        lv = (ListView) findViewById(R.id.mainListView);
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item value = dataSnapshot.getValue(Item.class);
                arrayList.add(value.getName());
                arrayList.add(value.getDescription());
                //arrayList.add(value.getName());
                arrayAdapter = new ArrayAdapter<String>(mainMenu.this, android.R.layout.simple_list_item_1, arrayList);
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


     /*   lv = (ListView) findViewById(R.id.mainListView);
        Query query = FirebaseDatabase.getInstance().getReference().child("menu").child("Main");
        FirebaseListOptions<Item> options;
        options = new FirebaseListOptions<Item>.Builder<>()
                .setLayout(R.layout.food_item_desc)
                .setQuery(query, Item.class)
                .build();*/

     /*lv = (ListView) findViewById(R.id.mainListView);
        DatabaseReference foodReference = FirebaseDatabase.getInstance().getReference().child("menu").child("Main");
        lv.setAdapter(new FirebaseListAdapter<Item>(this, Item.class, android.R.layout.one_line_list_item, foodReference));*/
    }
}
