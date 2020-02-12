package com.example.labs_81;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.labs_81.MainActivity.LOG_TAG;

public class ChoiceGroup extends AppCompatActivity {
    private SharedPreferences sPref;// файл с настройками

    private AllGroup group;// список всех групп
    private DataBase data;// дополнительная информация
    List<String> listNameGroup;
    private MenuItem editMenuItem;
    private MenuItem removeMenuItem;
    private LinearLayout linearLayout;
    private String numberGroup;// текущая группа

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_group);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        getNumberGroup();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);



        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mActionBarToolbar.setTitle(R.string.action_settings);
        setSupportActionBar(mActionBarToolbar);


        group = new JsonParse().importGroupJsonInFile(this);



        listNameGroup = group.getListGroup();
        RVAdapterChoice adapter = new RVAdapterChoice(listNameGroup,numberGroup,this);
        rv.setAdapter(adapter);
        rv.setOnClickListener(new View.OnClickListener() {

    @Override
    public void onClick(View view) {
        Log.v(LOG_TAG,"uuuuuuuu");


        //or you can use For loop if you have long list of items. Use its length or size of the list as



    }
});
    Log.v(LOG_TAG,""+listNameGroup.size());

    }

    private boolean getNumberGroup() {
        sPref = getSharedPreferences("prefs",MODE_PRIVATE);
        numberGroup = sPref.getString("numberGroup", "-1");
        if (numberGroup.equals("-1")) return false;
        else return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        editMenuItem = menu.findItem(R.id.action_edit);
        editMenuItem.setVisible(false);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
