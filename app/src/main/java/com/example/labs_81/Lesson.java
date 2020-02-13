package com.example.labs_81;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lesson {

    @JsonProperty("Time")
    private int Time;

    @JsonProperty("Aud")
    private String Aud;

    @JsonProperty("Name")
    private String Name;

    @JsonProperty("Type")
    private int Type;

    @JsonProperty("Teach")
    private String Teach;



    public int getTime() {
        return Time;
    }

    public void setTime(int Time) {
        this.Time = Time;
    }


    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getAud() {
        return Aud;
    }

    public void setAud(String aud) {
        Aud = aud;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTeach() {
        return Teach;
    }

    public void setTeach(String teach) {
        Teach = teach;
    }

}
