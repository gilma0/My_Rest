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
import com.google.firebase.database.Query;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    static String user;
    ListView typeList;
    private FirebaseDatabase mydata;
    DatabaseReference myref;
    ArrayList<Item> list = new ArrayList<>();
    //ArrayList<String> descList = new ArrayList<>();
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user = getIntent().getExtras().getString("userID");
        typeList=(ListView)findViewById(R.id.typeList);
        mydata = FirebaseDatabase.getInstance();
        myref = mydata.getReference().child("typeIcons");
        Query query = myref.orderByChild("num");
        /*ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Starters");
        arrayList.add("Main");
        arrayList.add("Deserts");
        arrayList.add("Drinks");
        arrayList.add("Alcohol");*/
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item value = dataSnapshot.getValue(Item.class);
                list.add(value);
                // descList.add(value.getDescription());
                //arrayList.add(value.getName());
                adapter = new CustomListAdapter(UserActivity.this, R.layout.main_layout, list);
                typeList.setAdapter(adapter);
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

        //ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
       // typeList.setAdapter(arrayAdapter);
        typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(UserActivity.this,startersMenu.class);
                    //intent.putExtra(startersMenu.user, user); will send user to next activity
                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(UserActivity.this,mainMenu.class);
                    //intent.putExtra(mainMenu.user, user); will send user to next activity
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(UserActivity.this,desertsMenu.class);
                    //intent.putExtra(desertsMenu.user, user); will send user to next activity
                    startActivity(intent);
                }
                if(position == 3){
                    Intent intent = new Intent(UserActivity.this,drinksMenu.class);
                    intent.putExtra("userID", user);
                    //intent.putExtra(drinksMenu.user, user); will send user to next activity
                    startActivity(intent);
                }
                if(position == 4){
                    Intent intent = new Intent(UserActivity.this,alcoholMenu.class);
                    //intent.putExtra(alcoholMenu.user, user); will send user to next activity
                    startActivity(intent);
                }
            }
        });
    }
}
