package com.bucket.bunti.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bucket.bunti.Helpers.SharedPreferencesProject;
import com.bucket.bunti.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static java.security.AccessController.getContext;

public class MethodPaymentActivity extends AppCompatActivity {

    //FIREBASE
    private FirebaseAuth oAuth;
    private FirebaseUser user;
    private FirebaseFirestore dataBase;
    private CollectionReference usuariosRef;
    private Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_method_payment);
        Button btnCash = (Button) findViewById(R.id.buttonCash);
        Button btnCreditCard = (Button) findViewById(R.id.buttonCreditCard);
        Button btnPaypal = (Button) findViewById(R.id.buttonPayPal);

        oAuth       = FirebaseAuth.getInstance();
        user = oAuth.getCurrentUser();
        dataBase    = FirebaseFirestore.getInstance();
        usuariosRef = dataBase.collection("usuarios");

        //Toast.makeText(this, id_document, Toast.LENGTH_SHORT).show();

        btnCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveService("efectivo");
            }
        });

        btnCreditCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveService("tarjeta de credito");
            }
        });

        btnPaypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveService("paypal");
            }
        });
    }

    private void saveService(String metodo_pago){
        // Create a new user
        Map<String, Object> service = new HashMap<>();
        service.put("usuario", user.getDisplayName());
        service.put("pago",metodo_pago);
        service.put("direccion", "actopan hgo");
        service.put("fecha", getDate());
        service.put("hora", getHour());

        // Add a new document with a generated ID
        //retrieve data
        String id_document = SharedPreferencesProject.retriveData(getApplicationContext(),"id_document");
        usuariosRef.document(id_document).collection("services").add(service)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        updateUI();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("Ocurrio un error, intenta mas tarde.");
                    }
                });
    }

    private void updateUI(){
        Intent intentSatisfactoryPayment = new Intent(getApplicationContext(), SatisfactoryPaymentActivity.class);
        startActivityForResult(intentSatisfactoryPayment,0);
        finish();
    }

    private String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date)+"";
    }

    private String getHour(){
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        return hourFormat.format(date)+"";
    }

    private void showMessage(String message){
        // Method for toast messages
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
