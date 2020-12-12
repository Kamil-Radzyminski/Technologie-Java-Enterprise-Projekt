package com.kamilradzyminski.projekt.dto;

import com.kamilradzyminski.projekt.domain.enums.PropertyType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SearchRequest {
    PropertyType propertyType;
    String value;

}
