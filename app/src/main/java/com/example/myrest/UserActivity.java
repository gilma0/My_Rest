package com.example.myrest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    static String user;
    ListView typeList;
    private FirebaseDatabase mydata;
    private String delete;
    DatabaseReference myref;
    ArrayList<Item> list = new ArrayList<>();
    CustomListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        user = getIntent().getExtras().getString("userID");
        delete = getIntent().getExtras().getString("delete");
        typeList=(ListView)findViewById(R.id.typeList);
        mydata = FirebaseDatabase.getInstance();
        myref = mydata.getReference().child("typeIcons");
        Query query = myref.orderByChild("num");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Item value = dataSnapshot.getValue(Item.class);
                list.add(value);
                adapter = new CustomListAdapter(UserActivity.this, R.layout.main_layout, list);
                typeList.setAdapter(adapter);
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

        typeList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    Intent intent = new Intent(UserActivity.this,menuOf.class);
                    intent.putExtra("type","Starters");
                    intent.putExtra("userID", user);
                    intent.putExtra("delete",delete);
                    //intent.putExtra(startersMenu.user, user); will send user to next activity
                    startActivity(intent);
                }
                if(position == 1){
                    Intent intent = new Intent(UserActivity.this,menuOf.class);
                    intent.putExtra("type","Main");
                    intent.putExtra("userID", user);
                    intent.putExtra("delete",delete);
                    //intent.putExtra(mainMenu.user, user); will send user to next activity
                    startActivity(intent);
                }
                if(position == 2){
                    Intent intent = new Intent(UserActivity.this,menuOf.class);
                    intent.putExtra("type","Deserts");
                    intent.putExtra("userID", user);
                    intent.putExtra("delete",delete);
                    //intent.putExtra(desertsMenu.user, user); will send user to next activity
                    startActivity(intent);
                }
                if(position == 3){
                    Intent intent = new Intent(UserActivity.this,menuOf.class);
                    intent.putExtra("type","Drinks");
                    intent.putExtra("userID", user);
                    intent.putExtra("delete",delete);
                    //intent.putExtra(drinksMenu.user, user); will send user to next activity
                    startActivity(intent);
                }
                if(position == 4){
                    Intent intent = new Intent(UserActivity.this,menuOf.class);
                    intent.putExtra("type","Alcohol");
                    intent.putExtra("userID", user);
                    intent.putExtra("delete",delete);
                    //intent.putExtra(alcoholMenu.user, user); will send user to next activity
                    startActivity(intent);
                }
            }
        });
    }
}
