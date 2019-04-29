package com.webmodels;

import com.webmodels.data.CeneoProductCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired
    CeneoProductCollector ceneoProductCollector;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        ceneoProductCollector.createProductListFromSymbol("dmc-gx80");

//        ceneoProductCollector.collectData();
    }
}
