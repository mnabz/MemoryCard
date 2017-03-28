package com.example.nautilius.memorycards;

import android.app.ActionBar;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;


public class AfficherTable extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static String authority = "com.example.nautilius.contentprovider";
    ListView listV;
    SimpleCursorAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_table);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            listV = (ListView) findViewById(R.id.listV);
        adapter = new SimpleCursorAdapter(AfficherTable.this,
                android.R.layout.simple_list_item_1, null,
                new String[]{"discipline"},
                new int[]{android.R.id.text1}, 0);
        listV.setAdapter(adapter);
        final String caller= getIntent().getStringExtra("caller");
        Log.d("caller",caller);
        listV.setOnItemClickListener((new AdapterView.OnItemClickListener(){
            int check=0;
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Cursor item = (Cursor) parent.getItemAtPosition(position);
                    String theme = String.valueOf(item.getString(1));
                    Log.i("Selected item : ", theme);

                switch(caller){

                    case  "AjoutCartes" :
                    Intent i = new Intent(AfficherTable.this, AjoutCartes.class);
                    i.putExtra(InterProv.COLONNE_DISCIPLINE, theme);
                    startActivity(i);
                        break;

                    case "SuppJeu" :
                    Intent j = new Intent(AfficherTable.this, SuppJeu.class);
                    j.putExtra(InterProv.COLONNE_DISCIPLINE, theme);
                    startActivity(j);
                        break;
                    case "Apprendre" :
                    Intent k = new Intent(AfficherTable.this, ListCarte.class);
                    k.putExtra(InterProv.COLONNE_DISCIPLINE, theme);
                    startActivity(k);
                        break;
                    default:
                        Log.i("mauvais caller", caller);
                }

            }


            }));
        android.app.LoaderManager manager = getLoaderManager();
        getSupportLoaderManager().initLoader(0, null, this);



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

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri.Builder builder = new Uri.Builder();
        Uri uri = builder.scheme("content").authority(authority)
                .appendPath("jeux").build();
        Log.d("uri interprov tab ", uri.toString());
        return new CursorLoader(this, uri, new String[]{"_id", "discipline"},
                null, null, null);
    }




    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data); // les données prêtes, associer le Cursor
        // avec adapter pour faire afficher
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
