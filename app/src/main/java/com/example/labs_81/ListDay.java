package com.example.labs_81;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListDay {

    private int start=0;
    private Map<Integer, Lesson> map = new HashMap<Integer, Lesson>();

    public Lesson getLesson(int timeStart){
        return map.get(timeStart);
    }

    public void addLesson(int timeStart, Lesson lesson){
        map.put(timeStart,lesson);
        if(timeStart<start) start=timeStart;
    }

    public int getStart(){
        return start;
    }

    public int getSize(){
        return map.size();
    }

}
