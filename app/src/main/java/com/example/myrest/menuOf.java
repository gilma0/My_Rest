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

public class menuOf extends AppCompatActivity {
    private ListView lv;
    private FirebaseDatabase mydata;
    static String user;
    private String delete;
    DatabaseReference myref;
    ArrayList<Item> list = new ArrayList<>();
    CustomListAdapter adapter;
    ArrayList<String> keyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_of);

        user = getIntent().getExtras().getString("userID");
        delete = getIntent().getExtras().getString("delete");
        mydata = FirebaseDatabase.getInstance();
        myref = mydata.getReference().child("menu").child(getIntent().getExtras().getString("type"));

        lv = (ListView) findViewById(R.id.menuListView);
        myref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item value = dataSnapshot.getValue(Item.class);
                list.add(value);
                keyList.add(dataSnapshot.getKey());
                adapter = new CustomListAdapter(menuOf.this, R.layout.main_layout, list);
                lv.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                menuOf.this.finish();
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
                Intent intent;
                if(delete.equals("false")) {
                    intent = new Intent(getApplication(), orderActivity.class);
                }else{
                    intent = new Intent(getApplication(), deleteActivity.class);
                }
                Item choice = list.get(position);
                intent.putExtra("userID", user);
                intent.putExtra("itemName", choice.getName());
                intent.putExtra("itemIMG", choice.getImageUrl());
                intent.putExtra("itemID", keyList.get(position));
                intent.putExtra("itemType", getIntent().getExtras().getString("type"));
                //Toast.makeText(getApplication(),choice.getTypeOf(),Toast.LENGTH_LONG).show();
                intent.putExtra("itemDesc", choice.getDescription());
                startActivity(intent);
            }
        });
    }
}
