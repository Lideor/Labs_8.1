package com.example.labs_81;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    @JsonProperty("Week")
    private List<String> Week = new ArrayList<String>();

    @JsonProperty("Type")
    private List<String> Type = new ArrayList<String>();
}
