package com.example.nautilius.memorycards;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nautilius on 01/01/2017.
 */

public class ResponseDialogFragment extends DialogFragment implements View.OnClickListener {



    private int id,difficulte;
    private String question,reponse,discipline,Duration;
    private TextView difficulteview,idcartev,themecartev,reponsev,timerc;

    private Button boite1,boite2,boite3;
    private Handler handler = new Handler();

    public static ResponseDialogFragment newInstance(String theme,String q,String rep,int id,int diff) {
       ResponseDialogFragment ldf = new ResponseDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(InterProv.COLONNE_DISCIPLINE,theme);
        bundle.putString(InterProv.COLONNE_QUESTION,q);
        bundle.putString(InterProv.COLONNE_REPONSE,rep);
        bundle.putInt(InterProv.COLONNE_ID,id);
        bundle.putInt(InterProv.COLONNE_DIFFICULTE,diff);
        ldf.setArguments(bundle);

        return ldf;
    }


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        this.setCancelable(true);
        int style = DialogFragment.STYLE_NORMAL, theme = 2;
        setStyle(style, theme);

        discipline= getArguments().getString(InterProv.COLONNE_DISCIPLINE);
        reponse=getArguments().getString(InterProv.COLONNE_REPONSE);
        question = getArguments().getString(InterProv.COLONNE_QUESTION);
        difficulte = getArguments().getInt(InterProv.COLONNE_DIFFICULTE);
        id = getArguments().getInt(InterProv.COLONNE_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lien_response, container, false);
        idcartev = (TextView) v.findViewById(R.id.idcarterep);
        difficulteview = (TextView) v.findViewById(R.id.diffcarterep);
       reponsev =  (TextView) v.findViewById(R.id.textviewrep);


        reponsev.setText(reponse);
        difficulteview.setText(String.valueOf(difficulte));
        idcartev.setText(String.valueOf(id));

        boite1 = (Button) v.findViewById(
                R.id.boite1);
        boite1.setOnClickListener(this);

        boite2 = (Button) v.findViewById(
                R.id.boite2);
        boite2.setOnClickListener(this);
        boite3 = (Button) v.findViewById(
                R.id.boite3);
        boite3.setOnClickListener(this);
        return v;
    }

    public void onClick(View v) {
        if(v==boite1){
            Intent i = new Intent(getActivity(), UpdateService.class);
            i.putExtra(InterProv.COLONNE_DISCIPLINE,discipline);
            i.putExtra(InterProv.COLONNE_QUESTION,question);
            i.putExtra(InterProv.COLONNE_REPONSE,reponse);
            i.putExtra(InterProv.COLONNE_ID,id);
            i.putExtra(InterProv.COLONNE_DIFFICULTE,1);
            getActivity().startService(i);
            dismiss();

        }else if (v==boite2){
            Intent i = new Intent(getActivity(), UpdateService.class);
            i.putExtra(InterProv.COLONNE_DISCIPLINE,discipline);
            i.putExtra(InterProv.COLONNE_QUESTION,question);
            i.putExtra(InterProv.COLONNE_REPONSE,reponse);
            i.putExtra(InterProv.COLONNE_ID,id);
            i.putExtra(InterProv.COLONNE_DIFFICULTE,2);
            getActivity().startService(i);

            dismiss();

        }else if (v==boite3){
            Intent i = new Intent(getActivity(), UpdateService.class);
            i.putExtra(InterProv.COLONNE_DISCIPLINE,discipline);
            i.putExtra(InterProv.COLONNE_QUESTION,question);
            i.putExtra(InterProv.COLONNE_REPONSE,reponse);
            i.putExtra(InterProv.COLONNE_ID,id);
            i.putExtra(InterProv.COLONNE_DIFFICULTE,3);
            getActivity().startService(i);

            dismiss();

        }


        // telechargement DownloadMAnager

    }


}