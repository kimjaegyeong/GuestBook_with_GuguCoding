package org.zerock.guestbook_with_gugucoding.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook_with_gugucoding.dto.GuestbookDTO;
import org.zerock.guestbook_with_gugucoding.dto.PageRequestDTO;
import org.zerock.guestbook_with_gugucoding.dto.PageResultDTO;
import org.zerock.guestbook_with_gugucoding.entity.Guestbook;

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

    @Test
    public void testList_1(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
    }
    @Test
    public void testList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);
        System.out.println("resultDTO.isPrev() = " + resultDTO.isPrev());
        System.out.println("resultDTO.isNext() = " + resultDTO.isNext());
        System.out.println("resultDTO.getTotalPage() = " + resultDTO.getTotalPage());

        System.out.println("-----------------------------------------");
        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
        System.out.println("=========================================");
        resultDTO.getPageList().forEach(i-> System.out.println(i));
    }

    @Test
    public void testSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc")
                .keyword("한글")
                .build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(pageRequestDTO);

        System.out.println("PREV: "+ resultDTO.isPrev());
        System.out.println("NEXT: "+ resultDTO.isNext());
        System.out.println("TOTAL: "+resultDTO.getTotalPage());

        System.out.println("=======================================");
        for(GuestbookDTO guestbookDTO : resultDTO.getDtoList()){
            System.out.println(guestbookDTO);
        }
        System.out.println("=========================================");
        resultDTO.getDtoList().forEach(i-> System.out.println(i));
    }


}
