package com.example.onetrickpony.banktracker.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by onetrickpony on 05.09.2017.
 */

public class Card {

    private String type;
    private String number;
    private int plusSum;
    private int minusSum;

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public int getPlusSum() {
        return plusSum;
    }

    public int getMinusSum() {
        return minusSum;
    }

    public int getResSum() {
        return plusSum - minusSum;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPlusSum(int plusSum) {
        this.plusSum = plusSum;
    }

    public void setMinusSum(int minusSum) {
        this.minusSum = minusSum;
    }
}
