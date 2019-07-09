package com.bucket.bunti.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bucket.bunti.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtUser, txtEmail, txtPassword, txtPasswordC;
    private Button btnRegister;

    //FIREBASE
    private FirebaseAuth oAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUser      = findViewById(R.id.txtName);
        txtEmail     = findViewById(R.id.txtEmail);
        txtPassword  = findViewById(R.id.txtPassword);
        txtPasswordC = findViewById(R.id.txtPasswordConfirm);
        btnRegister  = findViewById(R.id.btnRegister);
        oAuth        = FirebaseAuth.getInstance();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String user  = txtUser.getText().toString();
                final String email = txtEmail.getText().toString();
                final String password = txtPassword.getText().toString();
                final String passwordC = txtPasswordC.getText().toString();

                btnRegister.setVisibility(View.INVISIBLE);

                if(user.isEmpty() || email.isEmpty() || password.isEmpty() || passwordC.isEmpty()){
                    // Wrong - fields are empty
                    showMessage("Por favor completa todos los campos");
                    btnRegister.setVisibility(View.VISIBLE);
                } else {
                    // fields ok
                    if(password.equals(passwordC)){
                        // Create account
                        CreateUserAccount(user,email,password);
                    } else {
                        // Wrong - passwords not same
                        showMessage("Las contraseñas no coinciden");
                        btnRegister.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
    }

    private void CreateUserAccount(final String user, String email, String password) {
        //create new account
        oAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            showMessage("Cuenta creada...");
                            updateUserInfo(user, oAuth.getCurrentUser());
                        } else {
                            showMessage("Ocurrio un error, intenta más tarde");
                            btnRegister.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    private void updateUserInfo(final String user, FirebaseUser currentUser){
        // Update username
        UserProfileChangeRequest profileUpdate = new UserProfileChangeRequest.Builder()
                .setDisplayName(user)
                .build();

        currentUser.updateProfile(profileUpdate)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            showMessage("¡Registro exitoso!");
                            updateUI();
                        }
                    }
                });
    }

    private void updateUI(){
        Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeActivity);
        finish();
    }


    private void showMessage(String message){
        // Method for toast messages
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
