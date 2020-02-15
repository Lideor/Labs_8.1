package com.example.labs_81;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


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
    private final String listGroup = "Group.json"; // Файл расписания
    private final String listData = "Data.json"; // Файл базы


    //выгрузка из файла всех групп
    public AllGroup importGroupJsonInFile(Context ctn){

        String jsonString = convertStreamToString(ctn,listGroup);// переводим значения файла срасписанием в строоку

        Gson gson = new GsonBuilder().create(); // конвертор json от гугла

        AllGroup data = null;

        try {
             data = gson.fromJson(jsonString, AllGroup.class);//десерелизуем полученную строку по образу объекта
        } catch (ArithmeticException e) {
             Log.d(LOG_TAG,e.toString());
        }

        return data;

    }

    //выгрузка из файла базы данных
    public DataBase importDataJsonInFile(Context ctn){

        String jsonString = convertStreamToString(ctn,listData);// переводим значения файла срасписанием в строоку
        Gson gson = new GsonBuilder().create();

        DataBase data = null;

        try {
            data = gson.fromJson(jsonString, DataBase.class);//десерелизуем полученную строку по образу объекта
        } catch (ArithmeticException e) {
            Log.d(LOG_TAG,e.toString());
        }

        return data;
    }

    //чтение файла из папки ассетов
    private String convertStreamToString(Context ctn, String fileName) {

        String line = null;
        BufferedReader reader;

        String out = null;
        StringBuilder sb = null;

        try {

            AssetManager assetManager = ctn.getAssets();
            reader = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            sb = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(sb!=null) out = sb.toString();
        return out;
    }
}

