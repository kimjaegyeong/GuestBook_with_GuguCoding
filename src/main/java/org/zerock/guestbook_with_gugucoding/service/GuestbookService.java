package org.zerock.guestbook_with_gugucoding.service;

import org.zerock.guestbook_with_gugucoding.dto.PageRequestDTO;
import org.zerock.guestbook_with_gugucoding.dto.PageResultDTO;
import org.zerock.guestbook_with_gugucoding.entity.Guestbook;
import org.zerock.guestbook_with_gugucoding.dto.GuestbookDTO;


public interface GuestbookService {
    Long register(GuestbookDTO dto);
    PageResultDTO<GuestbookDTO , Guestbook> getList(PageRequestDTO requestDTO);
    GuestbookDTO read(Long gno);
    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto= GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
    return dto;
    }
}
