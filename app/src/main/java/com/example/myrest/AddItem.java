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
    private EditText editName, imageUrl, editDescription;
    private Button add;
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
        editDescription = (EditText) findViewById(R.id.editDescription);
        add = (Button) findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add();
            }
        });
    }

    public void Add(){
        name = editName.getText().toString().trim();
        description = editDescription.getText().toString().trim();
        url = imageUrl.getText().toString().trim();
        Item item = new Item(type,name,description,url);
        mDatabaseReference = mDatabase.getReference().child("menu").child(type).child(name);
        mDatabaseReference.setValue(item);
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