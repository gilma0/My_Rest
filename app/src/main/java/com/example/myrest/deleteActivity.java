package com.example.myrest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class deleteActivity extends AppCompatActivity {

    static String user;
    static String itemName;
    static String itemID;
    static String itemIMG;
    static String itemDesc;
    static String itemType;
    ArrayList<Item> list = new ArrayList<>();
    private ListView lv;
    private Item item;
    private Button orderButton;
    CustomListAdapter adapter;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        user = getIntent().getExtras().getString("userID");
        itemName = getIntent().getExtras().getString("itemName");
        itemID = getIntent().getExtras().getString("itemID");
        itemDesc = getIntent().getExtras().getString("itemDesc");
        itemType = getIntent().getExtras().getString("itemType");
        itemIMG = getIntent().getExtras().getString("itemIMG");
        lv = (ListView) findViewById(R.id.deleteList);
        orderButton = (Button) findViewById(R.id.confirmDel);
        //Toast.makeText(getApplication(),itemType,Toast.LENGTH_LONG).show();

        item = new Item (itemType, itemName, itemDesc, itemIMG);
        list.add(item);
        adapter = new CustomListAdapter(deleteActivity.this, R.layout.main_layout, list);
        lv.setAdapter(adapter);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(deleteActivity.this).create();
                alertDialog.setTitle("Are you sure?");
                alertDialog.setMessage("The item will be deleted");
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mDatabase.getReference().child("menu").child(itemType).child(itemID).removeValue();
                                deleteActivity.this.finish();
                            }
                        });
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
                //mDatabase.getReference().child("menu").child(itemType).child(itemID).removeValue();
               // mDatabaseReference.removeValue();
            }
        });
    }
}
