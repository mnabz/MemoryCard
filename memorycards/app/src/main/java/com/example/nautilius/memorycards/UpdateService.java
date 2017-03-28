package com.example.nautilius.memorycards;

import android.app.IntentService;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class UpdateService extends IntentService {
    String theme,question,reponse;
    int id,difficulte;
    public Handler handler = new Handler() ;

    public UpdateService() {
        super("UpdateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        InterProv cr = new InterProv(this);
        Log.d("Service Satrting", "Service INttent");
        theme=intent.getStringExtra(InterProv.COLONNE_DISCIPLINE);
        question=intent.getStringExtra(InterProv.COLONNE_QUESTION);
        reponse=intent.getStringExtra(InterProv.COLONNE_REPONSE);
        id=intent.getIntExtra(InterProv.COLONNE_ID,-1);
        difficulte=intent.getIntExtra(InterProv.COLONNE_DIFFICULTE,-1);

        cr.updatecarte(theme,question,reponse,id,difficulte);



        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "update boite carte:"+id, Toast.LENGTH_SHORT).show();
            }});


    }


}
