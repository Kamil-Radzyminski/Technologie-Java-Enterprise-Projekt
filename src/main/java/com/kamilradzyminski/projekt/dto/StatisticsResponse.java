package com.kamilradzyminski.projekt.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StatisticsResponse {
    String creditCardType;
    int count;

}
