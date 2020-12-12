package com.kamilradzyminski.projekt.dto;

public class StatisticsResponse {
    String creditCardType;
    int count;

    public StatisticsResponse() {
    }

    public StatisticsResponse(String creditCardType, int count) {
        this.creditCardType = creditCardType;
        this.count = count;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
