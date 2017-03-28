package com.example.nautilius.memorycards;

import android.content.ClipData;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

/**
 * Created by nautilius on 31/12/2016.
 */

public class CarteCursorAdapter extends CursorAdapter {
    private int mSelectedPosition;
    private String Duration;

    public static final long countdown= (30*1000);
    private boolean flag;
    // private Context context;

    private static class ViewHolder {
        TextView theme, question, idcarte, diffcarfte, tempsset;
        Spinner diffSpinner;

        TextView scheduleDuration;
        Button valider;
        String themec, questionc, reponsec, datec;
        int idc, difficultec;
        boolean classee= false;

    }

    public CarteCursorAdapter(Context context, Cursor c) {
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
        View v = LayoutInflater.from(context).inflate(R.layout.carte_layout, null);
        ViewHolder holder = new ViewHolder();
        holder.theme = (TextView) v.findViewById(R.id.theme2);
        holder.diffcarfte = (TextView) v.findViewById(R.id.diffcarte2);

        holder.idcarte = (TextView) v.findViewById(R.id.idcarte2);
        holder.valider = (Button) v.findViewById(R.id.valider2);

// Specify the layout to use when the list of choices appears

        v.setTag(holder);



        return v;

        // edit: no need to call bindView here. That's done automatically

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();


        holder.theme.setText(cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DISCIPLINE)));

        holder.diffcarfte.setText(cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DIFFICULTE)));
        holder.idcarte.setText(cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_ID)));


        holder.themec=cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_DISCIPLINE));
        holder.questionc = cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_QUESTION));
        holder.reponsec = cursor.getString(cursor.getColumnIndex(InterProv.COLONNE_REPONSE));
        holder.difficultec = cursor.getInt(cursor.getColumnIndex(InterProv.COLONNE_DIFFICULTE));
        holder.idc=  cursor.getInt(cursor.getColumnIndex(InterProv.COLONNE_ID));








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


}
