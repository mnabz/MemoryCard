package com.example.nautilius.memorycards;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;

public class GestionCarte extends AppCompatActivity{
    Button create, Ajout, supp,manuel;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gestion_carte);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Ajout = (Button) findViewById(R.id.ajoutcarte);
        Ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),AfficherTable.class);
                i.putExtra("caller", "AjoutCartes");
                startActivity(i);
            }
        });



        create= (Button) findViewById(R.id.creerjeu);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           /*    FragmentTransaction ft =
                        getFragmentManager().beginTransaction();
                LienDialogFragment ldf =
                        LienDialogFragment.newInstance(
                                "Enter Something");
                ldf.show(ft,"LINK ENTER"); */
                Intent i = new Intent(GestionCarte.this, LienActivity.class);
                startActivity(i);

            }
        });


        supp= (Button) findViewById(R.id.suppjeu);
        supp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AfficherTable.class);
                i.putExtra("caller", "SuppJeu");
                startActivity(i);
            }
        });

        manuel= (Button) findViewById(R.id.button);
        manuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AjouterJeu.class);
                startActivity(i);
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.menu_main, m);
        return super.onCreateOptionsMenu(m);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.gestioncarte:
               /* Intent i = new Intent(this,GestionCarte.class);
                startActivity(i); */
                return true;
            case R.id.apprendre:
                Intent j= new Intent(this,AfficherTable.class);
                j.putExtra("caller", "Apprendre");
                startActivity(j);
                return true;
            case R.id.Parametres:
                Intent k = new Intent(this,ParamActivity.class);
                startActivity(k);
                //
                return true;

            default:
        }
        return super.onOptionsItemSelected(item);

    }
    public void creerjeu() {

        //  Intent i = new Intent(this,.class);
        //  startActivity(i);
    }

    public void suppjeu() {

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */




}
