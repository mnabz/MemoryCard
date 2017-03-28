package com.example.nautilius.memorycards;

import android.app.DownloadManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import android.os.Handler;
import java.util.logging.LogRecord;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static android.app.DownloadManager.COLUMN_LOCAL_URI;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class DmServiceInsert extends IntentService {
    //private static String authority = "com.example.nautilius.provider";
    private Long myDreference;
    private ArrayList<Carte> cartes;
    private String nom_jeu;
    public Handler handler = new Handler() ;


    public DmServiceInsert() {
        super("DmServiceInsert");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */


    @Override
    protected void onHandleIntent(Intent intent) {
        InterProv cr= new InterProv(this);
        Log.d("Service Satrting", "Service INttent");
        String discipline = intent.getStringExtra(InterProv.COLONNE_DISCIPLINE);
        ArrayList<Carte>cartes;
        cartes= intent.getParcelableArrayListExtra("cartes");
        cr.insertJeu(discipline);
        for(Carte a : cartes){
            cr.insertCarte(discipline,a.getQuestion(),a.getReponse(),a.getDifficulte());

        }



        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Nouveau chargé:", Toast.LENGTH_LONG).show();
            }});


    }
    public void affiche(ArrayList<Carte>cartes){

        for(Carte a : cartes){
            Log.d("carte dans array",a.getQuestion()+ " : "+a.getReponse()+" " );
        }
    }





}
