package com.example.labs_81;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

//import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Locale;


import static android.content.Context.MODE_PRIVATE;

public class JsonParse {
    private String LOG_TAG = "mylogs";
    private final String FILENAME = "Package.txt"; // имя файла

    //выгрузка из файла пакетов
    public String importJsonInFile(Context ctn){

//        Gson gson = new Gson();

        String jsonString="";

        try {
            // открываем поток для чтения
            File file = new File(ctn.getFilesDir(),FILENAME);
            FileInputStream fileReader = new FileInputStream(file);

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    ctn.openFileInput(FILENAME)));

            // читаем содержимое
            try {
                StringBuilder sb=  new StringBuilder();
                while ( (jsonString=br.readLine()) != null) {
                    sb.append(jsonString);
                }
                jsonString = sb.toString();
                br.close();
                fileReader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG,jsonString);
        return jsonString;
        //return true;
    }

}

