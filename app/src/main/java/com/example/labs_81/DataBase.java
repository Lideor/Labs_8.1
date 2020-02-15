package com.example.labs_81;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    @JsonProperty("Week")
    private List<String> Week = new ArrayList<String>();// массив хранящий список названий недели

    @JsonProperty("Type")
    private List<String> Type = new ArrayList<String>();// массив хранящий список типов занятий


    @JsonProperty("Call")
    private List<Windows> Call = new ArrayList<Windows>();// массив хранящий список звонков

    public Windows getCall(int number){
        return Call.get(number);
    }

    public String getType(int number){
        return Type.get(number);
    }

    public String getWeek(int number){
        return Week.get(number);
    }
}
