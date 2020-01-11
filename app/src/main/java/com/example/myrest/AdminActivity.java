package com.example.myrest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    private Button addItem;
    private Button goToMenu;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        user = getIntent().getExtras().getString("userID");

        addItem = (Button) findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAddItem();
            }
        });
        goToMenu = (Button) findViewById(R.id.goToMenu);
        goToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movegoToMenu();
            }
        });
    }
    public void moveToAddItem(){
        Intent intent = new Intent(AdminActivity.this,AddItem.class);
        startActivity(intent);
    }
    public void movegoToMenu(){
        Intent intent = new Intent(AdminActivity.this,UserActivity.class);
        intent.putExtra("userID",user);
        startActivity(intent);
    }

}
