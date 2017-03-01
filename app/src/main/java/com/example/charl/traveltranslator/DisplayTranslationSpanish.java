package com.example.charl.traveltranslator;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class DisplayTranslationSpanish extends Activity {

    String english;
    String spanish;
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_translation_spanish);


        DatabaseHelper db = new DatabaseHelper(this);

        db.openDatabase();

        ImageView imgLogo = (ImageView)findViewById(R.id.imgLogo);
        imgLogo.setImageResource(R.drawable.logo);

        Bundle bundle = getIntent().getExtras();
        english = bundle.getString("query");
        spanish = db.getSingleSpanishEntry(english);


        TextView textEnglish = (TextView) findViewById(R.id.txtEnglish);
        TextView textSpanish = (TextView) findViewById(R.id.txtSpanish);

        textEnglish.setText(english);
        textSpanish.setText(spanish);



    }
    public void onClick(View v){

        playSpanish();

    }

    private void playSpanish() {

        tts=new TextToSpeech(DisplayTranslationSpanish.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    Locale locSpanish = new Locale("es", "MEX");
                    int result=tts.setLanguage(locSpanish);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
                    }
                    else{

                        tts.speak(spanish, TextToSpeech.QUEUE_FLUSH,null);
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });
    }


}
