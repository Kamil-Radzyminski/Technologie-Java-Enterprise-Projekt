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


    public Application(Long id, String appName, String domainName) {
        this.id = id;
        this.appName = appName;
        this.domainName = domainName;
    }

    public Application(String appName, String domainName) {
        this.appName = appName;
        this.domainName = domainName;
    }
}
