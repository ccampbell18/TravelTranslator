package com.example.charl.traveltranslator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Main_Menu extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String choices[] = getResources().getStringArray(R.array.main_lang);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        ImageView imgLogo = (ImageView)findViewById(R.id.imgLogo);
        imgLogo.setImageResource(R.drawable.logo);


        final ListView lv =(ListView)findViewById(R.id.lstContent);
        ListAdapter la = new ArrayAdapter<String>(this, R.layout.custom_text_menu, choices);

        lv.setAdapter(la);
        lv.setTextFilterEnabled(true);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                switch( position ){

                    case 0: Intent newActivity0 = new Intent(Main_Menu.this, MainActivitySpanish.class);
                            startActivity(newActivity0);
                            break;

                    case 1: Intent newActivity1 = new Intent(Main_Menu.this, MainActivityFrench.class);
                        startActivity(newActivity1);
                        break;

                }

            }

        });

    }


}
