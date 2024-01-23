package com.kalayciburak.corepackage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CorePackageApplication {

    public static void main(String[] args) {
        SpringApplication.run(CorePackageApplication.class, args);
    }

}
