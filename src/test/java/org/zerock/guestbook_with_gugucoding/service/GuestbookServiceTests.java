package org.zerock.guestbook_with_gugucoding.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook_with_gugucoding.dto.GuestbookDTO;

@SpringBootTest
public class GuestbookServiceTests {

    @Autowired
    public GuestbookService service;

    @Test
    public void testRegister(){ //service계층의 dto 객체를 repository계층의 entity로 변환하여 등록하는 동작을 테스트하는 메소드이다.

        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("sample title..")
                .content("sample content..")
                .writer("user0")
                .build(); //builder 패턴 사용

        System.out.println(service.register(guestbookDTO));
    }
}
