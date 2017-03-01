package com.example.charl.traveltranslator;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class DisplayTranslationFrench extends Activity {

    String english;
    String french;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_translation_french);


        DatabaseHelper db = new DatabaseHelper(this);

        db.openDatabase();

        ImageView imgLogo = (ImageView)findViewById(R.id.imgLogo);
        imgLogo.setImageResource(R.drawable.logo);

        Bundle bundle = getIntent().getExtras();
        english = bundle.getString("query");
        french = db.getSingleFrenchEntry(english);


        TextView textEnglish = (TextView) findViewById(R.id.txtEnglish);
        TextView textFrench = (TextView) findViewById(R.id.txtFrench);

        textEnglish.setText(english);
        textFrench.setText(french);



    }
    public void onClick(View v){

        playFrench();

    }

    private void playFrench() {

        tts=new TextToSpeech(DisplayTranslationFrench.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    Locale locFrench = new Locale("fr", "CA");
                    int result=tts.setLanguage(locFrench);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{

                        tts.speak(french, TextToSpeech.QUEUE_FLUSH,null);
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });
    }


}
