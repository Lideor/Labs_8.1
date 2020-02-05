package com.example.labs_81;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListGroup {

    private int numberGroup;
    private List<Map<String, ListDay>> dayList= new ArrayList();

    ListGroup(int numberGroup){
        for(int i = 0; i < 2;i++){
            dayList.add(new HashMap<String, ListDay>());
        }
    }

    public void addDay(int numberWeek, String nameDay, ListDay day){
        dayList.get(numberWeek).put(nameDay,day);
    }

    public ListDay getDay(int numberWeek, String nameDay){
        return dayList.get(numberWeek).get(nameDay);
    }

}
