package com.victornsto.restwithspringbootandjava.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.email")
@Getter
@Setter
@RequiredArgsConstructor
public class EmailConfig {
    private String host;
    private String port;
    private String username;
    private String password;
    private String from;
    private boolean ssl;
}
