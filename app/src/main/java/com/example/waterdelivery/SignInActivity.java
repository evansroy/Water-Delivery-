package com.example.waterdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class SignInActivity extends AppCompatActivity {

    EditText etUserEmail,etUserPassword;
    TextView  txtForgotPassword,txtSignUpAccount;
    ProgressBar progressBar;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //Initialize the views
        etUserEmail = findViewById(R.id.etEmailSignIn);
        etUserPassword = findViewById(R.id.etPasswordSignIn);

        txtForgotPassword = findViewById(R.id.txtForgotPassword);
        txtSignUpAccount = findViewById(R.id.txtCreateAccountSignUp);

        progressBar = findViewById(R.id.progressSignIn);

        mAuth = FirebaseAuth.getInstance();


    }

    public  void logInButtonClicked(View view)
    {
        String txtEmail = etUserEmail.getText().toString().trim();
        String txtPassword = etUserPassword.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(txtEmail).matches())
        {
            etUserEmail.setError("Please Enter a Valid Email");
            etUserEmail.requestFocus();
        }
        if (txtPassword.length()<6)
        {
            etUserPassword.setError("Please Enter a Valid Password");
            etUserPassword.requestFocus();
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(txtEmail,txtPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInActivity.this, "User Signed Successfully", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignInActivity.this, "Failed to Sign In the User", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void forgotPasswordClicked(View view)
    {
        startActivity(new Intent(this,ForgotPasswordActivity.class));
    }

    public void createAccountClicked(View view)
    {
        startActivity(new Intent(SignInActivity.this,SignUpActivity.class));
    }
}