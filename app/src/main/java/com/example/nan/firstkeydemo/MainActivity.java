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
}

public class MainActivity extends Activity {
    //private String text = "";
    private TextView showText;
    int before = 0;
    int after = 0;
    //int i=-1;
    Stroke data;
    HashMap<Integer, String> mapAlp = new HashMap<Integer, String>();
    HashMap<Integer, String> mapVowel = new HashMap<Integer, String>();
    HashMap<Integer, String> mapFinal = new HashMap<Integer, String>();
    HashMap<Integer, String> mapTone = new HashMap<Integer, String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showText = (TextView) findViewById(R.id.textView);
        putMap();

    //vowel button
        Button vowel0 = (Button) findViewById(R.id.vowel0);
        vowel0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(1);
                int k = 1;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else addNewVowel(d, k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel1 = (Button) findViewById(R.id.vowel1);
        vowel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(3);
                int k=3;
                boolean s = chectDelay();
                if (s) addOldVowel(d,k);
                else addNewVowel(d,k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel2 = (Button) findViewById(R.id.vowel2);
        vowel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(5);
                int k=5;
                boolean s = chectDelay();
                if (s) addOldVowel(d,k);
                else addNewVowel(d,k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel3 = (Button) findViewById(R.id.vowel3);
        vowel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(7);
                int k=7;
                boolean s = chectDelay();
                if (s) addOldVowel(d,k);
                else addNewVowel(d,k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel4 = (Button) findViewById(R.id.vowel4);
        vowel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(9);
                int k=9;
                boolean s = chectDelay();
                if (s) addOldVowel(d,k);
                else addNewVowel(d,k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel5 = (Button) findViewById(R.id.vowel5);
        vowel5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(11);
                int k=11;
                boolean s = chectDelay();
                if (s) addOldVowel(d,k);
                else addNewVowel(d,k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel6 = (Button) findViewById(R.id.vowel6);
        vowel6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(13);
                int k=13;
                boolean s = chectDelay();
                if (s) addOldVowel(d,k);
                else addNewVowel(d,k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel7 = (Button) findViewById(R.id.vowel7);
        vowel7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(15);
                int k=15;
                boolean s = chectDelay();
                if (s) addOldVowel(d,k);
                else addNewVowel(d,k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel8 = (Button) findViewById(R.id.vowel8);
        vowel8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(17);
                int k=17;
                boolean s = chectDelay();
                if (s) addOldVowel(d,k);
                else addNewVowel(d,k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel9 = (Button) findViewById(R.id.vowel9);
        vowel9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(19);
                int k=19;
                boolean s = chectDelay();
                if (s) addOldVowel(d,k);
                else addNewVowel(d,k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel10 = (Button) findViewById(R.id.vowel10);
        vowel10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(21);
                int k=21;
                boolean s = chectDelay();
                if (s) addOldVowel(d,k);
                else addNewVowel(d,k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button vowel11 = (Button) findViewById(R.id.vowel11);
        vowel11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapVowel.get(23);
                int k=23;
                boolean s = chectDelay();
                if (s) addOldVowel(d, k);
                else addNewVowel(d, k);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });

        //change vowel button
        Button shortTop = (Button) findViewById(R.id.shorttop);
        shortTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean s = chectDelay();
                if (s) changeOldVowel();
                else changeBefore();
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button shortRight = (Button) findViewById(R.id.shortright);
        shortRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean s = chectDelay();
                if (s) changeOldVowel();
                else changeBefore();
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });

        //alp button
        Button alp0 = (Button) findViewById(R.id.alp0);
        alp0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(0);
                boolean s = chectDelay();
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
                String d = mapAlp.get(1);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp2 = (Button) findViewById(R.id.alp2);
        alp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(2);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp3 = (Button) findViewById(R.id.alp3);
        alp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(3);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp4 = (Button) findViewById(R.id.alp4);
        alp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(4);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp5 = (Button) findViewById(R.id.alp5);
        alp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(5);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp6 = (Button) findViewById(R.id.alp6);
        alp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(6);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp7 = (Button) findViewById(R.id.alp7);
        alp7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(7);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });

        Button alp8 = (Button) findViewById(R.id.alp8);
        alp8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(8);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp9 = (Button) findViewById(R.id.alp9);
        alp9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(9);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp10 = (Button) findViewById(R.id.alp10);
        alp10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(10);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp11 = (Button) findViewById(R.id.alp11);
        alp11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(11);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp12 = (Button) findViewById(R.id.alp12);
        alp12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(12);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp13 = (Button) findViewById(R.id.alp13);
        alp13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(13);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp14 = (Button) findViewById(R.id.alp14);
        alp14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(14);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp15 = (Button) findViewById(R.id.alp15);
        alp15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(15);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp16 = (Button) findViewById(R.id.alp16);
        alp16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(16);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp17 = (Button) findViewById(R.id.alp17);
        alp17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(17);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp18 = (Button) findViewById(R.id.alp18);
        alp18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(18);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button alp19 = (Button) findViewById(R.id.alp19);
        alp19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapAlp.get(19);
                boolean s = chectDelay();
                if (s) addOldAlp(d);
                else addNewAlp(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });

        //tone button
        Button tone0 = (Button) findViewById(R.id.tone0);
        tone0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapTone.get(0);
                boolean s = chectDelay();
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
                String d = mapTone.get(1);
                boolean s = chectDelay();
                if (s) addOldTone(d);
                else addNewTone(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button tone2 = (Button) findViewById(R.id.tone2);
        tone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapTone.get(2);
                boolean s = chectDelay();
                if (s) addOldTone(d);
                else addNewTone(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button tone3 = (Button) findViewById(R.id.tone3);
        tone3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapTone.get(3);
                boolean s = chectDelay();
                if (s) addOldTone(d);
                else addNewTone(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });

        //final button
        Button final0 = (Button) findViewById(R.id.final0);
        final0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapFinal.get(0);
                boolean s = chectDelay();
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
                String d = mapFinal.get(1);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else addNewFinal(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final2 = (Button) findViewById(R.id.final2);
        final2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapFinal.get(2);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else addNewFinal(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final3 = (Button) findViewById(R.id.final3);
        final3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapFinal.get(3);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else addNewFinal(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final4 = (Button) findViewById(R.id.final4);
        final4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapFinal.get(4);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else addNewFinal(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final5 = (Button) findViewById(R.id.final5);
        final5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapFinal.get(5);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else addNewFinal(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final6 = (Button) findViewById(R.id.final6);
        final6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapFinal.get(6);
                boolean s = chectDelay();
                if (s) addOldFinal(d);
                else addNewFinal(d);
                String t = "" + data.getAlp() + " " + data.getVowel() + " " + data.getFin() + " " + data.getTone();
                showText.setText(t);
            }
        });
        Button final7 = (Button) findViewById(R.id.final7);
        final7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String d = mapFinal.get(7);
                boolean s = chectDelay();
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
    protected void addOldVowel(String dd,int j) {
        data.setSk(j);
        data.setVowel(mapVowel.get(data.getSk()));
    }

    protected void addNewVowel(String dd,int j) {
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

    protected  void changeOldVowel(){
        data.setVowel(mapVowel.get(data.getSk() - 1));
    }
    protected  void changeBefore(){
        data=new Stroke();
        data.setBk(1);
    }

    protected  void putMap(){
        //put Alp
        mapAlp.put(0,"จ");
        mapAlp.put(1,"ช");
        mapAlp.put(2,"ฮ");
        mapAlp.put(3,"ด");
        mapAlp.put(4,"ซ");
        mapAlp.put(5,"น");
        mapAlp.put(6,"ค");
        mapAlp.put(7,"ท");
        mapAlp.put(8,"ก");
        mapAlp.put(9,"ป");
        mapAlp.put(10,"ต");
        mapAlp.put(11,"พ");
        mapAlp.put(12,"ม");
        mapAlp.put(13,"ร");
        mapAlp.put(14,"ล");
        mapAlp.put(15,"ย");
        mapAlp.put(16,"ง");
        mapAlp.put(17,"ว");
        mapAlp.put(18,"บ");
        mapAlp.put(19,"ฟ");
        //put vowel
        mapVowel.put(0,"เอะ");
        mapVowel.put(1,"เอ");
        mapVowel.put(2,"แอะ");
        mapVowel.put(3,"แอ");
        mapVowel.put(4,"อัวะ");
        mapVowel.put(5,"อัว");
        mapVowel.put(6,"อึ");
        mapVowel.put(7,"อือ");
        mapVowel.put(8,"อะ");
        mapVowel.put(9,"อา");
        mapVowel.put(10,"อิ");
        mapVowel.put(11,"อี");
        mapVowel.put(12,"อุ");
        mapVowel.put(13,"อู");
        mapVowel.put(14,"โอะ");
        mapVowel.put(15,"โอ");
        mapVowel.put(16,"เอาะ");
        mapVowel.put(17,"ออ");
        mapVowel.put(18,"เอียะ");
        mapVowel.put(19,"เอีย");
        mapVowel.put(20,"เออะ");
        mapVowel.put(21,"เออ");
        mapVowel.put(22,"เอือะ");
        mapVowel.put(23,"เอือ");
        //put final
        mapFinal.put(0,"กม");
        mapFinal.put(1,"กน");
        mapFinal.put(2,"กง");
        mapFinal.put(3,"กบ");
        mapFinal.put(4,"กก");
        mapFinal.put(5,"เกย");
        mapFinal.put(6,"กด");
        mapFinal.put(7,"เกอว");
        //put tone
        mapTone.put(0,"เอก");
        mapTone.put(1,"โท");
        mapTone.put(2,"ตรี");
        mapTone.put(3,"จัตวา");
    }


}
