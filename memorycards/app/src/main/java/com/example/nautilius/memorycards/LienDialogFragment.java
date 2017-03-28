package com.example.nautilius.memorycards;

import android.app.DialogFragment;
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


public class LienDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String REFERENCE= "REFERENCE";
    private EditText lien;
    private Handler handler = new Handler();
    public static LienDialogFragment newInstance(String lienpResId)
    {
        LienDialogFragment ldf = new LienDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("lien_resource", lienpResId);
        ldf.setArguments(bundle);

        return ldf;
    }


    @Override
    public void onCreate(Bundle icicle)
    {
        super.onCreate(icicle);
        this.setCancelable(true);
        int style = DialogFragment.STYLE_NORMAL, theme = 2;
        setStyle(style,theme);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lien_dialog, container, false);
        TextView tv = (TextView) v.findViewById(R.id.urllien);


        lien= (EditText)v.findViewById(R.id.edittext);
        Button dl = (Button)v.findViewById(
                R.id.valider);
        dl.setOnClickListener(this);
        return v;
    }

    public void onClick(View v)
    {
        String link = lien.getText().toString();


       Intent i = new Intent(getActivity(), DmServiceInsert.class);
        i.putExtra(REFERENCE,link);
        getActivity().startService(i);

        Log.d("url",link);

        dismiss();

        // telechargement DownloadMAnager

    }







}
