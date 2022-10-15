package com.example.waterdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    //Define Input Variables
    EditText userName,password,email,mobileNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //intialize the Views
        userName = findViewById(R.id.etUserName);
        password = findViewById(R.id.etPassword);
        email = findViewById(R.id.etEmail);
        mobileNumber = findViewById(R.id.etMobileNumber);
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
            password.setError("Please Enter your Password");
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
    }
}