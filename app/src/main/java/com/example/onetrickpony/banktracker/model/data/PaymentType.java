package com.example.onetrickpony.banktracker.model.data;

/**
 * Created by onetrickpony on 06.10.2017.
 */

public class PaymentType {
    private String paymentType;
    private String cName;

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcName() {

        return cName;
    }

    private float sum;

    public String getPaymentType() {
        return paymentType;
    }

    public float getSum() {
        return sum;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}