package com.example.nan.firstkeydemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;


class Stroke{
    private String alp;
    private String vowel;
    private String fin;
    private String tone;

    public Stroke() {
        alp="";
        vowel="";
        fin="";
        tone="";
    }

    public String getAlp() {
        return alp;
    }

    public void setAlp(String alp) {
        this.alp = alp;
    }

    public String getVowel() {
        return vowel;
    }

    public void setVowel(String vowel) {
        this.vowel = vowel;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getTone() {
        return tone;
    }

    public void setTone(String tone) {
        this.tone = tone;
    }
}

public class MainActivity extends Activity {
    //private String text = "";
    private TextView showText;
    int before = 0;
    int after = 0;
    //int i=-1;
    Stroke data;
    HashMap<Integer, String> mapAlp = new HashMap<Integer, String>();


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;
    //private Stroke data[]=new Stroke[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showText = (TextView) findViewById(R.id.textView);
        mapAlp.put(0,"");

        //Calendar c = Calendar.getInstance();
        //int seconds = c.get(Calendar.MILLISECOND);
        //String t="";
        Button vowel0 = (Button) findViewById(R.id.vowel0);
        vowel0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "เอ";
                boolean s = chectDelay();
                /*if(s) addOldVowel(i,d);
                else {
                    i++;
                    if(i==5) i=0;
                    addNewVowel(i,d);
                    //i++;
                }
                String t=showData();
                //String t=""+i;*/
                if (s) addOldVowel(d);
                else addNewVowel(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel1 = (Button) findViewById(R.id.vowel1);
        vowel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "แอ";
                boolean s = chectDelay();
                /*if(s) addOldVowel(i,d);
                else {
                    i++;
                    if(i==5) i=0;
                    addNewVowel(i,d);
                    //i++;
                }
                String t=showData();*/
                if (s) addOldVowel(d);
                else addNewVowel(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button shortTop = (Button) findViewById(R.id.shorttop);
        shortTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "แอ";
                boolean s = chectDelay();
                /*if(s) addOldVowel(i,d);
                else {
                    i++;
                    if(i==5) i=0;
                    addNewVowel(i,d);
                    //i++;
                }
                String t=showData();*/
                if (s) addOldVowel(d);
                else addNewVowel(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp0 = (Button) findViewById(R.id.alp0);
        alp0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "จ";
                boolean s = chectDelay();
                /*if(s) addOldAlp(i,d);
                else {
                    i++;
                    if(i==5) i=0;
                    addNewAlp(i,d);
                    //i++;
                }
                String t=showData();*/
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp1 = (Button) findViewById(R.id.alp1);
        alp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "ช";
                boolean s = chectDelay();
                /*if(s) addOldAlp(i,d);
                else {
                    i++;
                    if(i==5) i=0;
                    addNewAlp(i,d);
                    //i++;
                }
                String t=showData();*/
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button tone0 = (Button) findViewById(R.id.tone0);
        tone0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "เอก";
                boolean s = chectDelay();
                /*if (s) addOldTone(i, d);
                else {
                    i++;
                    if(i==5) i=0;
                    addNewTone(i, d);
                    //i++;
                }
                String t = showData();*/
                if (s) addOldTone(d);
                else addNewTone(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button tone1 = (Button) findViewById(R.id.tone1);
        tone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "โท";
                boolean s = chectDelay();
                /*if (s) addOldTone(i, d);
                else {
                    i++;
                    if(i==5) i=0;
                    addNewTone(i, d);
                    //i++;
                }
                String t = showData();*/
                if (s) addOldTone(d);
                else addNewTone(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final0 = (Button) findViewById(R.id.final0);
        final0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "กม";
                boolean s = chectDelay();
               /* if (s) addOldFinal(i, d);
                else {
                    i++;
                    if(i==5) i=0;
                    addNewFinal(i, d);
                    //i++;
                }
                String t = showData();*/
                if (s) addOldFinal(d);
                else addNewFinal(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final1 = (Button) findViewById(R.id.final1);
        final1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = "กน";
                boolean s = chectDelay();
                /*if (s) addOldFinal(i, d);
                else {
                    i++;
                    if(i==5) i=0;
                    addNewFinal(i, d);
                    //i++;
                }
                String t = showData();*/
                if (s) addOldFinal(d);
                else addNewFinal(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });


    }

    protected boolean chectDelay() {
        Calendar c = Calendar.getInstance();
        before = after;
        after = c.get(Calendar.MILLISECOND) + c.get(Calendar.SECOND) * 1000;
        int a = abs(after - before);
        if (a > 300) return false;
        else return true;
    }

    /*protected void addOldVowel(int j,String dd){
        data[j].setVowel(dd);
    }
    protected void addNewVowel(int j,String dd){
        data[j]=new Stroke();
        data[j].setVowel(dd);
    }
    protected void addOldAlp(int j,String dd){
        data[j].setAlp(dd);
    }
    protected void addNewAlp(int j,String dd){
        data[j]=new Stroke();
        data[j].setAlp(dd);
    }
    protected void addOldTone(int j,String dd){
        data[j].setTone(dd);
    }
    protected void addNewTone(int j,String dd){
        data[j]=new Stroke();
        data[j].setTone(dd);
    }
    protected void addOldFinal(int j,String dd){
        data[j].setFin(dd);
    }
    protected void addNewFinal(int j,String dd){
        data[j]=new Stroke();
        data[j].setFin(dd);
    }
    protected  String showData(){
        String r="";
        //int j=0;
        for (int j=0;j<5;j++){
            //r=""+j;
            if(data[j]==null) break;

            r=r+data[j].getAlp()+" "+data[j].getVowel()+" "+data[i].getFin()+" "+data[i].getTone()+" , ";

        }
        return r;
    }*/
    protected void addOldVowel(String dd) {
        data.setVowel(dd);
    }

    protected void addNewVowel(String dd) {
        data = new Stroke();
        data.setVowel(dd);
    }

    protected void addOldAlp(String dd) {
        data.setAlp(dd);
    }

    protected void addNewAlp(String dd) {
        data = new Stroke();
        data.setAlp(dd);
    }

    protected void addOldFinal(String dd) {
        data.setFin(dd);
    }

    protected void addNewFinal(String dd) {
        data = new Stroke();
        data.setFin(dd);
    }

    protected void addOldTone(String dd) {
        data.setTone(dd);
    }

    protected void addNewTone(String dd) {
        data = new Stroke();
        data.setTone(dd);
    }


}
