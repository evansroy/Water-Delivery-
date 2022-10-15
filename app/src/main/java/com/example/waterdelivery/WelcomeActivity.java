package com.example.waterdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);




    }
    public void onButtonSignUpClicked(View view)
    {
        startActivity(new Intent(WelcomeActivity.this,SignUpActivity.class));
    }

    public void onButtonSignInClicked(View view)
    {
        startActivity(new Intent(WelcomeActivity.this,SignInActivity.class));
    }
}