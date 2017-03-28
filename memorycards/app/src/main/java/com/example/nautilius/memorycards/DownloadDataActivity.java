package com.example.nautilius.memorycards;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import android.app.IntentService;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Query;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class DownloadDataActivity extends Activity implements OnClickListener {
    public  ArrayList<Carte> cartes;
    private DownloadManager downloadManager;
    public long downloadReference;
    String ref;
    public String nom_jeu;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_data);
    cartes = new ArrayList<Carte>();
        //start download button
        Button startDownload = (Button) findViewById(R.id.startDownload);
        startDownload.setOnClickListener(this);

        //display all download button
        Button displayDownload = (Button) findViewById(R.id.displayDownload);
        displayDownload.setOnClickListener(this);

        //check download status button
        Button checkStatus = (Button) findViewById(R.id.checkStatus);
        checkStatus.setOnClickListener(this);
        checkStatus.setEnabled(false);

        //cancel download button
        Button cancelDownload = (Button) findViewById(R.id.cancelDownload);
        cancelDownload.setOnClickListener(this);
        cancelDownload.setEnabled(false);

        //set filter to only when download is complete and register broadcast receiver
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        registerReceiver(downloadReceiver, filter);
        ref = getIntent().getExtras().getString(LienDialogFragment.REFERENCE);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    public void onClick(View v) {

        switch (v.getId()) {

            //start the download process
            case R.id.startDownload:

                downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                Uri Download_Uri = Uri.parse(ref);
                DownloadManager.Request request = new DownloadManager.Request(Download_Uri);

                //Restrict the types of networks over which this download may proceed.
                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
                //Set whether this download may proceed over a roaming connection.
                request.setAllowedOverRoaming(false);
                //Set the title of this download, to be displayed in notifications (if enabled).
                request.setTitle("My Data Download");
                //Set a description of this download, to be displayed in notifications (if enabled)
                request.setDescription("Android Data download using DownloadManager.");
                //Set the local destination for the downloaded file to a path within the application's external files directory
                request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "jeu.xml");

                //Enqueue a new download and same the referenceId
                downloadReference = downloadManager.enqueue(request);

                TextView showCartes = (TextView) findViewById(R.id.CarteData);
                showCartes.setText("Getting data from Server, Please WAIT...");

                Button checkStatus = (Button) findViewById(R.id.checkStatus);
                checkStatus.setEnabled(true);
                Button cancelDownload = (Button) findViewById(R.id.cancelDownload);
                cancelDownload.setEnabled(true);
                break;

            //display all downloads
            case R.id.displayDownload:

                Intent intent = new Intent();
                intent.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
                startActivity(intent);
                break;

            //check the status of a download
            case R.id.checkStatus:

                Query myDownloadQuery = new Query();
                //set the query filter to our previously Enqueued download
                myDownloadQuery.setFilterById(downloadReference);

                //Query the download manager about downloads that have been requested.
                Cursor cursor = downloadManager.query(myDownloadQuery);
                if (cursor.moveToFirst()) {
                    checkStatus(cursor);
                }
                break;

            //cancel the ongoing download
            case R.id.cancelDownload:

                downloadManager.remove(downloadReference);
                checkStatus = (Button) findViewById(R.id.checkStatus);
                checkStatus.setEnabled(false);
                showCartes= (TextView) findViewById(R.id.CarteData);
                showCartes.setText("Download of the file cancelled...");

                break;

            // More buttons go here (if any) ...

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void checkStatus(Cursor cursor) {

        //column for status
        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(columnIndex);
        //column for reason code if the download failed or paused
        int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
        int reason = cursor.getInt(columnReason);
        //get the download filename

        // int filenameIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME);
        // String filename = cursor.getString(filenameIndex);

        String statusText = "";
        String reasonText = "";

        switch (status) {
            case DownloadManager.STATUS_FAILED:
                statusText = "STATUS_FAILED";
                switch (reason) {
                    case DownloadManager.ERROR_CANNOT_RESUME:
                        reasonText = "ERROR_CANNOT_RESUME";
                        break;
                    case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                        reasonText = "ERROR_DEVICE_NOT_FOUND";
                        break;
                    case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                        reasonText = "ERROR_FILE_ALREADY_EXISTS";
                        break;
                    case DownloadManager.ERROR_FILE_ERROR:
                        reasonText = "ERROR_FILE_ERROR";
                        break;
                    case DownloadManager.ERROR_HTTP_DATA_ERROR:
                        reasonText = "ERROR_HTTP_DATA_ERROR";
                        break;
                    case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                        reasonText = "ERROR_INSUFFICIENT_SPACE";
                        break;
                    case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                        reasonText = "ERROR_TOO_MANY_REDIRECTS";
                        break;
                    case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                        reasonText = "ERROR_UNHANDLED_HTTP_CODE";
                        break;
                    case DownloadManager.ERROR_UNKNOWN:
                        reasonText = "ERROR_UNKNOWN";
                        break;
                }
                break;
            case DownloadManager.STATUS_PAUSED:
                statusText = "STATUS_PAUSED";
                switch (reason) {
                    case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                        reasonText = "PAUSED_QUEUED_FOR_WIFI";
                        break;
                    case DownloadManager.PAUSED_UNKNOWN:
                        reasonText = "PAUSED_UNKNOWN";
                        break;
                    case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                        reasonText = "PAUSED_WAITING_FOR_NETWORK";
                        break;
                    case DownloadManager.PAUSED_WAITING_TO_RETRY:
                        reasonText = "PAUSED_WAITING_TO_RETRY";
                        break;
                }
                break;
            case DownloadManager.STATUS_PENDING:
                statusText = "STATUS_PENDING";
                break;
            case DownloadManager.STATUS_RUNNING:
                statusText = "STATUS_RUNNING";
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                statusText = "STATUS_SUCCESSFUL";
                reasonText = "Filename:\n";
                break;
        }


        Toast toast = Toast.makeText(DownloadDataActivity.this,
                statusText + "\n" +
                        reasonText,
                Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 25, 400);
        toast.show();

    }

    private BroadcastReceiver downloadReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {


            //check if the broadcast message is for our Enqueued download
            long referenceId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (downloadReference == referenceId) {

                Button cancelDownload = (Button) findViewById(R.id.cancelDownload);
                cancelDownload.setEnabled(false);


                ParcelFileDescriptor file;

                StringBuffer carteData = new StringBuffer("");
                StringBuffer strContent = new StringBuffer("");


                try {
                    file = downloadManager.openDownloadedFile(downloadReference);

                    FileInputStream fileInputStream
                            = new ParcelFileDescriptor.AutoCloseInputStream(file);
                    int ch;
                    while( (ch = fileInputStream.read()) != -1)
                        strContent.append((char)ch);
                    InputSource is = new InputSource();
                    is.setCharacterStream(new StringReader(strContent.toString()));

                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

                    DocumentBuilder builder = factory.newDocumentBuilder();
                    Document dom = builder.parse(is);

                    Element root = dom.getDocumentElement();
                    nom_jeu = root.getAttribute("nom");
                    NodeList items = root.getElementsByTagName("carte"); //
                    if (items != null && items.getLength() > 0) {
                        for (int i = 0; i < items.getLength(); i++) {
                            Element carte = (Element) items.item(i);
                            String question = carte.getElementsByTagName("question").item(0).getTextContent();
                            String reponse = carte.getElementsByTagName("reponse").item(0).getTextContent();
                            int diff = Integer.parseInt(carte.getElementsByTagName("difficulte").item(0).getTextContent());
                            carteData.append(question + ": " + reponse + ": " + diff + "\n");
                           cartes.add(new Carte(nom_jeu,question, reponse, diff));
                        }
                        TextView showCountries = (TextView) findViewById(R.id.CarteData);
                        showCountries.setText(carteData.toString());

                        Toast toast = Toast.makeText(DownloadDataActivity.this,
                                "Downloading of data just finished : arraylist size "+cartes.size(), Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.TOP, 25, 400);
                        toast.show();


                        Intent service = new Intent(DownloadDataActivity.this,DmServiceInsert.class);
                        service.putExtra(InterProv.COLONNE_DISCIPLINE,nom_jeu);
                        service.putExtra("cartes",cartes);
                        startService(service);

                    }


                } catch (FileNotFoundException e) {
                    e.printStackTrace();


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }
            }

        }
    };


    public  void onDestroy(){
        unregisterReceiver(downloadReceiver);
        super.onDestroy();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

}
