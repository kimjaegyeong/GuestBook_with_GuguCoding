package org.zerock.guestbook_with_gugucoding.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Guestbook extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gno;

    @Column(length=100, nullable=false)
    private String title;

    @Column(length=100, nullable=false)
    private String content;

    @Column(length=50, nullable = false)
    private String writer;
}
