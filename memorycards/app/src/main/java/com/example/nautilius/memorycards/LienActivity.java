package com.example.nautilius.memorycards;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.ParcelFileDescriptor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static android.app.DownloadManager.COLUMN_LOCAL_URI;

public class LienActivity extends AppCompatActivity {
    private EditText lien;
    public static final String REFERENCE = "REFERENCE";
    private BroadcastReceiver receiverDLcomplete, receiverNotificationClicked;
    DownloadManager downloadManager;
    private long myDownloadReference;
    private ArrayList<Carte> cartes;
    private String nom_jeu;
    public Handler handler = new Handler() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lien);
        TextView tv = (TextView) findViewById(R.id.urllien);

        lien = (EditText) findViewById(R.id.edittext);
        Button dl = (Button) findViewById(
                R.id.valider);

        dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = lien.getText().toString();
                Intent i = new Intent(LienActivity.this, DownloadDataActivity.class);
                i.putExtra(REFERENCE,link);
             startActivity(i);
                Log.d("url",link);

            }
        });
    }






}

