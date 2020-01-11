package com.example.myrest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class AdminActivity extends AppCompatActivity {
    private Button addItem;
    private Button goToMenu;
    private Button deleteItem;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        user = getIntent().getExtras().getString("userID");

        deleteItem = (Button) findViewById(R.id.deleteItem);
        deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToDeleteItem();
            }
        });

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
                moveGoToMenu();
            }
        });
    }
    public void moveToAddItem(){
        Intent intent = new Intent(AdminActivity.this,AddItem.class);
        startActivity(intent);
    }
    public void moveGoToMenu(){
        Intent intent = new Intent(AdminActivity.this,UserActivity.class);
        intent.putExtra("userID",user);
        intent.putExtra("delete","false");
        startActivity(intent);
    }
    public void moveToDeleteItem(){
        Intent intent = new Intent(AdminActivity.this,UserActivity.class);
        intent.putExtra("userID",user);
        intent.putExtra("delete","true");
        startActivity(intent);
    }

}
