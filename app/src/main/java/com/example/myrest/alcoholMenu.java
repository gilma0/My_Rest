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

public class alcoholMenu extends AppCompatActivity {

    /*private ListView lv;
    private FirebaseDatabase mydata;
    DatabaseReference myref;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;*/
    private ListView lv;
    private FirebaseDatabase mydata;
    DatabaseReference myref;
    ArrayList<Item> list = new ArrayList<>();
    //ArrayList<String> descList = new ArrayList<>();
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcohol_menu);

        mydata = FirebaseDatabase.getInstance();
        myref = mydata.getReference().child("menu").child("Alcohol");

        lv = (ListView) findViewById(R.id.alcoholListView);
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item value = dataSnapshot.getValue(Item.class);
                list.add(value);
                // descList.add(value.getDescription());
                //arrayList.add(value.getName());
                adapter = new CustomListAdapter(alcoholMenu.this, R.layout.main_layout, list);
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
        /*mydata = FirebaseDatabase.getInstance();
        myref = mydata.getReference().child("menu").child("Alcohol");

        lv = (ListView) findViewById(R.id.alcoholListView);
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item value = dataSnapshot.getValue(Item.class);
                arrayList.add(value.getName());
                arrayList.add(value.getDescription());
                //arrayList.add(value.getName());
                arrayAdapter = new ArrayAdapter<String>(alcoholMenu.this, android.R.layout.simple_list_item_1, arrayList);
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
