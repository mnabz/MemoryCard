package com.example.nautilius.memorycards;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{

    Button b3, b4, b5;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        b3 = (Button) findViewById(R.id.button3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, GestionCarte.class);
                startActivity(i);
            }
        });

        b4 = (Button) findViewById(R.id.button4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(MainActivity.this, AfficherTable.class);
                i.putExtra("caller", "Apprendre");
                startActivity(i);
            }
        });

        b5 = (Button) findViewById(R.id.button5);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(MainActivity.this, ParamActivity.class);
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
            Intent i = new Intent(this,GestionCarte.class);
            startActivity(i);
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


}


