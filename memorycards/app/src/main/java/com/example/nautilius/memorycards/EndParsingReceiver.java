package com.example.nautilius.memorycards;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by nautilius on 25/12/2016.
 */

public class EndParsingReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            String state = extras.getString("nom_jeu");
            Log.d("MY_DEBUG_TAG", state);
            Toast toast = Toast.makeText(context,"chargement jeu " +state, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    }

