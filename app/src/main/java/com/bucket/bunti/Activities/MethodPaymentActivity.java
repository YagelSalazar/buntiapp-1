package com.bucket.bunti.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bucket.bunti.Helpers.SharedPreferencesProject;
import com.bucket.bunti.R;

import static java.security.AccessController.getContext;

public class MethodPaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_payment);
        Button btnCash = (Button) findViewById(R.id.buttonCash);
        Button btnCreditCard = (Button) findViewById(R.id.buttonCreditCard);
        Button btnPaypal = (Button) findViewById(R.id.buttonPayPal);

        //retrieve data
        String id_document = SharedPreferencesProject.retriveData(getApplicationContext(),"id_document");
        Toast.makeText(this, id_document, Toast.LENGTH_SHORT).show();

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI();
            }
        });

        btnCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI();
            }
        });

        btnPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI();
            }
        });
    }

    private void updateUI(){
        Intent intentSatisfactoryPayment = new Intent(getApplicationContext(), SatisfactoryPaymentActivity.class);
        startActivityForResult(intentSatisfactoryPayment,0);
        finish();
    }
}
