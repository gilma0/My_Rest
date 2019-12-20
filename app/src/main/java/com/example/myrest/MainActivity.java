package com.example.myrest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText TextEmail;
    private EditText TextPassword;
    private Button registerBtn, loginBtn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference mDatabaseReference = mDatabase.getReference();
    private int userFlag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Rest");

        //auth
        firebaseAuth = FirebaseAuth.getInstance();


        //email and password
        TextEmail = (EditText) findViewById(R.id.TxtEmail);
        TextPassword = (EditText) findViewById(R.id.TxtPassword);

        registerBtn = (Button) findViewById(R.id.registerButton);
        loginBtn = (Button) findViewById(R.id.loginButton);

        progressDialog = new ProgressDialog(this);

        //listeners
        registerBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

    }



    private void registerUser() {


        //getting email and password
        final String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        //checking for fields
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }




        progressDialog.setMessage("Working...");
        progressDialog.show();

        //registering the user in firebase
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String userName = email.substring(0, pos);
                            String mail = userName + email.substring(pos,email.indexOf("."));
                            User temp = new User(userName);
                            mDatabaseReference = mDatabase.getReference().child("users").child(mail);
                            mDatabaseReference.setValue(temp);
                            Toast.makeText(MainActivity.this, "Registered email: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//checking for problems
                                Toast.makeText(MainActivity.this, "Email already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Something went wrong, try again", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    private void loginUser() {
        //getting email and password
        final String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        if (email.equals("admin") && password.equals("admin")){
            Intent intentAdmin = new Intent(getApplication(), AdminActivity.class);
            startActivity(intentAdmin);
            return;
        }

        //checking for fields
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Working...");
        progressDialog.show();

        //logging in
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            int pos = email.indexOf("@");
                            String user = email.substring(0, pos);
                            Toast.makeText(MainActivity.this, "Welcome: " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplication(), UserActivity.class);
                            intent.putExtra(UserActivity.user, user);
                            startActivity(intent);


                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//checking for collisions
                                Toast.makeText(MainActivity.this, "User Doesn't exist ", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MainActivity.this, "Something went wrong, check email and password ", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });


    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.registerButton:
                registerUser();
                break;
            case R.id.loginButton:
                loginUser();
                break;
        }


    }

}