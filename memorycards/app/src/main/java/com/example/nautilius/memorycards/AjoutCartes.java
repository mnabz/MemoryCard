package com.example.nautilius.memorycards;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.example.nautilius.memorycards.R.id.edittext;

public class AjoutCartes extends AppCompatActivity {
    private static String authority= "com.example.nautilius.memorycards.content";
    Button a;
    EditText q,r,d;
    String discipline;
    InterProv ic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_ajout_cartes);
        a = (Button) findViewById(R.id.button6);
        q = (EditText)findViewById(R.id.editText);
        r= (EditText)findViewById(R.id.editText2);
        d = (EditText)findViewById(R.id.difficulte);
        discipline=getIntent().getExtras().getString(InterProv.COLONNE_DISCIPLINE);
        ic = new InterProv(this);

        a.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String question = q.getText().toString();
                String reponse = r.getText().toString();
                int diff = Integer.parseInt(d.getText().toString());


                ic.insertCarte(discipline,question,reponse,diff);

                q.getText().clear();
                r.getText().clear();


            }
        });

    }



}
