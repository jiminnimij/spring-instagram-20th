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
[User]
|내 정보 설정 페이지|연락처 추가 페이지|프로필 페이지|
|------|---|---|
|![image](https://github.com/user-attachments/assets/8c592b62-8ae9-49ba-985c-fc7b5339f5e8)|![image](https://github.com/user-attachments/assets/9019e531-c145-4c6c-8b18-ac4d3751090e)|![image](https://github.com/user-attachments/assets/4fc59823-c367-4e5d-b84f-843060c2b583)|
|이름, 사용자 이름, 성별, 소개, 프로필|전화번호, 이메일|게시물, 팔로잉, 팔로워 수|

[Post]

[Comment]

[DM]


#### ERD
![image](https://github.com/user-attachments/assets/5f913c7a-cdec-476a-ac4e-b3cc8d9c8c38)

#### 💡@Column

#### 💡연관관계

#### ❓nullable=false와 @NotNull 비교

#### ❓Long형 + 대체키 + 키 생성전략

#### 💡@Getter, @Setter

### 2) Repository 단위 테스트
#### 💡영속성 컨텍스트

#### 💡JPQL

#### 테스트 코드

#### 어노테이션 설명
