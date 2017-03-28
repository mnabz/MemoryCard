package com.example.nautilius.memorycards;

import android.database.Cursor;
import android.net.Uri;
import android.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ListCarte extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    private static String authority = "com.example.nautilius.contentprovider";
    private ListView listV;
    private String discipline;
    private CarteCursorAdapter adapter;
    private String themec,questionc,reponsec,datecarte;
    int difficultec,idc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     discipline= getIntent().getExtras().getString(InterProv.COLONNE_DISCIPLINE);
        setContentView(R.layout.activity_list_carte);
        listV=(ListView)findViewById(R.id.listview);
        adapter = new CarteCursorAdapter(this,null);

        listV.setAdapter(adapter);
        listV.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                Cursor cursor = (Cursor) parent.getItemAtPosition(position);
             /*   Date today = Calendar.getInstance().getTime();
                SimpleDateFormat fdate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
                String date = fdate.format(today);  */

                datecarte= cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DATE));
                themec=cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DISCIPLINE));
                questionc = cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_QUESTION));
                reponsec = cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_REPONSE));
                difficultec = cursor.getInt(cursor.getColumnIndex(InterProv.COLONNE_DIFFICULTE));
                idc=  cursor.getInt(cursor.getColumnIndex(InterProv.COLONNE_ID));
                //Log.i("Selected item listecarte: ", ""+ position +datecarte );



                FragmentTransaction ft = getFragmentManager().beginTransaction();
                QuestionDialogFragment qdf = QuestionDialogFragment.newInstance(themec,questionc,reponsec,idc,difficultec);
                qdf.show(ft,"qfrag");


            }


        });
        android.app.LoaderManager manager = getLoaderManager();
        getSupportLoaderManager().initLoader(0, null, this);



    }


    @Override
    /*public void onBackPressed() {
        QuestionDialogFragment myFrag = (QuestionDialogFragment) getFragmentManager().findFragmentByTag("qfrag");
        if(myFrag)
    }*/

    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Uri.Builder builder = new Uri.Builder();
        Uri uri = builder.scheme("content").authority(authority)
                .appendPath("cartesthemes").build();
        Calendar c = Calendar.getInstance();
       // c.add(Calendar.DATE,1);
        Date today = c.getTime();
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd");
        String date = fdate.format(today);
        return new CursorLoader(this, uri, new String[]{"_id","discipline","question","reponse","difficulte"},null,new String[]{discipline,date},null);
    }




    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data); // les données prêtes, associer le Cursor
        // avec adapter pour faire afficher
    }


    @Override
    public void onLoaderReset(Loader loader) {

    }
}
