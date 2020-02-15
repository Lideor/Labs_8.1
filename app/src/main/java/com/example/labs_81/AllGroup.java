package com.example.labs_81;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.labs_81.MainActivity.LOG_TAG;

public class AllGroup {

     private List<ListGroup> listGroup = new ArrayList<ListGroup>();

     //группа по названию
    public ListGroup getGroup(String numberGroup){
        for (ListGroup key : listGroup) {
            if(key.getNumber().equals(numberGroup)) return key;
        }
        return null;
    }

    //преобразование название групп в строку
    public List<String> getListGroup(){

        List<String> listNameGroup = new ArrayList<String>();
        for (ListGroup key : listGroup) {
            Log.v(LOG_TAG,key.getNumber());
            listNameGroup.add(key.getNumber());
        }
        return listNameGroup;
    }

}
