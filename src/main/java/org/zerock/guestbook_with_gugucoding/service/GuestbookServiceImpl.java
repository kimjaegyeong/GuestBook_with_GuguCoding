package org.zerock.guestbook_with_gugucoding.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.guestbook_with_gugucoding.dto.GuestbookDTO;
import org.zerock.guestbook_with_gugucoding.dto.PageRequestDTO;
import org.zerock.guestbook_with_gugucoding.dto.PageResultDTO;
import org.zerock.guestbook_with_gugucoding.entity.Guestbook;
import org.zerock.guestbook_with_gugucoding.repository.GuestbookRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor //의존성 자동 주입. 다만 final로 선언된 것들만 가능
public class GuestbookServiceImpl implements  GuestbookService {
    private final GuestbookRepository repository; //JPA처리를 위해 주입

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("DTO----------------");
        log.info(String.valueOf(dto));

        Guestbook entity= dtoToEntity(dto);
        log.info(String.valueOf(entity));

        repository.save(entity);
        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending()); //페이지 설정 및 정렬조건

        Page<Guestbook> result = repository.findAll(pageable); //jpa가 제공하는 findAll메소드 사용. 설정한 페이지 조건으로 데이터를 가져오나봄.
        Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public GuestbookDTO read(Long gno) {
        Optional<Guestbook> result = repository.findById(gno);
        return result.isPresent()? entityToDto(result.get()) : null;
    }


}
