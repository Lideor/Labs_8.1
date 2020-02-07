package com.example.labs_81;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Day {
    @JsonProperty("Day")
    private int Day;

    @JsonProperty("Lesson")
    private List<Lesson> Lesson = new ArrayList<Lesson>();
}