package com.example.labs_81;

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

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sPref;// файл с настройками
    private int numberGroup;// текущая группа
    private String LOG_TAG = "mylogs";

    private AllGroup group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        group = new JsonParse().importJsonInFile(this);
        int n = 0;
        try {
      //      n = group.listGroup.get("405").getDay(1, "Mn").getLesson(4).getType();
        }  catch (ArithmeticException e) {
            Log.d(LOG_TAG,e.toString());
        }
        Log.d(LOG_TAG,n+" надеюсь это сраотает ");
        if(getNumberGroup()){

        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private boolean getNumberGroup() {
        sPref = getSharedPreferences("prefs",MODE_PRIVATE);
        numberGroup = sPref.getInt("numberGroup", -1);
        if (numberGroup == -1) return false;
        else return true;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
