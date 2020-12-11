package com.kamilradzyminski.projekt.dto;

import com.kamilradzyminski.projekt.domain.enums.CreditCardType;

public class StatisticsResponse {
    CreditCardType creditCardType;
    int count;

    public StatisticsResponse() {
    }

    public StatisticsResponse(CreditCardType creditCardType, int count) {
        this.creditCardType = creditCardType;
        this.count = count;
    }

    public CreditCardType getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(CreditCardType creditCardType) {
        this.creditCardType = creditCardType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
