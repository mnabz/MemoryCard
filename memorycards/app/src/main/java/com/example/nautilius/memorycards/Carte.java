package com.example.nautilius.memorycards;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nautilius on 21/12/2016.
 */

public class Carte implements Parcelable {
        private String discipline, question;
         private String reponse;
        private int difficulte,_id;



    public Carte(String discipline,String q, String rep , int diff){
        this.discipline=discipline;
        this.question=q;
        this.reponse=rep;
        this.difficulte= diff;

    }
    public Carte(String discipline,String q, String rep , int diff,int id){
        this.discipline=discipline;
        this.question=q;
        this.reponse=rep;
        this.difficulte= diff;
        this._id = id;
    }


    public Carte(String q, String rep, int d){
        this.question=q;
        this.reponse=rep;
        this.difficulte=d;


    }

    protected Carte(Parcel in) {
        discipline = in.readString();
        question = in.readString();
        reponse = in.readString();
        difficulte = in.readInt();
        _id = in.readInt();
    }

    public static final Creator<Carte> CREATOR = new Creator<Carte>() {
        @Override
        public Carte createFromParcel(Parcel in) {
            return new Carte(in);
        }

        @Override
        public Carte[] newArray(int size) {
            return new Carte[size];
        }
    };

    public String getQuestion(){
        return this.question;
    }

    public String getDiscipline(){
        return this.discipline;
    }


    public String getReponse(){
        return this.reponse;
    }

    public void setQuestion(String q){
        this.question= q;
    }

     public void setReponse(String rep){
         this.reponse=rep;
     }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }


    public int get_id_table() {
        return _id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(discipline);
        dest.writeString(question);
        dest.writeString(reponse);
        dest.writeInt(difficulte);
        dest.writeInt(_id);
    }
}
