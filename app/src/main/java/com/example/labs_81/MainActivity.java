package com.example.labs_81;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends Activity {

    private SharedPreferences sPref;// файл с настройками
    private int numberGroup;// текущая группа
    public static String LOG_TAG = "mylogs";



    private AllGroup group;
    private DataBase data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mActionBarToolbar);

        group = new JsonParse().importGroupJsonInFile(this);
        data = new JsonParse().importDataJsonInFile(this);

        int n = 0;
        try {

        }  catch (ArithmeticException e) {
            Log.d(LOG_TAG,e.toString());
        }
        Log.d(LOG_TAG,n+" надеюсь это сраотает ");
        if(getNumberGroup()){

        }


    }

    private boolean getNumberGroup() {
        sPref = getSharedPreferences("prefs",MODE_PRIVATE);
        numberGroup = sPref.getInt("numberGroup", -1);
        if (numberGroup == -1) return false;
        else return true;

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
