package org.zerock.guestbook_with_gugucoding.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.zerock.guestbook_with_gugucoding.entity.Guestbook;


public interface GuestbookRepository extends JpaRepository<Guestbook, Long>//엔티티, 엔티티 키 값
, QuerydslPredicateExecutor<Guestbook> { // querydsl 사용하기 위해서 QuerydslPredicateExecutor 인터페이스 추가 상속

}
