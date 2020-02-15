package com.example.labs_81;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListDay {

    @JsonProperty("Number")
    private int Number;

    @JsonProperty("Days")
    private List<Day> Days = new ArrayList<Day>();

    public int getSize(){
        return Days.size();
    }
    public int getNumber(){
        return Number;
    }
    public Day getDay(int number){
        return Days.get(number);
    }

}
