package com.kalayciburak.inventoryservice;

import com.kalayciburak.corepackage.util.constant.Paths;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = Paths.ConfigurationBasePackage)
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

}
