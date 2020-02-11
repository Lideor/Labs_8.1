package com.example.labs_81;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static com.example.labs_81.MainActivity.LOG_TAG;

public class ChoiceGroup extends AppCompatActivity {

    private AllGroup group;// список всех групп
    private DataBase data;// дополнительная информация
    List<String> listNameGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_group);
        group = new JsonParse().importGroupJsonInFile(this);
        listNameGroup = group.getListGroup();
        Log.v(LOG_TAG,""+listNameGroup.size());
    }
}
