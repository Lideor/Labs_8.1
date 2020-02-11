package com.example.labs_81;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity  {

    private SharedPreferences sPref;// файл с настройками
    private int numberGroup;// текущая группа
    public static String LOG_TAG = "mylogs";

    private MenuItem editMenuItem;
    private MenuItem removeMenuItem;

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

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("1 неделя"));
        tabs.addTab(tabs.newTab().setText("2 неделя"));


        int n = 0;
        try {

        }  catch (ArithmeticException e) {
            Log.d(LOG_TAG,e.toString());
        }
        Log.d(LOG_TAG,n+" надеюсь это сраотает ");



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
        editMenuItem = menu.findItem(R.id.action_edit);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.equals(editMenuItem)) {
            Intent intent = new Intent(MainActivity.this, ChoiceGroup.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
