package com.example.labs_81;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Windows {
    @JsonProperty("Start")

    private blockTime Start;

    @JsonProperty("End")
    private blockTime End;

    public blockTime getStart(){
        return Start;
    }

    public blockTime getEnd(){
        return End;
    }

    class blockTime{
        @JsonProperty("H")
        int H;
        @JsonProperty("M")
        int M;

        @Override
        public String toString(){
            return String.format("%02d", H)+":"+ String.format("%02d", M);
        }
    }
}