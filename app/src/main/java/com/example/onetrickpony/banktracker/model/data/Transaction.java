package com.example.onetrickpony.banktracker.model.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by onetrickpony on 05.09.2017.
 */

public class Transaction implements Serializable {
    private int mins;
    private int hours;
    private int year;
    private int month;
    private int day;

    @SerializedName("Sum")
    private float sum;

    @SerializedName("Direction")
    private boolean direction; // true - to, false - from

    @SerializedName("CardNumber")
    private String cardNumber;

    @SerializedName("CardType")
    private String cardType;

    @SerializedName("CompanyName")
    private String companyName;

    @SerializedName("PaymentType")
    private String paymentType;

    @SerializedName("bankName")
    private String bankName;

    public String getDate() {
        String date = new String(day + "." + month + "." + year);
        //String date = new String(dateTime.getDay() + "." + dateTime.getMonth() + "." + dateTime.getYear());
        return date;
    }

    public String getTime() {
        String time = new String(hours + ":" + mins);
        return time;
    }

    public float getSum() {
        return sum;
    }

    public boolean isDirection() {
        return direction;
    }
/*
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
*/
    public void setDateTime(String dateTime) {
        Pattern pattern = Pattern.compile("(\\d+).(\\d+).(\\d+) (\\d+):(\\d+)");

        if (pattern.matcher(dateTime).matches()) {
            Matcher matcher = pattern.matcher(dateTime);
            matcher.find();

            day = Integer.parseInt(matcher.group(1));
            month = Integer.parseInt(matcher.group(2));
            year = 2000 + Integer.parseInt(matcher.group(3));

            hours = Integer.parseInt(matcher.group(4));
            mins = Integer.parseInt(matcher.group(5));
        } else
        {
            // исключение(журнал)
        }
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public void setDirection(int direction) {
        if (direction == 0)
            this.direction = false;
        else
            this.direction = true;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setCompanyName(String companyName) {

        this.companyName = companyName;
    }

    public void setCardType(String cardType) {

        this.cardType = cardType;
    }

    public void setCardNumber(String cardNumber) {

        this.cardNumber = cardNumber;
    }

    public void setBankName(String bankName) {

        this.bankName = bankName;
    }

    public String getPaymentType() {

        return paymentType;
    }

    public String getCompanyName() {

        return companyName;
    }

    public String getCardType() {

        return cardType;
    }

    public String getCardNumber() {

        return cardNumber;
    }

    public String getBankName() {
        return bankName;
    }
}
