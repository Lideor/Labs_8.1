package com.example.labs_81;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ListGroup {

    @JsonProperty("Number")
    private String Number;
    @JsonProperty("Week")
    private List<ListDay> Week = new ArrayList<ListDay>();

    public String getNumber(){
        return Number;
    }

  /*  public void addDay(int numberWeek, String nameDay, ListDay day){
        dayList.get(numberWeek).put(nameDay,day);
    }

    public ListDay getDay(int numberWeek, String nameDay){
        return dayList.get(numberWeek).get(nameDay);
    }
*/
}
