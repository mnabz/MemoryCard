package com.example.nautilius.memorycards;

import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ParamActivity extends AppCompatActivity {

    public static final String PREFS_RAPPEL = "RappelParam";
    public static final String PREFS_TIMER= "TimerParam";

    int difficulte;
    EditText rappeljour,tempstimer;
    Button valider;
    long countdown;
    int datecarterevisions;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_param);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        tempstimer= (EditText)findViewById(R.id.valeurtimerpram) ;
        rappeljour= (EditText)findViewById(R.id.rappelparam) ;
        valider = (Button)findViewById(R.id.bouttonparam);
        rappeljour = (EditText)findViewById(R.id.rappelparam);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ParamActivity.this);
                SharedPreferences.Editor editPrefs = prefs.edit();
                editPrefs.putInt(PREFS_TIMER,Integer.parseInt(tempstimer.getText().toString()));
                editPrefs.putInt(PREFS_RAPPEL,Integer.parseInt(rappeljour.getText().toString()));
                editPrefs.commit();

                Toast.makeText(getApplicationContext(), "parametre enregistré", Toast.LENGTH_SHORT).show();


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
               /* Intent k = new Intent(this,ParamActivity.class);
                startActivity(k); */
                //
                return true;

            default:
        }
        return super.onOptionsItemSelected(item);

    }
}
