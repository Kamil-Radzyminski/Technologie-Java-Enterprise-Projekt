package com.kamilradzyminski.projekt.dto;

import com.kamilradzyminski.projekt.domain.enums.PropertyType;

public class SearchRequest {
    PropertyType propertyType;
    String value;

    public SearchRequest() {
    }

    public SearchRequest(PropertyType propertyType, String value) {
        this.propertyType = propertyType;
        this.value = value;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
