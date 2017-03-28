package com.example.nautilius.memorycards;

/**
 * Created by nautilius on 28/12/2016.
 */



import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.net.Uri;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nabil on 16/12/16.
 */

public class InterProv {


    private Context c;
    private ContentResolver cr;
    public final static int VERSION = 1;
    public final static String DB_NAME = "base";

    public final static String JEUX = "jeux";
    public final static String COLONNE_DISCIPLINE= "discipline";
    public final static String COLONNE_ID= "_id";
    public final static String CARTE = "carte";
    public final static String COLONNE_QUESTION= "question";
    public final static String COLONNE_REPONSE= "reponse";
    public final static String COLONNE_DIFFICULTE= "difficulte";
    public final static String COLONNE_DATE= "datecol";

    private static String authority = "com.example.nautilius.contentprovider";

    public InterProv(Context con){
        c = con;
        cr = con.getContentResolver();
    }

    public void insertJeu(String discipline){
       // Log.d("theme demandé insert jeu  ", discipline);
        try {

            ContentValues values = new ContentValues();
            values.put(COLONNE_DISCIPLINE, discipline);

            Uri.Builder builder = new Uri.Builder();
            builder.scheme("content").authority(authority).appendPath(JEUX);

            Uri uri = builder.build();
            cr.insert(uri, values);
         //   Log.d("uri interprov jeudemand ", uri.toString());
        }
        catch(SQLException e){
            Log.d("toto ","Erreur d'insertion dans Jeux");
        }
    }

    public void insertCarte(String discipline, String question, String reponse, int difficulte){
       // SimpleDateFormat fdate = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd");
        String date = fdate.format(today);

        //Log.d("Content values icarte discipline ",""+discipline );
        //Log.d("Content values icarte ques ",""+question );
        //Log.d("Content values icarte rep ",""+reponse);
        //Log.d("Content values icarte diff ",""+difficulte );
        //Log.d("Content values icarte diff ",""+date);
        try {
            ContentValues values = new ContentValues();
            values.put(COLONNE_DISCIPLINE, discipline);
            values.put(COLONNE_QUESTION, question);
            values.put(COLONNE_REPONSE, reponse);
            values.put(COLONNE_DIFFICULTE, difficulte);
            values.put(COLONNE_DATE, date);
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("content").authority(authority).appendPath(CARTE);
            //builder = ContentUris.appendId(builder, 2);
            Uri uri = builder.build();
             cr.insert(uri, values);
            //Log.d("Uri interprov cartedeman", uri.toString());
        }
        catch(SQLException e){

            Log.d("toto ","Erreur d'insertion dans Cartes");
        }
    }

    public void suppJeu(String args){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("content").authority(authority).appendPath(JEUX);
        Uri uri = builder.build();
        cr.delete(uri,COLONNE_DISCIPLINE,new String[]{args});


    }


    public void updatecarte(String discipline,String question,String reponse,int id, int diff){
        try {
            String date=null;
            switch (diff){
                case 3 :
                  Calendar c = Calendar.getInstance();
                    c.add(Calendar.DATE,1);
                    Date today = c.getTime();
                    SimpleDateFormat fdate = new SimpleDateFormat("yyyy-MM-dd");
                    date = fdate.format(today);



                    break;
                case 2:

                    Calendar c1= Calendar.getInstance();
                    c1.add(Calendar.DATE,2);
                    Date today1= c1.getTime();
                    SimpleDateFormat fdate1 = new SimpleDateFormat("yyyy-MM-dd");
                    date = fdate1.format(today1);
                    break;

                case 1:
                    Calendar c2 = Calendar.getInstance();
                    c2.add(Calendar.DATE,3);
                    Date today2 = c2.getTime();
                    SimpleDateFormat fdate2= new SimpleDateFormat("yyyy-MM-dd");
                    date = fdate2.format(today2);
                    break;
                default:
                    Log.d("erreur difficulte", "interprov diffculte");
            }

            ContentValues values = new ContentValues();
            values.put(COLONNE_ID, id);
            values.put(COLONNE_DISCIPLINE, discipline);
            values.put(COLONNE_QUESTION, question);
            values.put(COLONNE_REPONSE, reponse);
            values.put(COLONNE_DIFFICULTE, diff);
            values.put(COLONNE_DATE, date);
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("content").authority(authority).appendPath("carte");
            Uri uri = builder.build();


           // Log.d("Uri interprov carte maj ", uri.toString());
                cr.update(uri, values,COLONNE_ID+" = "+id,null);

        }
        catch(SQLException e){

            Log.d("toto ","Erreur maj dans Cartes");
        }


    }







}
