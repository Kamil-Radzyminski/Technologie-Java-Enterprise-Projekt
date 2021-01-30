package com.kamilradzyminski.projekt.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Application {

    private Long id;
    private String name;
    private String domain;


    public Application(Long id, String name, String domain) {
        this.id = id;
        this.name = name;
        this.domain = domain;
    }

    public Application(String name, String domain) {
        this.name = name;
        this.domain = domain;
    }
}
