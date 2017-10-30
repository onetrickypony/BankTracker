package com.example.onetrickpony.banktracker.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by onetrickpony on 05.09.2017.
 */

public class Card {

    private String type;
    private String number;
    private float plusSum;
    private float minusSum;

    public String getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public float getPlusSum() {
        return plusSum;
    }

    public float getMinusSum() {
        return minusSum;
    }

    public float getResSum() {
        return plusSum - minusSum;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPlusSum(float plusSum) {
        this.plusSum = plusSum;
    }

    public void setMinusSum(float minusSum) {
        this.minusSum = minusSum;
    }
}
