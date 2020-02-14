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
    public String numberGroup;// текущая группа TODO:периписать на get
    public static String LOG_TAG = "mylogs";

    private TabLayout myTab;
    private ViewPager myView;
    private FragmentsAdapter fAdapter;

    private MenuItem editMenuItem;
    private MenuItem removeMenuItem;

    private AllGroup group;
    private DataBase data;
    Toolbar mActionBarToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myTab = (TabLayout) findViewById(R.id.tabs);
        myView = (ViewPager) findViewById(R.id.viewpager);
        mActionBarToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        if (getNumberGroup()) {
            mActionBarToolbar.setTitle(numberGroup);
            setContent();
        } else {
            mActionBarToolbar.setTitle("Выберите группу");
        }
        setSupportActionBar(mActionBarToolbar);


        int n = 0;
        try {

        } catch (ArithmeticException e) {
            Log.d(LOG_TAG, e.toString());
        }
        Log.d(LOG_TAG, n + " надеюсь это сраотает ");


    }

    private void setContent() {
        fAdapter = new FragmentsAdapter(getSupportFragmentManager());
        fAdapter.AddFragement(new FragmentMain(1, this));
        fAdapter.AddFragement(new FragmentMain(2, this));
        myView.setAdapter(fAdapter);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int dayOfWeek = c.get(Calendar.WEEK_OF_YEAR);
        myTab.setupWithViewPager(myView);
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

    private boolean getNumberGroup() {
        sPref = getSharedPreferences("prefs", MODE_PRIVATE);
        numberGroup = sPref.getString("numberGroup", "-1");
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
