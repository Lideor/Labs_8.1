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
    private List<String> listNameGroup;//список названий всех групп

    private MenuItem editMenuItem;//кнопка перехода выбора группы

    private String numberGroup;// текущая группа

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_group);

        //создание бара
        Toolbar mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mActionBarToolbar.setTitle(R.string.action_settings); //Задаем текст бара
        setSupportActionBar(mActionBarToolbar);

        //добавление кнопки возращения назад
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getNumberGroup();

        //доступ к recieview, в котором будет распологаться основной основной список контент
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(this);// создаем менджер отвечающий за расположение объектов
        // в recieview
        rv.setLayoutManager(llm); // устанавливаем созданный менждер

        group = new JsonParse().importGroupJsonInFile(this); // получаем информацию о всех группах
        listNameGroup = group.getListGroup();// переводим название групп в виде массива строк

        RVAdapterChoice adapter = new RVAdapterChoice(listNameGroup,numberGroup,this);/* создаем аддаптер, отвечающий за создание
        элементов контента из масиива данных и передача их спискок контнета */
        rv.setAdapter(adapter); //устанавливаем только что созданный адаптер для основного списком контента


    }


    //метод определяющий текущую заданную группу, возращает false если группа не задана
    private boolean getNumberGroup() {
        sPref = getSharedPreferences("prefs",MODE_PRIVATE); //доступ к настройкам с имененм prefs
        numberGroup = sPref.getString("numberGroup", "-1");// получаем значение хранящиеся под именем "numberGroup"
        // в случае отсутсвия данного значение веренет "-1"
        if (numberGroup.equals("-1")) return false;
        else return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        editMenuItem = menu.findItem(R.id.action_edit);

        editMenuItem.setVisible(false);//делаем кнопку перехода в данную активити неотображаемой


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()== android.R.id.home) {
            finish();//заверщаем текущую активити при нажатие клавиши
        }
        return super.onOptionsItemSelected(item);
    }

}
