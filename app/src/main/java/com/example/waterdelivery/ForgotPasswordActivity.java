package com.example.waterdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgotPasswordActivity extends AppCompatActivity {

    EditText email;

    FirebaseAuth mAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.etEmailForgot);

        mAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressForgot);
    }
    public void resetPasswordButtonClicked(View view)
    {
        resetPassword();
    }
    public void backToSignInClicked(View view)
    {
        startActivity(new Intent(this,SignInActivity.class));
    }

    public  void resetPassword()
    {
        String txtEmailReset = email.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmailReset).matches())
        {
            email.setError("Please Enter a Valid Email");
            email.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.sendPasswordResetEmail(txtEmailReset).addOnCompleteListener(task -> {

            if (task.isSuccessful())
            {
                Toast.makeText(ForgotPasswordActivity.this, "Please Check your Email to Reset Password", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ForgotPasswordActivity.this,SignInActivity.class));
            }
            else
            {
                Toast.makeText(ForgotPasswordActivity.this, "Failed to Reset Password", Toast.LENGTH_SHORT).show();
            }
        });
    }

}