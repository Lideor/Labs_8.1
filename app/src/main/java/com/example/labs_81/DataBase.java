package com.example.labs_81;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    @JsonProperty("Week")
    private List<String> Week = new ArrayList<String>();

    @JsonProperty("Type")
    private List<String> Type = new ArrayList<String>();


    @JsonProperty("Call")
    private List<Windows> Call = new ArrayList<Windows>();

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
