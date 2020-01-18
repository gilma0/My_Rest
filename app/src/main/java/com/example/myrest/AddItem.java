package com.example.myrest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String type,name, description, url;
    private EditText editName, imageUrl, editDescription, editPrice;
    private Button add;
    private Item item;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        firebaseAuth = FirebaseAuth.getInstance();
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        editName = (EditText) findViewById(R.id.editName);
        imageUrl = (EditText) findViewById(R.id.imageUrl);
        editPrice = (EditText) findViewById(R.id.editPrice);
        editDescription = (EditText) findViewById(R.id.editDescription);
        add = (Button) findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean boxes = Add();
                if(boxes == true) {
                    AddItem.this.finish();
                }
            }
        });
    }

    public boolean Add(){
        if(editPrice.getText().toString().trim().equals("") ||  editName.getText().toString().trim().equals("") || editDescription.getText().toString().trim().equals("") || imageUrl.getText().toString().trim().equals("")){
            Toast.makeText(this,"please fill all options", Toast.LENGTH_LONG).show();
            return false;
        }
        name = editName.getText().toString().trim();
        description = editDescription.getText().toString().trim() + "\nPrice: " + editPrice.getText().toString().trim();
        url = imageUrl.getText().toString().trim();
        Item item = new Item(type,name,description,url);
        mDatabaseReference = mDatabase.getReference().child("menu").child(type).push();
        mDatabaseReference.setValue(item);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = parent.getItemAtPosition(position).toString();
        Toast.makeText(this,"Type: " + type, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
