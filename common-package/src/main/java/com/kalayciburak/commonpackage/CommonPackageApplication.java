package com.kalayciburak.commonpackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CommonPackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonPackageApplication.class, args);
    }

}
