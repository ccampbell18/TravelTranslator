package com.example.charl.traveltranslator;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.IOException;

public class MainActivitySpanish extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String phrases[] = getResources().getStringArray(R.array.main_menu);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_spanish);

        DatabaseHelper myDbHelper = new DatabaseHelper(this);

        try {

            myDbHelper.createDatabase();

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }

        try {

            myDbHelper.openDatabase();

        }catch(SQLException sqle){

            throw sqle;

        }


        ImageView imgLogo = (ImageView)findViewById(R.id.imgLogo);
        imgLogo.setImageResource(R.drawable.logo);



        final ListView lv =(ListView)findViewById(R.id.lstContent);
        ListAdapter la = new ArrayAdapter<String>(this, R.layout.custom_text, phrases);

        lv.setAdapter(la);
        lv.setTextFilterEnabled(true);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                Intent intent = new Intent(getBaseContext(), DisplayTranslationSpanish.class);
                intent.putExtra("query", (String)lv.getAdapter().getItem(position));
                startActivity(intent);

            }

        });




    }



}

