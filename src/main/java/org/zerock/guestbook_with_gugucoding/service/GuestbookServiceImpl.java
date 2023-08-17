package org.zerock.guestbook_with_gugucoding.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
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
import org.zerock.guestbook_with_gugucoding.entity.QGuestbook;
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
        BooleanBuilder booleanBuilder =  getSearch(requestDTO);
        Page<Guestbook> result = repository.findAll(booleanBuilder,pageable); //jpa가 제공하는 findAll메소드 사용. 설정한 페이지 조건으로 데이터를 가져오나봄.
        //Querydsl 사용

        Function<Guestbook, GuestbookDTO> fn = (entity -> entityToDto(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public GuestbookDTO read(Long gno) {
        Optional<Guestbook> result = repository.findById(gno);
        return result.isPresent()? entityToDto(result.get()) : null;
    }

    @Override
    public void remove(Long gno) {
        repository.deleteById(gno);
    }

    @Override
    public void modify(GuestbookDTO dto) {
        Optional<Guestbook> result = repository.findById(dto.getGno());
        Guestbook entity= result.get();

        if(result.isPresent()){
            entity.changeTitle(dto.getTitle());
            entity.changeContent(dto.getContent());
            repository.save(entity);
        }
    }

    private BooleanBuilder getSearch(PageRequestDTO requestDTO){
        //Querydsl 처리
        String type= requestDTO.getType();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QGuestbook qGuestbook = QGuestbook.guestbook;
        String keyword = requestDTO.getKeyword();
        BooleanExpression expression = qGuestbook.gno.gt(0L); //gno가 0보다 큰 경우 전부
        booleanBuilder.and(expression); // 조건 : gno가 0보다 큰 경우 이면서 ....

        if(type==null || type.trim().length()==0){//검색조건이 없는 경우
            return booleanBuilder; //위에서 만들어진 booleanBuilder를 return. (exp: gno가 0보다 큰 경우..)
        }

        //검색 조건 작성하기
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        if(type.contains("t")){
            conditionBuilder.or(qGuestbook.title.contains(keyword));
        }
        if(type.contains("c")){
            conditionBuilder.or(qGuestbook.content.contains(keyword));
        }
        if(type.contains("w")){
            conditionBuilder.or(qGuestbook.writer.contains(keyword));
        }

        //모든 조건 통합
        booleanBuilder.and(conditionBuilder); //booleanBuilder를 booleanBuilder와 and 연산했다.

        return booleanBuilder;

    }
}
