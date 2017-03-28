package com.example.nautilius.memorycards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SuppJeu extends AppCompatActivity {


    InterProv ic;
    String discipline;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supp_jeu);
        ic = new InterProv(this);
        discipline=getIntent().getExtras().getString(InterProv.COLONNE_DISCIPLINE);
        ic.suppJeu(discipline);
        Toast.makeText(getApplicationContext(), "Suppression Reussie",
                Toast.LENGTH_SHORT).show();

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);

    }
}
