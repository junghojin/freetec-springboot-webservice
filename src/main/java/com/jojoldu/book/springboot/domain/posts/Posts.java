package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내 Getter 메소드 자동 생성
@NoArgsConstructor // 기본생성자 자동 추기 - Lombok
@Entity // JPA
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // @Column을 선언하지 않으면 varchar(255)이 기본으로 설정된다.
    // 변경사항이 있는 경우 @Column annotation 사용
    private String author;

    @Builder
    // : 해당 클래스의 빌더 패턴 클래스 생성
    // : Lombok - 생성자를 만든다. 생성자에 포함된 필드만 빌더에 포함한다.
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
