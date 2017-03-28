package com.example.nautilius.memorycards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Timer;

/**
 * Created by nautilius on 30/12/2016.
 */

public class CarteAdapter extends BaseAdapter {
    private ArrayList<Carte> cartes;
    private Context context;
    private LayoutInflater minflater;
    TextView theme,question,idcarte;
    Spinner diffSpinner;
    Timer tempsset ;
    Button valider,editerrep;

    public CarteAdapter(Context c, ArrayList<Carte> cartes1){
        this.context= c;
        this.cartes= cartes1;
        minflater = LayoutInflater.from(c);
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
         return cartes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layoutitem;

        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "personne_layout.xml"
            layoutitem = (LinearLayout) minflater.inflate(R.layout.carte_layout, parent, false);
        } else {
            layoutitem = (LinearLayout) convertView;
        }



        //(2) : Récupération des TextView de notre layout
        theme = (TextView)layoutitem.findViewById(R.id.theme);
        question= (TextView)layoutitem.findViewById(R.id.textviewquest);
        diffSpinner= (Spinner) layoutitem.findViewById(R.id.spinnerddiff);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.difficulte, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        diffSpinner.setAdapter(adapter);
        valider = (Button) layoutitem.findViewById(R.id.valider);
        editerrep = (Button) layoutitem.findViewById(R.id.editrep);


        //(3) : Renseignement des valeurs
        theme.setText(cartes.get(position).getDiscipline());
        question.setText(cartes.get(position).getQuestion());



        return layoutitem;
    }
}
