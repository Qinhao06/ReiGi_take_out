package com.qh.reigi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableCaching
public class ReiGiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReiGiApplication.class, args);

    }

}
