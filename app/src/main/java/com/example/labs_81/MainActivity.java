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
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sPref;// файл с настройками
    private String numberGroup;// текущая группа
    public static String LOG_TAG = "mylogs";

    private TabLayout myTab; // таб выбора номера недели
    private ViewPager myView;//содержимое таба
    private FragmentsAdapter fAdapter;//адаптер содержимого таба

    private MenuItem editMenuItem;//меню тулбара

    private Toolbar mActionBarToolbar;//сам тулбар

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //инициаллищация элемнтов из лайаута
        myTab = (TabLayout) findViewById(R.id.tabs);
        myView = (ViewPager) findViewById(R.id.viewpager);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);

        //проверка надичия выбора группы, в случае наличия вызываем метод иницилизирующий табы
        if (getNumberGroup()) {
            mActionBarToolbar.setTitle(numberGroup);
            setContent();
        } else {
            mActionBarToolbar.setTitle("Выберите группу");//текст тулбара
        }

        setSupportActionBar(mActionBarToolbar);//заставляем тулбар выполнять функции актионбара

    }

    private void setContent() {

        fAdapter = new FragmentsAdapter(getSupportFragmentManager());// создаем новый адаптер фрагментов
        //добавляем фрагменты перывой и второй недели
        fAdapter.AddFragement(new FragmentMain(1, this));
        fAdapter.AddFragement(new FragmentMain(2, this));
        myView.setAdapter(fAdapter);//устанавливаем выбранный адаптер для содердимого табов
        myTab.setupWithViewPager(myView);//устанавливаем полученное содержимое для таба


        //получаем текущий номер недели
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.WEEK_OF_YEAR);

        // в зависимости от четности нелеои делаем ее активной
        if (dayOfWeek % 2 != 1) {
            myTab.getTabAt(0).setText("1 неделя •");
            myTab.getTabAt(1).setText("2 неделя");
            myTab.setScrollPosition(0, 0f, true);
            myView.setCurrentItem(0);
        } else {
            myTab.getTabAt(0).setText("1 неделя");
            myTab.getTabAt(1).setText("2 неделя •");
            myTab.setScrollPosition(1, 0f, true);
            myView.setCurrentItem(1);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (getNumberGroup()) {
            mActionBarToolbar.setTitle(numberGroup);
            setContent();
        } else {
            mActionBarToolbar.setTitle("Выберите группу");
        }
        setSupportActionBar(mActionBarToolbar);
    }

    //метод определяющий текущую заданную группу, возращает false если группа не задана
    private boolean getNumberGroup() {
        sPref = getSharedPreferences("prefs", MODE_PRIVATE); //доступ к настройкам с имененм prefs
        numberGroup = sPref.getString("numberGroup", "-1");// получаем значение хранящиеся под именем "numberGroup"
        // в случае отсутсвия данного значение веренет "-1"
        if (numberGroup.equals("-1")) return false;
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
