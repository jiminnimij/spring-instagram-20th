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

```

#### 💡연관관계

#### ❓nullable=false와 @NotNull 비교

#### ❓Long형 + 대체키 + 키 생성전략

#### 💡@Getter, @Setter

### 2) Repository 단위 테스트
#### 💡영속성 컨텍스트

#### 💡JPQL

#### 테스트 코드

#### 어노테이션 설명
