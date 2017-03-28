package com.example.nautilius.memorycards;

import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class QuestionDialogFragment extends DialogFragment implements View.OnClickListener{


    private int id,difficulte;
    private String question,reponse,discipline,Duration;
    private TextView difficulteview,idcartev,themecartev,questionv,timerc;
    private Button validercartev,editercartev;
    private Handler handler = new Handler();
    private CountDownTimer countDownTimer;
    private static final String KEY_TEXT_VALUE = "textValue";
    private long countdown; //= (30 *1000);

    public static QuestionDialogFragment newInstance(String theme,String q,String rep,int id,int diff) {
        QuestionDialogFragment ldf = new QuestionDialogFragment();
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
        setRetainInstance(true);
        this.setCancelable(true);
        int style = android.app.DialogFragment.STYLE_NORMAL, theme = 2;
        setStyle(style,theme);
        discipline= getArguments().getString(InterProv.COLONNE_DISCIPLINE);
        reponse=getArguments().getString(InterProv.COLONNE_REPONSE);
        question = getArguments().getString(InterProv.COLONNE_QUESTION);
        difficulte = getArguments().getInt(InterProv.COLONNE_DIFFICULTE);
        id = getArguments().getInt(InterProv.COLONNE_ID);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        int cmp = prefs.getInt(ParamActivity.PREFS_TIMER,30);
        countdown = cmp*1000;




    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_question_dialog, container, false);
        themecartev = (TextView) v.findViewById(R.id.themecarte);
        idcartev = (TextView) v.findViewById(R.id.idquestioncarte);
        difficulteview = (TextView) v.findViewById(R.id.diffquestioncarte);
        questionv =  (TextView) v.findViewById(R.id.questioncarte);
        validercartev= (Button) v.findViewById(R.id.validercarte);
        timerc = (TextView)v.findViewById(R.id.timercarte);

        themecartev.setText(discipline);
        questionv.setText(question);
        difficulteview.setText(String.valueOf(difficulte));
        idcartev.setText(String.valueOf(id));
        validercartev.setOnClickListener(this);

        editercartev= (Button) v.findViewById(
                R.id.editercarte);
        editercartev.setOnClickListener(this);
        Context context =getActivity();


        if(countDownTimer == null){
            countDownTimer = new CountDownTimer(countdown, 1000) {
                // 1000 means, onTick function will be called at every 1000
                // milliseconds

                @Override
                public void onTick(long leftTimeInMilliseconds) {
                    long seconds = leftTimeInMilliseconds / 1000;

                    Duration = String.valueOf(seconds);
                    timerc.setText(Duration);


                }

                @Override
                public void onFinish() {
                    // this function will be called when the timecount is finished
                    Duration = "fini";
                    timerc.setText(Duration);

                    ResponseDialogFragment rf = ResponseDialogFragment.newInstance(discipline, question, reponse, id, difficulte);
                    FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                    rf.show(ft, "reponse fragment");
                    dismiss();

                }


            }.start();}

        return v;
    }


    @Override
    /*public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        getActivity().finish();
    }
    */


    public void onClick(View v) {
        if(v==validercartev){
            countDownTimer.cancel();
            int cmp=0;

            //mettre des enums si le temps le permet
            switch (difficulte){
                case 1 :
                    cmp=1;
                    break;
                case 2:
                    cmp=1;
                    break;
                case 3 :
                    cmp =2;
                    break;
                default:

            }
           Intent i = new Intent(getActivity(), UpdateService.class);
            i.putExtra(InterProv.COLONNE_DISCIPLINE,discipline);
            i.putExtra(InterProv.COLONNE_QUESTION,question);
            i.putExtra(InterProv.COLONNE_REPONSE,reponse);
            i.putExtra(InterProv.COLONNE_ID,id);
            i.putExtra(InterProv.COLONNE_DIFFICULTE,cmp);
            getActivity().startService(i);


            dismiss();

        }else if (v==editercartev){
            countDownTimer.cancel();
           /* Intent i = new Intent(getActivity(), UpdateService.class);
            i.putExtra(InterProv.COLONNE_ID, id);
            i.putExtra(InterProv.COLONNE_DIFFICULTE,2);
            getActivity().startService(i); */

            ResponseDialogFragment rf = ResponseDialogFragment.newInstance(discipline,question,reponse,id,difficulte);
            FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
            rf.show(ft,"reponse fragment");

            dismiss();


        }


        // telechargement DownloadMAnager

    }
}
