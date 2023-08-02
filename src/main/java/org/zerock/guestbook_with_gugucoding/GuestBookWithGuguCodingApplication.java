package org.zerock.guestbook_with_gugucoding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GuestBookWithGuguCodingApplication {

    public static void main(String[] args) {
        SpringApplication.run(GuestBookWithGuguCodingApplication.class, args);
    }

}
