package com.example.nautilius.memorycards;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by nautilius on 02/01/2017.
 */

public class CarteCursorAdapter2 extends CursorAdapter {
    private int mSelectedPosition;
    private String themec, questionc, reponsec, datec;
    int idc, difficultec;
    boolean classe = false;

    TextView theme, idcarte, diffcarfte;
    Button valider;


    public CarteCursorAdapter2(Context context, Cursor c) {
        super(context, c, 0);
        // mInflater = LayoutInflater.from(context);
    }

    public void setSelectedPosition(int position) {

        mSelectedPosition = position;
        // something has changed.
        notifyDataSetChanged();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.carte_layout, parent, false);


        // edit: no need to call bindView here. That's done automatically

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        theme = (TextView) view.findViewById(R.id.theme2);
        diffcarfte = (TextView) view.findViewById(R.id.diffcarte2);

        idcarte = (TextView) view.findViewById(R.id.idcarte2);

        valider = (Button) view.findViewById(R.id.valider2);

        theme.setText(cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DISCIPLINE)));
        idcarte.setText(cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_ID)));
        diffcarfte.setText(cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DIFFICULTE)));

        /*themec = cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DISCIPLINE));
        difficultec = cursor.getInt(cursor.getColumnIndex(InterProv.COLONNE_DIFFICULTE));
        idc = cursor.getInt(cursor.getColumnIndex(InterProv.COLONNE_ID));*/
        questionc = cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_QUESTION));
        reponsec = cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_REPONSE));
//      datec = cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DATE));


    }
}

   /* public View newView(Context context, Cursor cursor, ViewGroup parent) {

        View v = LayoutInflater.from(context).inflate(R.layout.carte_layout2, null);
        theme = (TextView) v.findViewById(R.id.theme2);
        diffcarfte = (TextView) v.findViewById(R.id.diffcarte2);

        idcarte = (TextView) v.findViewById(R.id.idcarte2);

        valider = (Button) v.findViewById(R.id.valider2);

        return v;

        // edit: no need to call bindView here. That's done automatically

    } */



    /*public void bindView2(View view, final Context context, Cursor cursor) {


        themec= cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DISCIPLINE));
        theme.setText(themec);

        difficultec = cursor.getInt(cursor.getColumnIndex(InterProv.COLONNE_DIFFICULTE));
           diffcarfte.setText(difficultec);

        idc = cursor.getInt(cursor.getColumnIndex(InterProv.COLONNE_ID));
        idcarte.setText(idc);

        questionc= cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_QUESTION));
        reponsec =cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_REPONSE));
        datec=cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DATE));






        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(context, "bouton valider", Toast.LENGTH_LONG);
                toast.show();
            }
        });







    }













      /*  //(2) : Récupération des TextView de notre layout
        theme = (TextView) view.findViewById(R.id.theme);
        question = (TextView) view.findViewById(R.id.textviewquest);
        diffSpinner = (Spinner) view.findViewById(R.id.spinnerddiff);
        // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.difficulte, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        diffSpinner.setAdapter(adapter);
        diffcarfte = (TextView) view.findViewById(R.id.diffcarte);
        idcarte = (TextView) view.findViewById(R.id.idcarte);
        valider = (Button) view.findViewById(R.id.valider);
        editerrep = (Button) view.findViewById(R.id.editrep);
        tempsset=(TextView) view.findViewById(R.id.chronometer4);
        tempsset.setText(Duration);

        theme.setText(cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DISCIPLINE)));
        question.setText(cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_QUESTION)));
        idcarte.setText(cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_ID)));
        diffcarfte.setText(cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DIFFICULTE)));




        setdiff= diffSpinner.getSelectedItem().toString();   */
    //(3) : Renseignement des valeurs








