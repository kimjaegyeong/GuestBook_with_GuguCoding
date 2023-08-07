package org.zerock.guestbook_with_gugucoding.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook_with_gugucoding.dto.GuestbookDTO;

import java.util.stream.Stream;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    public GuestbookService service;

    @Test
    public void testRegister(){

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("sample title..")
                .content("sample content..")
                .writer("user0")
                .build();

        System.out.println(service.register(guestbookDTO));
    }
}
