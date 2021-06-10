package com.example.guestbookreal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GuestBookRealApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestBookRealApplication.class, args);
    }

}
