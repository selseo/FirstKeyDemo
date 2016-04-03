package com.example.nan.firstkeydemo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static java.lang.Math.abs;


class Stroke{
    private String alp;
    private String vowel;
    private String fin;
    private String tone;
    private int sk;
    private  int bk;

    public Stroke() {
        alp="";
        vowel="";
        fin="";
        tone="";
        sk=0;
        bk=0;
    }
    public Stroke(String a,String b,String c,String d){
        alp=a;
        vowel=b;
        fin=c;
        tone=d;
        sk=0;
        bk=0;
    }

    public int getSk() {
        return sk;
    }

    public void setSk(int sk) {
        this.sk = sk-bk;
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

    public int getBk() {
        return bk;
    }

    public void setBk(int bk) {
        this.bk = bk;
    }

    public boolean compareComp(Stroke a) throws UnsupportedEncodingException {
        if(this.alp.equals(a.getAlp())&&this.vowel.equals(a.getVowel())&&this.fin.equals(a.getFin())&&this.tone.equals(a.getTone())){
            //.System.out.println("yeah");
            return true;
        }
        else return false;
    }
}

public class MainActivity extends Activity {
    //private String text = "";
    private TextView showText;
    //private TextView loca;
    int before = 0;
    int after = 0;
    ArrayList<Stroke>[] db = new ArrayList[100000];
    String[] rword = new String[100000];
    //int i=-1;
    Stroke data;
    HashMap<Integer, String> mapAlp = new HashMap<Integer, String>();
    HashMap<Integer, String> mapVowel = new HashMap<Integer, String>();
    HashMap<Integer, String> mapFinal = new HashMap<Integer, String>();
    HashMap<Integer, String> mapTone = new HashMap<Integer, String>();
    Stroke[] search =new Stroke[5];
    Stroke[] temp = new Stroke[4];
    String show = "";
    int ii = 0;
    String t="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showText = (TextView) findViewById(R.id.textView);
        //loca=(TextView) findViewById(R.id.textView2);
        putMap();
        //loca.setText(""+Environment.getExternalStorageDirectory());
        String path= Environment.getExternalStorageDirectory()+"/trantext.txt";
        //String path= "SD Card/Download/trantext.txt";
        //String path = "C:\\Users\\Nan\\Documents\\testdata.txt";
        data=new Stroke();

        File file = new File(path);
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            for (int i = 0; i < 70000; i++) {
                String line = br.readLine();
                line = line.trim();
                //Log.v("OUTPUT_H",line);
                //System.out.println("yeah");
                int s = line.indexOf(",");
                //System.out.println("yeah");
                rword[i] = line.substring(0, s);
                //System.out.println("yeah");
                line = line.substring(s + 1);
                int j = 0;
                boolean ch = true;
                while (j < 280 && ch) {
                    //System.out.println(i+"yeah");
                    s = line.indexOf(",");
                    String a = "";
                    a = line.substring(0, s);
                    //System.out.println("yeah");
                    line = line.substring(s + 1);
                    s = line.indexOf(",");
                    //System.out.println("yeah");
                    String v = "";
                    v = line.substring(0, s);
                    line = line.substring(s + 1);
                    String f = "";
                    String tt = "";
                    if (j < 276) {
                        //System.out.println(i+"yeah");
                        s = line.indexOf(",");

                        f = line.substring(0, s);
                        //System.out.println(i+"yeah");
                        line = line.substring(s + 1);
                        s = line.indexOf(",");
                        tt = line.substring(0, s);
                        line = line.substring(s + 1);

                    } else {
                        //System.out.println(i+"yeah");
                        f = line;

                    }
                    //System.out.println(a+v+f+t);
                    Stroke c = new Stroke(a, v, f, tt);
                    if (db[i] == null) {
                        db[i] = new ArrayList();
                    }
                    if (c.getAlp().equals("") && c.getVowel().equals("") && c.getFin().equals("") && c.getTone().equals("")) {
                        //System.out.println("yeah");
                        ch = false;
                    }
                    db[i].add(c);
                    //System.out.println(j);
                    j = j + 4;
                }
            }
            br.close();

        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
        //Log.v("OUTPUT_H","yeah");
        //vowel button
        Button vowel0 = (Button) findViewById(R.id.vowel0);
        vowel0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String t="";
                String d = mapVowel.get(1);
                int k = 1;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                   // Log.v("OUTPUT_H", "yeah");
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();

                showText.setText(t);
            }
        });
        Button vowel1 = (Button) findViewById(R.id.vowel1);
        vowel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapVowel.get(3);
                int k = 3;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel2 = (Button) findViewById(R.id.vowel2);
        vowel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapVowel.get(5);
                int k = 5;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel3 = (Button) findViewById(R.id.vowel3);
        vowel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapVowel.get(7);
                int k = 7;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel4 = (Button) findViewById(R.id.vowel4);
        vowel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapVowel.get(9);
                int k = 9;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel5 = (Button) findViewById(R.id.vowel5);
        vowel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapVowel.get(11);
                int k = 11;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel6 = (Button) findViewById(R.id.vowel6);
        vowel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapVowel.get(13);
                int k = 13;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel7 = (Button) findViewById(R.id.vowel7);
        vowel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapVowel.get(15);
                int k = 15;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel8 = (Button) findViewById(R.id.vowel8);
        vowel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapVowel.get(17);
                int k = 17;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel9 = (Button) findViewById(R.id.vowel9);
        vowel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapVowel.get(19);
                int k = 19;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel10 = (Button) findViewById(R.id.vowel10);
        vowel10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapVowel.get(21);
                int k = 21;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel11 = (Button) findViewById(R.id.vowel11);
        vowel11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapVowel.get(23);
                int k = 23;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewVowel(d, k);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });

        //change vowel button
        Button shortTop = (Button) findViewById(R.id.shorttop);
        shortTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                boolean s = chectDelay();
                if (s) changeOldVowel();
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    changeBefore();
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button shortRight = (Button) findViewById(R.id.shortright);
        shortRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                boolean s = chectDelay();
                if (s) changeOldVowel();
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    changeBefore();
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });

        //alp button
        Button alp0 = (Button) findViewById(R.id.alp0);
        alp0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapAlp.get(0);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp1 = (Button) findViewById(R.id.alp1);
        alp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapAlp.get(1);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp2 = (Button) findViewById(R.id.alp2);
        alp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String t="";
                String d = mapAlp.get(2);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp3 = (Button) findViewById(R.id.alp3);
        alp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String t="";
                String d = mapAlp.get(3);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp4 = (Button) findViewById(R.id.alp4);
        alp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(4);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp5 = (Button) findViewById(R.id.alp5);
        alp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(5);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp6 = (Button) findViewById(R.id.alp6);
        alp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapAlp.get(6);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp7 = (Button) findViewById(R.id.alp7);
        alp7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapAlp.get(7);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });

        Button alp8 = (Button) findViewById(R.id.alp8);
        alp8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String t="";
                String d = mapAlp.get(8);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp9 = (Button) findViewById(R.id.alp9);
        alp9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(9);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp10 = (Button) findViewById(R.id.alp10);
        alp10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(10);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp11 = (Button) findViewById(R.id.alp11);
        alp11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(11);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp12 = (Button) findViewById(R.id.alp12);
        alp12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(12);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp13 = (Button) findViewById(R.id.alp13);
        alp13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(13);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp14 = (Button) findViewById(R.id.alp14);
        alp14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(14);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp15 = (Button) findViewById(R.id.alp15);
        alp15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(15);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp16 = (Button) findViewById(R.id.alp16);
        alp16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(16);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp17 = (Button) findViewById(R.id.alp17);
        alp17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapAlp.get(17);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp18 = (Button) findViewById(R.id.alp18);
        alp18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapAlp.get(18);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp19 = (Button) findViewById(R.id.alp19);
        alp19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapAlp.get(19);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewAlp(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });

        //tone button
        Button tone0 = (Button) findViewById(R.id.tone0);
        tone0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapTone.get(0);
                boolean s = chectDelay();
                if (s) addOldTone(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewTone(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button tone1 = (Button) findViewById(R.id.tone1);
        tone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    String t="";
                String d = mapTone.get(1);
                boolean s = chectDelay();
                if (s) addOldTone(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewTone(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button tone2 = (Button) findViewById(R.id.tone2);
        tone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapTone.get(2);
                boolean s = chectDelay();
                if (s) addOldTone(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewTone(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button tone3 = (Button) findViewById(R.id.tone3);
        tone3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapTone.get(3);
                boolean s = chectDelay();
                if (s) addOldTone(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewTone(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });

        //final button
        Button final0 = (Button) findViewById(R.id.final0);
        final0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapFinal.get(0);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewFinal(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final1 = (Button) findViewById(R.id.final1);
        final1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapFinal.get(1);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewFinal(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final2 = (Button) findViewById(R.id.final2);
        final2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapFinal.get(2);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewFinal(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final3 = (Button) findViewById(R.id.final3);
        final3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String t="";
                String d = mapFinal.get(3);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewFinal(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final4 = (Button) findViewById(R.id.final4);
        final4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  String t="";
                String d = mapFinal.get(4);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewFinal(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final5 = (Button) findViewById(R.id.final5);
        final5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          //      String t="";
                String d = mapFinal.get(5);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewFinal(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final6 = (Button) findViewById(R.id.final6);
        final6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   String t="";
                String d = mapFinal.get(6);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewFinal(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final7 = (Button) findViewById(R.id.final7);
        final7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   String t="";
                String d = mapFinal.get(7);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else {
                    try {
                        t=realWord(data);
                        if(t.equals("NO")) t=kamArn(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    addNewFinal(d);
                }
                //String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });


    }

    protected String kamArn(String a,String v,String f,String t){
        String ans="";
        // TODO code application logic here

        Set<String> fkp = new HashSet<String>() {};
        fkp.add("กง");
        fkp.add("กน");
        fkp.add("กม");
        fkp.add("เกย");
        fkp.add("เกอว");

        Set<String> fkt = new HashSet<String>() {};
        fkt.add("กก");
        fkt.add("กบ");
        fkt.add("กด");

        Set<String> vl = new HashSet<String>() {};
        vl.add("อา");
        vl.add("อี");
        vl.add("อือ");
        vl.add("เอ");
        vl.add("โอ");
        vl.add("แอ");
        vl.add("ออ");
        vl.add("เออ");
        vl.add("เอีย");
        vl.add("เอือ");
        vl.add("อัว");
        vl.add("อู");

        Set<String> vs = new HashSet<String>() {};
        vs.add("อะ");
        vs.add("อิ");
        vs.add("อึ");
        vs.add("อุ");
        vs.add("เอะ");
        vs.add("แอะ");
        vs.add("โอะ");
        vs.add("เอาะ");
        vs.add("เออะ");
        vs.add("เอียะ");
        vs.add("เอือะ");
        vs.add("อัวะ");

        Set<String> ma=new HashSet<String>(){};
        ma.add("ก");
        ma.add("จ");
        ma.add("ด");
        ma.add("ต");
        ma.add("บ");
        ma.add("ป");

        Set<String> lao=new HashSet<String>(){};
        lao.add("ง");
        lao.add("ย");
        lao.add("น");
        lao.add("ร");
        lao.add("ว");
        lao.add("ม");
        lao.add("ฬ");
        lao.add("ล");

        Set<String> lap=new HashSet<String>(){};
        lap.add("ค");
        lap.add("ช");
        lap.add("ซ");
        lap.add("ท");
        lap.add("พ");
        lap.add("ฟ");
        lap.add("ฮ");

        Set<String> nv=new HashSet<String>(){};
        nv.add("อา");
        nv.add("อิ");
        nv.add("อี");
        nv.add("อือ");
        nv.add("อึ");
        nv.add("อุ");
        nv.add("อู");

        HashMap<String,String> pair_alp=new HashMap<String,String>();
        pair_alp.put("ค","ข");
        pair_alp.put("ช","ฉ");
        pair_alp.put("ซ","ส");
        pair_alp.put("ท","ถ");
        pair_alp.put("พ","ผ");
        pair_alp.put("ฟ","ฝ");
        pair_alp.put("ฮ","ห");

        HashMap<String,String> map_tone=new HashMap<String,String>();
        map_tone.put("เอก", "่");
        map_tone.put("โท", "้");
        map_tone.put("ตรี","๊");
        map_tone.put("จัตวา", "๋");
        map_tone.put("", "");
        map_tone.put(null, "");
        map_tone.put("NO", "");


        HashMap<String,String> map_final=new HashMap<String,String>();
        map_final.put("กก", "ก");
        map_final.put("กน", "น");
        map_final.put("กง", "ง");
        map_final.put("กด", "ด");
        map_final.put("กบ", "บ");
        map_final.put("กม", "ม");
        map_final.put("เกย", "ย");
        map_final.put("เกอว", "ว");
        map_final.put("", "");

        String nt="";
        String na="";
        if(ma.contains(a)){
            if(fkp.contains(f)){
                nt=t;
            }
            else if(fkt.contains(f)){
                if(t.equals("เอก"))nt="";
                else nt=t;
            }
            else if(f.equals("")){
                if(vl.contains(v)){
                    nt=t;
                }
                else if(vs.contains(v)){
                    if(t.equals("เอก"))nt="";
                    else nt=t;
                }
                else nt="NO";
            }
            else nt="NO";
            na=a;
        }
        else if(lao.contains(a)){
            if(fkp.contains(f)){
                if(t.equals("เอก")){
                    na="ห"+a;
                    nt=t;
                }
                else if(t.equals("โท")){
                    nt="เอก";
                    na=a;
                }
                else if(t.equals("ตรี")){
                    nt="โท";
                    na=a;
                }
                else if(t.equals("จัตวา")){
                    na="ห"+a;
                    nt="";
                }
                else if(t.equals("")) {
                    na=a;
                    nt=t;
                }
                else nt="NO";
            }
            else if(fkt.contains(f)){
                if(vl.contains(v)){
                    if(t.equals("")) {
                        nt=t;
                        na=a;
                    }
                    else if(t.equals("เอก")) {
                        na="ห"+a;
                        nt="";
                    }
                    else if(t.equals("โท")){
                        na=a;
                        nt="";
                    }
                    else if(t.equals("ตรี")){
                        na=a;
                        nt="โท";
                    }
                    else if(t.equals("จัตวา")){
                        na=a;
                        nt=t;
                    }
                    else nt="NO";
                }
                else if(vs.contains(v)){
                    if(t.equals("")) {
                        nt=t;
                        na=a;
                    }
                    else if(t.equals("เอก")) {
                        na="ห"+a;
                        nt="";
                    }
                    else if(t.equals("โท")){
                        na="ห"+a;
                        nt=t;
                    }
                    else if(t.equals("ตรี")){
                        na=a;
                        nt="";
                    }
                    else if(t.equals("จัตวา")){
                        na=a;
                        nt=t;
                    }
                    else nt="NO";
                }
                else nt="NO";
            }
            else if(f.equals("")){
                if(vl.contains(v)){
                    if(t.equals("เอก")){
                        na="ห"+a;
                        nt=t;
                    }
                    else if(t.equals("โท")){
                        nt="เอก";
                        na=a;
                    }
                    else if(t.equals("ตรี")){
                        nt="โท";
                        na=a;
                    }
                    else if(t.equals("จัตวา")){
                        na="ห"+a;
                        nt="";
                    }
                    else if(t.equals("")) {
                        na=a;
                        nt=t;
                    }
                    else nt="NO";
                }
                else if(vs.contains(v)){
                    if(t.equals("")) {
                        nt=t;
                        na=a;
                    }
                    else if(t.equals("เอก")) {
                        na="ห"+a;
                        nt="";
                    }
                    else if(t.equals("โท")){
                        na="ห"+a;
                        nt=t;
                    }
                    else if(t.equals("ตรี")){
                        na=a;
                        nt="";
                    }
                    else if(t.equals("จัตวา")){
                        na=a;
                        nt=t;
                    }
                    else nt="NO";
                }
                else nt="NO";
            }
            else nt="NO";
        }
        else if(lap.contains(a)){
            if(fkp.contains(f)){
                if(t.equals("เอก")){
                    na=pair_alp.get(a);
                    nt=t;
                }
                else if(t.equals("โท")){
                    nt="เอก";
                    na=a;
                }
                else if(t.equals("ตรี")){
                    nt="โท";
                    na=a;
                }
                else if(t.equals("จัตวา")){
                    na=pair_alp.get(a);
                    nt="";
                }
                else if(t.equals("")) {
                    na=a;
                    nt=t;
                }
                else nt="NO";
            }
            else if(fkt.contains(f)){
                if(vl.contains(v)){
                    if(t.equals("")) {
                        nt=t;
                        na=a;
                    }
                    else if(t.equals("เอก")) {
                        na=pair_alp.get(a);
                        nt="";
                    }
                    else if(t.equals("โท")){
                        na=a;
                        nt="";
                    }
                    else if(t.equals("ตรี")){
                        na=a;
                        nt="โท";
                    }
                    else if(t.equals("จัตวา")){
                        na=a;
                        nt=t;
                    }
                    else nt="NO";
                }
                else if(vs.contains(v)){
                    if(t.equals("")) {
                        nt=t;
                        na=a;
                    }
                    else if(t.equals("เอก")) {
                        na=pair_alp.get(a);
                        nt="";
                    }
                    else if(t.equals("โท")){
                        na=pair_alp.get(a);
                        nt=t;
                    }
                    else if(t.equals("ตรี")){
                        na=a;
                        nt="";
                    }
                    else if(t.equals("จัตวา")){
                        na=a;
                        nt=t;
                    }
                    else nt="NO";
                }
                else nt="NO";
            }
            else if(f.equals("")){
                if(vl.contains(v)){
                    if(t.equals("เอก")){
                        na=pair_alp.get(a);
                        nt=t;
                    }
                    else if(t.equals("โท")){
                        nt="เอก";
                        na=a;
                    }
                    else if(t.equals("ตรี")){
                        nt="โท";
                        na=a;
                    }
                    else if(t.equals("จัตวา")){
                        na=pair_alp.get(a);
                        nt="";
                    }
                    else if(t.equals("")) {
                        na=a;
                        nt=t;
                    }
                    else nt="NO";
                }
                else if(vs.contains(v)){
                    if(t.equals("")) {
                        nt=t;
                        na=a;
                    }
                    else if(t.equals("เอก")) {
                        na=pair_alp.get(a);
                        nt="";
                    }
                    else if(t.equals("โท")){
                        na=pair_alp.get(a);
                        nt=t;
                    }
                    else if(t.equals("ตรี")){
                        na=a;
                        nt="";
                    }
                    else if(t.equals("จัตวา")){
                        na=a;
                        nt=t;
                    }
                    else nt="NO";
                }
                else nt="NO";
            }
            else nt="NO";
        }
        else {
            nt="NO";
        }
        if(na.equals("")||na.equals(null)) na="อ";
        if(v.equals("อะ")){
            if(f.equals("")){
                ans=na+map_tone.get(nt)+"ะ";
            }
            else {
                ans=na+"ั"+map_tone.get(nt)+map_final.get(f);
            }
        }
        else if(v.equals("อา")){
            ans=na+map_tone.get(nt)+"า"+map_final.get(f);
        }
        else if(v.equals("อี")){
            ans=na+"ี"+map_tone.get(nt)+map_final.get(f);
        }
        else if(v.equals("อิ")){
            ans=na+"ิ"+map_tone.get(nt)+map_final.get(f);
        }
        else if(v.equals("อึ")){
            ans=na+"ึ"+map_tone.get(nt)+map_final.get(f);
        }
        else if(v.equals("อือ")){
            ans=na+"ื"+map_tone.get(nt)+map_final.get(f);
        }
        else if(v.equals("อุ")){
            ans=na+"ุ"+map_tone.get(nt)+map_final.get(f);
        }
        else if(v.equals("อู")){
            ans=na+"ู"+map_tone.get(nt)+map_final.get(f);
        }
        else if(v.equals("เอะ")){
            if(f.equals("")){
                ans="เ"+na+map_tone.get(nt)+"ะ";
            }
            else {
                ans="เ"+na+"็"+map_tone.get(nt)+map_final.get(f);
            }
        }
        else if(v.equals("เอ")){
            ans="เ"+na+map_tone.get(nt)+map_final.get(f);
        }
        else if(v.equals("แอะ")){
            if(f.equals("")){
                ans="แ"+na+map_tone.get(nt)+"ะ";
            }
            else {
                ans="แ"+na+"็"+map_tone.get(nt)+map_final.get(f);
            }
        }
        else if(v.equals("แอ")){
            ans="แ"+na+map_tone.get(nt)+map_final.get(f);
        }
        else if(v.equals("เอียะ")){
            ans="เ"+na+"ี"+map_tone.get(nt)+"ยะ";
        }
        else if(v.equals("เอีย")){
            ans="เ"+na+"ี"+map_tone.get(nt)+"ย"+map_final.get(f);
        }
        else if(v.equals("เอือะ")){
            ans="เ"+na+"ื"+map_tone.get(nt)+"อะ";
        }
        else if(v.equals("เอือ")){
            ans="เ"+na+"ื"+map_tone.get(nt)+"อ"+map_final.get(f);
        }
        else if(v.equals("อัวะ")){
            ans=na+"ั"+map_tone.get(nt)+"วะ";
        }
        else if(v.equals("อัว")){
            if(f.equals("")){
                ans=na+"ั"+map_tone.get(nt)+"ว";
            }
            else {
                ans=na+map_tone.get(nt)+"ว"+map_final.get(f);
            }
        }
        else if(v.equals("โอะ")){
            if(f.equals("")){
                ans="โ"+na+map_tone.get(nt)+"ะ";
            }
            else {
                ans=na+map_tone.get(nt)+map_final.get(f);
            }
        }
        else if(v.equals("โอ")){
            ans="โ"+na+map_tone.get(nt)+map_final.get(f);
        }
        else if(v.equals("เอาะ")){
            if(f.equals("")){
                ans="เ"+a+map_tone.get(nt)+"าะ";
            }
            else {
                ans=a+"็อ"+map_final.get(f);
            }
        }
        else if(v.equals("ออ")){
            ans=a+map_tone.get(nt)+"อ"+map_final.get(f);
        }
        else if(v.equals("เออะ")){
            if(f.equals("")){
                ans="เ"+a+map_tone.get(nt)+"อะ";
            }
            else {
                ans="เ"+a+"ิ"+map_tone.get(nt)+map_final.get(f);
            }
        }
        else if(v.equals("เออ")){
            ans="เ"+a+map_tone.get(nt)+"อ"+map_final.get(f);
        }

        return ans;
    }

    protected boolean chectDelay() {
        Calendar c = Calendar.getInstance();
        before = after;
        after = c.get(Calendar.MILLISECOND) + c.get(Calendar.SECOND) * 1000;
        int a = abs(after - before);
        if (a > 300) return false;
        else return true;
    }

    /*
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
    protected void addOldVowel(String dd, int j) {
        data.setSk(j);
        data.setVowel(mapVowel.get(data.getSk()));
    }

    protected void addNewVowel(String dd, int j) {
        data = new Stroke();
        data.setVowel(dd);
        data.setSk(j);
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

    protected void changeOldVowel() {
        data.setVowel(mapVowel.get(data.getSk() - 1));
    }

    protected void changeBefore() {
        data = new Stroke();
        data.setBk(1);
    }

    protected void putMap() {
        //put Alp
        mapAlp.put(0, "จ");
        mapAlp.put(1, "ช");
        mapAlp.put(2, "ฮ");
        mapAlp.put(3, "ด");
        mapAlp.put(4, "ซ");
        mapAlp.put(5, "น");
        mapAlp.put(6, "ค");
        mapAlp.put(7, "ท");
        mapAlp.put(8, "ก");
        mapAlp.put(9, "ป");
        mapAlp.put(10, "ต");
        mapAlp.put(11, "พ");
        mapAlp.put(12, "ม");
        mapAlp.put(13, "ร");
        mapAlp.put(14, "ล");
        mapAlp.put(15, "ย");
        mapAlp.put(16, "ง");
        mapAlp.put(17, "ว");
        mapAlp.put(18, "บ");
        mapAlp.put(19, "ฟ");
        //put vowel
        mapVowel.put(0, "เอะ");
        mapVowel.put(1, "เอ");
        mapVowel.put(2, "แอะ");
        mapVowel.put(3, "แอ");
        mapVowel.put(4, "อัวะ");
        mapVowel.put(5, "อัว");
        mapVowel.put(6, "อึ");
        mapVowel.put(7, "อือ");
        mapVowel.put(8, "อะ");
        mapVowel.put(9, "อา");
        mapVowel.put(10, "อิ");
        mapVowel.put(11, "อี");
        mapVowel.put(12, "อุ");
        mapVowel.put(13, "อู");
        mapVowel.put(14, "โอะ");
        mapVowel.put(15, "โอ");
        mapVowel.put(16, "เอาะ");
        mapVowel.put(17, "ออ");
        mapVowel.put(18, "เอียะ");
        mapVowel.put(19, "เอีย");
        mapVowel.put(20, "เออะ");
        mapVowel.put(21, "เออ");
        mapVowel.put(22, "เอือะ");
        mapVowel.put(23, "เอือ");
        //put final
        mapFinal.put(0, "กม");
        mapFinal.put(1, "กน");
        mapFinal.put(2, "กง");
        mapFinal.put(3, "กบ");
        mapFinal.put(4, "กก");
        mapFinal.put(5, "เกย");
        mapFinal.put(6, "กด");
        mapFinal.put(7, "เกอว");
        //put tone
        mapTone.put(0, "เอก");
        mapTone.put(1, "โท");
        mapTone.put(2, "ตรี");
        mapTone.put(3, "จัตวา");
    }

    public static String checkMatch(Stroke[] c, ArrayList<Stroke>[] d, String[] rw, int n) throws UnsupportedEncodingException {
        for (int i = 0; i < 70000; i++) {
            //System.out.println(d[i].size());
            if ((d[i].size() - 1) == n) {
                boolean lo = true;
                //System.out.println("n>>"+n);
                for (int j = 0; j < n; j++) {
                    //System.out.println("yeah "+j+" "+d[i].get(j).getAlp()+" "+c[j].getAlp());
                    if (!c[j].compareComp(d[i].get(j))) {
                        lo = false;
                        break;
                    }
                }
                //System.out.println("yeah2");
                if (lo) return rw[i];
            }
        }
        return "";
    }

    public String realWord(Stroke data) throws UnsupportedEncodingException {
        //if(data!=null)Log.v("OUTPUT_H","Yeeee");
        search[ii] = new Stroke(data.getAlp(),data.getVowel(),data.getFin(),data.getTone());
        if (ii < 4) {
            //Log.v("OUTPUT_H",data.getVowel());
            ii++;
            return "";
        }
        else {
            //Log.v("OUTPUT_H",data.getVowel());
            show = checkMatch(search, db, rword, 5);
            if (!show.equals("")) {
                //System.out.println(show);
                ii = 0;
                return show;
            } else {
                temp[0] = search[4];
                show = checkMatch(search, db, rword, 4);
                //System.out.println(show+" ff");
                if (!show.equals("")) {
                    //System.out.println(show);
                    search[0] = temp[0];
                    ii = 1;
                    return  show;
                } else {
                    temp[1] = search[3];
                    show = checkMatch(search, db, rword, 3);
                    if (!show.equals("")) {
                        //System.out.println(show);
                        search[0] = temp[1];
                        search[1] = temp[0];
                        ii = 2;
                        return show;
                    } else {
                        temp[2] = search[2];
                        show = checkMatch(search, db, rword, 2);
                        if (!show.equals("")) {
                            //System.out.println(show);
                            search[0] = temp[2];
                            search[1] = temp[1];
                            search[2] = temp[0];
                            ii = 3;
                            return show;
                        } else {
                            temp[3] = search[1];
                            show = checkMatch(search, db, rword, 1);
                            if (!show.equals("")) {
                                show=show+"";
                            } else {
                                show="NO";
                            }
                            search[0] = temp[3];
                            search[1] = temp[2];
                            search[2] = temp[1];
                            search[3] = temp[0];
                            ii = 4;
                            return show;
                        }
                    }
                }
            }

        }
        //sa=sa+1;
    }



}



