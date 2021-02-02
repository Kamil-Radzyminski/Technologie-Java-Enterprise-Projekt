package com.kamilradzyminski.projekt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Application {

    private Long id;
    private String appName;
    private String domainName;

}
