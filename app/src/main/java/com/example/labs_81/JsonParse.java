package com.example.labs_81;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

    Context ctn;
    public final String listGroup = "Group.json"; // Файл расписания


    //выгрузка из файла пакетов
    private String importJsonInFile(String filename){

        Gson gson = new Gson();

        String jsonString="";

        try {
            AssetManager am = ctn.getAssets();
            InputStream is = am.open(filename);
            String s = convertStreamToString(is);
            is.close();
            return s;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(LOG_TAG,jsonString);
        return jsonString;
        //return true;
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}

