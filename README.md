# spring-instagram-20th
CEOS 20th BE study - instagram clone coding

## 2주차
### 1) DB 모델링
#### 요구사항
- 게시글 조회
- 게시글에 사진과 함께 글 작성
- 게시글에 댓글 및 대댓글 기능
- 게시글에 좋아요 기능
- 게시글, 댓글, 좋아요 삭제 기능
- 유저 간 1:1 DM 기능

#### 기능 분석
**[User]**
|내 정보 설정 페이지|연락처 추가 페이지|프로필 페이지|
|------|---|---|
|![image](https://github.com/user-attachments/assets/8c592b62-8ae9-49ba-985c-fc7b5339f5e8)|![image](https://github.com/user-attachments/assets/9019e531-c145-4c6c-8b18-ac4d3751090e)|![image](https://github.com/user-attachments/assets/4fc59823-c367-4e5d-b84f-843060c2b583)|
|이름, 사용자 이름, 성별, 소개, 프로필|전화번호, 이메일|게시물, 팔로잉, 팔로워 수|

- 계정 생성: 30자 미만 사용자 이름(닉네임) + 패스워드 + 자동으로 가입일자 저장
- 나머지 필드는 계정 생성 이후 수정 가능


**[관계 분석]**

- User와 User 사이 Follow 관계(m:n) - Follow 테이블 생성

![image](https://github.com/user-attachments/assets/7d45a084-23c9-4a44-8de2-dc13ec41be73)



**[Post]**
|게시글 조회|게시글 생성|
|------|---|
|![image](https://github.com/user-attachments/assets/316d5f0a-2301-4a26-a079-dcda73f1d9db)|![image](https://github.com/user-attachments/assets/dbf53200-f297-482d-8f3b-209e15fe89d5)|
|작성자, 날짜, 좋아요, 댓글, 스크랩|본문, 해시태그|

- 게시글 생성: 이미지와 본문, 해시태그로 생성 가능
- 게시글 좋아요, 댓글 생성, 스크랩 가능

**[관계 분석]**

- User와 Post 사이 Like 관계(m:n) - Like 테이블 생성
- User와 Post 사이 Scrap 관계(m:n) - Scrap 테이블 생성
- Post와 Hashtag 사이 PostHashtag 관계(m:n) - PostHashtag 테이블 생성

![image](https://github.com/user-attachments/assets/3b61e494-41c7-4022-818b-c982a7deafa8)


**[Comment]** <br />
![image](https://github.com/user-attachments/assets/f99d90cf-3c1f-4016-a336-d6e6c6560370) <br />
작성자, 작성일, 본문, 답글, 좋아요

- 댓글 생성: 하나의 게시글 여러개의 댓글 생성 가능
- 답글 작성: 댓글에 여러 답글 작성 가능
- 회원 태그: 유저를 태그
- 댓글 좋아요

**[관계 분석]**

- Post와 Comment 사이 관계 (1:m)
- Comment와 Comment 사이 관계 (1:m) - 답글 ➡️ Comment가 자기자신을 참조
- Comment와 User 사이 Like 관계 (m:n) - CommentLike 테이블 생성
- Comment와 User 사이 CommentUserTag 관계 (m:n) - CommentUserTag 테이블 생성

![image](https://github.com/user-attachments/assets/4d763a7d-0b7b-4df5-b61f-cc477da1b891)


**[DM]**
|DM 방|DM 관련 기능|
|------|---|
![image](https://github.com/user-attachments/assets/414924ce-fac9-4f38-8d20-32dac11fb682)|![image](https://github.com/user-attachments/assets/725d4e20-d2a6-46b2-9cc6-4bee7aa0f89b)|

- 메시지 전송: 유저 2명이 한 대화방에서 메시지를 주고 받음
- 메시지 공감: 메시지에 공감 표시 가능
- 읽음 여부: 읽음 여부 확인 가능

  **[관계 분석]**

  - User와 DM (m:n) - DMRoom 생성

![image](https://github.com/user-attachments/assets/7148313e-622d-432f-8bc6-bf1a6d409d65)


#### ERD
![image](https://github.com/user-attachments/assets/5f913c7a-cdec-476a-ac4e-b3cc8d9c8c38)

[👉 ERD 링크](https://www.erdcloud.com/d/fqBpSLZ9cCXL4rbae)

#### 💡구현
```java
package com.ceos20.instagram.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "follow_id")
    private Long id;

    // 팔로우 한 시간
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // 친한 친구 여부
    private Boolean isBestFriend = false;

    // 팔로우 한 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="follower_id")
    private User followerId;

    // 팔로우 당한 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="following_id")
    private User followingId;


}

```

#### 💡연관관계
연관관계 매핑: 객체의 참조와 테이블의 외래키를 매핑하는 것

관계형 데이터베이스에서의 테이블 사이 연관관계 != 객체 지향 프로그램에서의 객체 사이 연관관계

차이를 해소하기 위한 기술: ORM

**[연관관계 매핑 고려사항]**

- **방향**: 단방향 연관관계, 양방향 연관관계
- **연관관계의 주인**: 양방향 연관관계에서 외래키를 관리하는 객체
- **다중성**: 다대일, 일대다, 일대일, 다대다

만약 데이터 중심적 모델링을 하게된다면?
```java
class Member {
    private long id;
    private long teamId;
    private String userName;
}

class Team {
    private long id;
    private String teamName;
}
```
➡️ 객체지향 프로그래밍에서 객체를 제대로 활용할 수 없음

이렇게 할 경우 member가 속한 팀 정보를 조회하기 위해 teamId를 통해 Team 조회 필요
```java
Member findMember = em.find(Member.class, memberId);
Long findTeamId = findMember.getTeamId();
Team findTeam = em.find(Team.class, findTeamId);
```
이럴 경우 Memeber, Team을 조회하는 2개의 쿼리가 따로 필요

🔎 그렇다면 객체 중심의 모델링을 한다면?
```java
@Entity
public class Memeber {
    @Id @GeneratedValue
    private Long id;
    
    private String userName;
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
```
➕ 추가적으로 ORM이 매핑을 해주는 것까지가 연관관계의 끝

조금 더 @ManyToOne를 살펴보자면
```java
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ManyToOne {
    Class targetEntity() default void.class;

    CascadeType[] cascade() default {};

    FetchType fetch() default FetchType.EAGER;

    boolean optional() default true;
}
```
|코드|설명|
|---|---|
|```@Target({ElementType.METHOD, ElementType.FIELD})```|어노테이션을 메서드 혹은 필드에 사용 가능|
|```@Retention(RetentionPolicy.RUNTIME)```|어노테이션이 애플리케이션 실행 중 반영|
|```Class targetEntity() default void.class;```|관계가 설정된 대상 엔티티의 클래스를 지정(엔티티 지정안할 경우 기본 값 사용 / 이 기능은 거의 사용하지 않음)|
|```CascadeType[] cascade() default {};```|cascade 작업 정의 (PERSIST, MERGE, REMOVE, REFRES, DETACH)|
|```FetchType fetch() default FetchType.EAGER;```|글로벌 패치 전략 설정|
|```boolean optional() default true;```|관계 필수 여부 설정(true일 경우 선택적, false일 경우 필수) |

#### ❓nullable=false와 @NotNull 비교

#### ❓Long형 + 대체키 + 키 생성전략

#### 💡@Getter, @Setter

### 2) Repository 단위 테스트
#### 💡영속성 컨텍스트

#### 💡JPQL

#### 테스트 코드

#### 어노테이션 설명
