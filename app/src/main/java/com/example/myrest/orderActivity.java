package com.example.myrest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
    private ListView lv;
    private Item item;
    private Button orderButton;
    CustomListAdapter adapter;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();
    private order order;

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
                order = new order(user.replace(".",","),itemID, itemName);
                mDatabaseReference = mDatabase.getReference().child("orders").push();
                mDatabaseReference.setValue(order);
                addNotification();
                orderActivity.this.finish();
            }
        });



    }

    public void addNotification(){
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("CHANNEL_ID",
                    "CHANNEL_NAME",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("NOTIFICATION_CHANNEL_DISCRIPTION");
            mNotificationManager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "CHANNEL_ID")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Ordered: " + itemName)
                .setContentText("if there are any questions or problems be sure to contact us")
                .setAutoCancel(true);
        Intent intent = new Intent(getApplicationContext(), orderActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());

    }
}
