package com.bucket.bunti.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bucket.bunti.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {

    private TextView btnLogout;
    //FIREBASE
    private FirebaseAuth oAuth;
    private FirebaseUser userCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btnLogout = findViewById(R.id.btnLogout);

        oAuth = FirebaseAuth.getInstance();
        userCurrent = oAuth.getCurrentUser();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
                finish();
            }
        });

        Button btnlocalization = (Button) findViewById(R.id.buttonSearch);
        btnlocalization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentlocalization = new Intent(view.getContext(), LocalizationActivity.class);
                startActivityForResult(intentlocalization,0);
            }
        });

        Button btnUserFound = (Button) findViewById(R.id.buttonCall);
        btnUserFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentUserFound = new Intent(view.getContext(),UserFound.class);
                startActivityForResult(intentUserFound,0);
            }
        });
    }
}
