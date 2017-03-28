package com.example.nautilius.memorycards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AjouterJeu extends AppCompatActivity {
    Button valider;
    EditText edittext;
    String discipline;
    InterProv ic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_jeu);
        ic = new InterProv(this);
        valider = (Button) findViewById(R.id.button2);
        edittext= (EditText)findViewById(R.id.edit6);
        valider .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                discipline = edittext.getText().toString();

                ic.insertJeu(discipline);
                edittext.getText().clear();


            }
        });
    }
}
