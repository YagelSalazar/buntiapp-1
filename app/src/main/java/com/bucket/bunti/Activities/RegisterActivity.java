package com.bucket.bunti.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bucket.bunti.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText txtUser, txtEmail, txtPassword, txtPasswordC;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUser      = findViewById(R.id.txtName);
        txtEmail     = findViewById(R.id.txtEmail);
        txtPassword  = findViewById(R.id.txtPassword);
        txtPasswordC = findViewById(R.id.txtPasswordConfirm);
        btnRegister  = findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}
