package com.example.labs_81;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.labs_81.MainActivity.LOG_TAG;


public class ListGroup {

    @JsonProperty("Number")
    private String Number;
    @JsonProperty("Week")
    private List<ListDay> Week = new ArrayList<ListDay>();

    public String getNumber(){
        return Number;
    }

    public ListDay getWeek(int number){
        for (ListDay key : Week) {
            if(number==key.getNumber()) return key;
        }
        return null;
    }

}
