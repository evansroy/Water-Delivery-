package com.example.waterdelivery;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.waterdelivery.Models.User;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignUpActivity extends AppCompatActivity {

    //Define Input Variables
    EditText userName,password,email,mobileNumber;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Initialize the Views
        userName = findViewById(R.id.etUserName);
        password = findViewById(R.id.etPassword);
        email = findViewById(R.id.etEmail);
        mobileNumber = findViewById(R.id.etMobileNumber);
        progressBar = findViewById(R.id.progress);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
    }

    //Function when Sign up button is clicked
    public  void signUpButtonClicked(View view)
    {
        //Get the Entered in the EditText

        String txtUserName = userName.getText().toString().trim();
        String txtPassword = password.getText().toString().trim();
        String txtEmail = email.getText().toString().trim();
        String txtMobileNo = mobileNumber.getText().toString().trim();

        //Check if the Edittext Contains values

        if (txtUserName.isEmpty())
        {
            userName.setError("Please Enter your UserName");
            userName.requestFocus();
        }

        if (txtPassword.isEmpty() || txtPassword.length() < 6)
        {
            password.setError("Please Enter your Password Containing At least 6 Character");
            password.requestFocus();
        }

        if (txtEmail.isEmpty())
        {
            email.setError("Please Enter a valid Email");
            email.requestFocus();
        }

        if(txtMobileNo.isEmpty())
        {
            mobileNumber.setError("Please Enter your Mobile Number");
            mobileNumber.requestFocus();
        }

        //Make The progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(txtEmail,txtPassword).addOnCompleteListener(task -> {

            if (task.isSuccessful()){

                User user = new User(txtUserName,txtPassword,txtMobileNo,txtEmail);

                FirebaseDatabase.getInstance().getReference("User")
                                .child(Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid())
                                        .setValue(user).addOnCompleteListener(task1 -> {

                                            if (task1.isSuccessful())
                                            {
                                                Toast.makeText(SignUpActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                            else
                                            {
                                                Toast.makeText(SignUpActivity.this, "User Failed to Register!", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        });

            }
            else {
                Toast.makeText(SignUpActivity.this, "User Failed to Registered!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public void signInClicked( View view)
    {
        startActivity(new Intent(this,SignInActivity.class));
    }
}