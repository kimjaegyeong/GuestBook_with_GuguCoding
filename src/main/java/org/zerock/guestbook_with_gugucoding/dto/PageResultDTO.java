package org.zerock.guestbook_with_gugucoding.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
public class PageResultDTO<DTO, EN> { //EN == ENTITY
    private List<DTO> dtoList;
    public PageResultDTO(Page<EN> result, Function<EN,DTO> fn){ //Function 함수형인터페이스는 apply메서드를 의미하며,
        //PageResultDTO가 사용될 때 람다를 이용하여 apply를 구현할 것이다.
        dtoList = result.stream().map(fn).collect(Collectors.toList());

    }
}
