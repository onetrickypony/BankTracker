package com.example.onetrickpony.banktracker.model.data;

/**
 * Created by onetrickpony on 06.10.2017.
 */

public class PaymentType {
    private String paymentType;
    private int sum;

    public String getPaymentType() {
        return paymentType;
    }

    public int getSum() {
        return sum;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}