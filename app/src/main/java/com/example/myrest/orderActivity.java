package com.example.myrest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class orderActivity extends AppCompatActivity {
    static String user;
    static String itemName;
    static String itemID;
    static String itemIMG;
    static String itemDesc;
    static String itemType;
    ArrayList<Item> list = new ArrayList<>();
    //private ImageView img;
    //private TextView desc;
    //private TextView name;
    private ListView lv;
    private Item item;
    private Button orderButton;
    CustomListAdapter adapter;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        user = getIntent().getExtras().getString("userID");
        itemName = getIntent().getExtras().getString("itemName");
        itemID = getIntent().getExtras().getString("itemID");
        itemDesc = getIntent().getExtras().getString("itemDesc");
        itemType = getIntent().getExtras().getString("itemType");
        itemIMG = getIntent().getExtras().getString("itemIMG");
        lv = (ListView) findViewById(R.id.orderList);
        orderButton = (Button) findViewById(R.id.confirm);

        item = new Item (itemType, itemName, itemDesc, itemIMG);
        list.add(item);
        adapter = new CustomListAdapter(orderActivity.this, R.layout.main_layout, list);
        lv.setAdapter(adapter);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order order = new order(user.replace(".",","),itemID);
                mDatabaseReference = mDatabase.getReference().child("orders").push();
                mDatabaseReference.setValue(order);
            }
        });

    }
}
