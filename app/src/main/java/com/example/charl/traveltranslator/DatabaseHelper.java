package com.example.charl.traveltranslator;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by charl on 2/20/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{



    private static String DB_PATH = "/data/data/com.example.charl.traveltranslator/databases/";

    private static String DB_NAME = "final_project.db";

    private SQLiteDatabase myDatabase;

    private final Context myContext;




    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }


    public void createDatabase() throws IOException {

        boolean dbExist = checkDatabase();

        if(dbExist){

        }else{


            this.getReadableDatabase();

            try {

                copyDatabase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }


    private boolean checkDatabase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){

            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }


    private void copyDatabase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDatabase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    public String getSingleSpanishEntry(String english)
    {
        Cursor cursor=myDatabase.query("CONTENT", null, " ENGLISH=?", new String[]{english}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String spanish= cursor.getString(cursor.getColumnIndex("spanish"));
        cursor.close();
        return spanish;
    }

    public String getSingleFrenchEntry(String english)
    {
        Cursor cursor=myDatabase.query("CONTENT", null, " ENGLISH=?", new String[]{english}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String french= cursor.getString(cursor.getColumnIndex("french"));
        cursor.close();
        return french;
    }

    @Override
    public synchronized void close() {

        if(myDatabase != null)
            myDatabase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



}