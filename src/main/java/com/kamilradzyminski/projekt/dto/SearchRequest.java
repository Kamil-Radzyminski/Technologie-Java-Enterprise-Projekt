package com.kamilradzyminski.projekt.dto;

import com.kamilradzyminski.projekt.dto.types.PropertyType;
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
