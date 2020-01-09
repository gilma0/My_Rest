package com.example.myrest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class orderActivity extends AppCompatActivity {
    static String user;
    static String itemName;
    static String itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        user = getIntent().getExtras().getString("userID");
        itemName = getIntent().getExtras().getString("itemName");
        itemID = getIntent().getExtras().getString("itemID");
    }
}
