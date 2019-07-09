package com.bucket.bunti.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bucket.bunti.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private Button btnLogin, btnRegister;
    private ProgressBar progressBar;
    private Intent homeActivity, registerActivity;


    //FIREBASE
    private FirebaseAuth oAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail    = findViewById(R.id.loginEmail);
        loginPassword = findViewById(R.id.loginPassword);
        progressBar   = findViewById(R.id.pbLogin);
        btnLogin      = findViewById(R.id.btnLogin);
        btnRegister   = findViewById(R.id.btnCreateAccount);
        oAuth         = FirebaseAuth.getInstance();
        homeActivity  = new Intent(this,HomeActivity.class);
        registerActivity  = new Intent(this,RegisterActivity.class);

        progressBar.setVisibility(View.INVISIBLE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableComponents();

                final String email    = loginEmail.getText().toString();
                final String password = loginPassword.getText().toString();

                if(email.isEmpty() || password.isEmpty()){
                    showMessage("Completa todos los campos");
                    enableComponents();
                } else {
                    signIn(email,password);
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI("register");
            }
        });

    }

    private void signIn(String email, String password) {
        oAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    enableComponents();
                    updateUI("home");
                } else {
                    enableComponents();
                    showMessage(task.getException().getMessage());
                }
            }
        });
    }

    private void updateUI(String activity){
        switch (activity){
            case "home":
                startActivity(homeActivity);
                break;

            case "register":
                startActivity(registerActivity);
                break;
        }
        finish();
    }

    private void showMessage(String message){
        // Method for toast messages
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void disableComponents(){
        loginEmail.setEnabled(false);
        loginPassword.setEnabled(false);
        btnRegister.setEnabled(false);
        btnLogin.setEnabled(false);
        btnLogin.setText("Iniciando sesión...");
        btnLogin.setTextColor(getApplication().getResources().getColor(R.color.colorPrimaryDark));
    }

    private void enableComponents(){
        loginEmail.setEnabled(true);
        loginPassword.setEnabled(true);
        btnRegister.setEnabled(true);
        btnLogin.setEnabled(true);
        btnLogin.setText(getResources().getString(R.string.iniciar_sesi_n));
        btnLogin.setTextColor(Color.parseColor("#FFFFFF"));
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = oAuth.getCurrentUser();
        if(user != null){
            // user already connected
            updateUI("home");
        }
    }

}
