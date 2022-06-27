package com.jojoldu.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 외부 WAS 를 사용하지 않고 내부 WAS를 실행하겠다.
        SpringApplication.run(Application.class, args);

    }
}
