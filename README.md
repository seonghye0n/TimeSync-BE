# 🚀 연차/당직 관리 프로젝트

## 목차

- [프로젝트 개요](#-프로젝트-개요)
- [프로젝트 기간](#-프로젝트-기간)
- [팀원 소개](#-팀원-소개)
- [기술 스택](#-기술-스택)
- [기술 아키텍처](#-기술-아키텍처)
- [프로젝트 세부 사항](#-프로젝트-세부사항)
- [설치 및 실행 방법](#-설치-및-실행방법)
- [DB 설계](#-DB-설계)
- [API 문서](#-API-문서)
- [기술 설명](#-기술-설명)

## 🌟 프로젝트 개요

"연차/당직 을 관리하는데 좀 더 효율적이고 편리하게 사용하게끔 만들어졌다."

## 📅 프로젝트 기간

### 2023년 7월 25일 - 2023년 8 월 11일

## 👥 팀원 소개

|                                                                                                                     **[박성현(BE-팀장)](https://github.com/seonghye0n)**                                                                                                                     |                                                                                                      **[서용현](https://github.com/zjdtm)**                                                                                                       |
|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|                                                                                                      <img src="https://avatars.githubusercontent.com/u/53041717?v=4" width="300">                                                                                                       |                                                                                  <img src="https://avatars.githubusercontent.com/u/35757620?v=4" width="300">                                                                                  |
| <ol> <b>API</b> <li>메인페이지 조회</li> <li>연차등록, 수정, 삭제</li> <li>마이 페이지 조회</li> <li>관리자 페이지 조회</li> <li> 결재 승인</li> </ol> <ol><b>DB</b> <li>테이블 스키마 초안 작성</li> </ol> <ol><b>서버/배포</b> <li>테스트 서버 구성(EC2)</li> <li>SSL 적용</li> <li>운영 서버 구성 <br>(Elastic Beanstalk)</li> <li>배포 자동화</li></ol> | <ol><b>API</b> <li>로그인</li> <li>회원가입</li> <li>비밀번호 수정</li></ol> <ol><b>DB</b> <li>Redis 구성 (RefreshToken 관리)</li></ol> <ol><b>보안/인증</b> <li>JWT Token 구현 (AccessToken, RefreshToken)</li> <li>Spring Security 설정</li> <li>AES 암호화 구현</li></ol> |

<br>

## 🚀 기술 스택

<div align=center>

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/springsecurity-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">

<img src="https://img.shields.io/badge/Amazon EC2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
<img src="https://img.shields.io/badge/Redis-DC382D?style=for-the-badge&logo=Redis&logoColor=white">
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white">
<img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white">
<img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=GitHub&logoColor=white">

<img src="https://img.shields.io/badge/GitHub Actions-2088FF?style=for-the-badge&logo=GitHub Actions&logoColor=white">
<img src="https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=Nginx&logoColor=white">
<img src="https://img.shields.io/badge/RDS-527FFF?style=for-the-badge&logo=AmazonRDS&logoColor=white">

</div>

## 🏛️ 기술 아키텍처

![image](https://github.com/seonghye0n/miniproject/assets/35757620/fe3cdfa9-b374-4645-aed9-7c2af8235ed9)

## 📝 프로젝트 세부사항

- ### 📅 연차 및 당직 관리 시스템 개발
  ![연차, 당직 관리 시스템](https://github.com/seonghye0n/miniproject/assets/35757620/4417377f-cc9c-4d24-afd8-f100e2242c9e)
- ### 💼 사용자 친화적인 UI/UX 디자인
  ![연차, 당직 메인 페이지](https://github.com/seonghye0n/miniproject/assets/35757620/7043bdf6-c4ce-45eb-ae0b-fc4a56a9f8af)
- ### 🛠️ 백엔드와 프론트엔드 협업을 통한 프로젝트
  프론트엔드 프로젝트
  링크 : https://github.com/FastCampusGroupFE9/MiniProject_KDT9

## ⚙️ 설치 및 실행방법

프로젝트 링크 : https://hmteresting.netlify.app/

관리자 계정 : admin@admin.com / fastcampus12#$

## 📑 DB 설계

### ERD

![ERD](https://github.com/seonghye0n/miniproject/assets/53041717/6a3d9cdd-28e7-45e2-92fa-aba6c7164fd2)

### member

```sql
create table member
(
    id               bigint primary key auto_increment,
    email            varchar(100) unique not null,
    password         varchar(100)        not null,
    name             varchar(100)        not null,
    joined_at        date                not null,
    role             varchar(10)         not null,
    annual_amount_id bigint              not null,
    annual_used      int,
    annual_remain    int,
    position         varchar(5)          not null,
    loggedin_at      timestamp,
    created_at       timestamp           not null,
    modified_at      timestamp,
    foreign key (annual_amount_id) references annual (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### req

```sql
create table reg
(
    id          bigint primary key auto_increment,
    category    varchar(10) not null,
    title       varchar(40) not null,
    started_at  date        not null,
    lasted_at   date        not null,
    reason      varchar(20),
    status      varchar(10) not null,
    member_id   bigint,
    created_at  timestamp   not null,
    modified_at timestamp,
    foreign key (member_id) references member (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### annual

```sql
create table annual
(
    id            bigint primary key auto_increment,
    years         int        not null,
    annual_amount int        not null,
    position      varchar(5) not null,
    hist_year     varchar(4) not null,
    unique (years, position, hist_year)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### login_log

```sql
create table login_log
(
    id                 bigint primary key auto_increment,
    user_agent         varchar(200) not null,
    client_ip          varchar(15)  not null,
    member_id          bigint       not null,
    success_login_date timestamp,
    foreign key (member_id) references member (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### refresh_token

```sql
create table refresh_token
(
    id            bigint       not null auto_increment,
    email         varchar(255) not null,
    refresh_token varchar(255) not null,
    primary key (id)
) engine=InnoDB DEFAULT CHARSET=utf8mb4;
```

## 📄 API 문서

| 엔드포인트                | 메소드  | 요청 본문 (Request Body)                                                                                                   | 요청 헤더 (Request Headers)                                           | 응답 본문 (Response Body)                                                                                    |
|----------------------|------|------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| `/api/register`      | POST | ``` {email: asd@asd.com, password: 1234567, name: “아무개”, join: YYYY-MM-DD } ```                                        | -                                                                 | `상태값 200 ok,json body 에 “회원가입에 성공하였습니다”. 메시지`                                                            |
| `/api/login`         | POST | -                                                                                                                      | `{ email: 이메일password: 패스워드}`                                     | `상태값 200 ok, accessToken 은 json body, refreshToken 은 cookie 값에 저장`                                       |
| `/api/token`         | POST | `{"startDate": "2023-08-15", "endDate": "2023-08-20"}`                                                                 | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200 ok, accessToken 은 json body, refreshToken 은 cookie 값에 저장`                                       |
| `/api/logout`        | POST | -                                                                                                                      | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200 ok, 메시지`                                                                                        |
| `/api/main`          | GET  | `                                                                                                                      | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | ![image](https://github.com/seonghye0n/miniproject/assets/35757620/a4400c07-dc53-4f54-9823-f469c7b32f6c) | 
| `/api/annual`        | POST | `{“title” : “연차 신청합니당~~”, “category” : “연차”, “startDate” : “2023-08-02”, “endDate” : “2023-08-07”,  “reason” : “병가” }` | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200 ok, 메시지`                                                                                        | 
| `/api/user`          | GET  | -                                                                                                                      | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | ![image](https://github.com/seonghye0n/miniproject/assets/35757620/2f97ee06-c667-4ee8-8a8e-f04df02e47ff) |
| `/api/user`          | POST | `{“newPassword” : “새로운 비밀번호” }`                                                                                        | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200 ok, 메시지`                                                                                        |
| `/api/annual/cancel` | POST | {“id” : annul 번호}                                                                                                      | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200. 메시지`                                                                                           |
| `/api/annual/update` | POST | {“id” : 1, “title” : “연차 수정합니다~~”, “startDate” : “2023-08-01”, “endDate” : “2023-08-04”,“reason” : “병가”}               | `Authorization: Bearer your_access_token`, `Cookie: refreshToken  | `상태값 200 ok 메시지`                                                                                         |
| `/api/admin`         | GET  | -                                                                                                                      | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | ![image](https://github.com/seonghye0n/miniproject/assets/35757620/69352c1e-6c42-48f1-b413-01909345a239) |
| `/api/admin/apply`   | POST | { “id” : 1 }                                                                                                           | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200 ok, 메시지`                                                                                        |

## 🛠️ 기술 설명

### Java 및 Spring Boot

프로젝트는 Java 언어와 Spring Boot 프레임워크를 기반으로 개발되었습니다. Spring Boot는 간결하고 효율적인 코드 작성을 지원하며, 프로젝트의 핵심 로직을 구현하는데 활용되었습니다.

### Amazon EC2 및 RDS

프로젝트는 Amazon EC2 인스턴스를 활용하여 테스트 서버와 운영 서버를 구성하였습니다. 데이터베이스는 Amazon RDS를 이용하여 관리되며, 안정적인 데이터 저장 및 관리가 가능합니다.

### Redis 로 RefreshToekn 관리

Redis를 사용하여 RefreshToken 을 구현하였다. 토큰의 만료기간을 Redsi 의 유효기간으로 설정하여 토큰을 좀 더 안전하게 관리하고 삭제하는 것이 가능합니다.

### Spring Security 및 JWT 인증

Spring Security를 통해 강력한 보안 기능을 구현하였으며, JWT(JSON Web Token) 기반의 인증 방식을 사용하여 안전한 사용자 인증을 보장합니다.

### GitHub Actions를 통한 자동 배포

프로젝트의 소스 코드는 GitHub Actions를 활용하여 자동으로 테스트 및 배포되며, 개발자들의 작업 흐름을 자동화하고 효율성을 높였습니다.

---

## 👨‍🏫 코드리뷰 요청

### <img width="15" alt="star1" src="https://user-images.githubusercontent.com/78655692/151471925-e5f35751-d4b9-416b-b41d-a059267a09e3.png"> 코드 리뷰 잘 부탁드립니다 !!

`박성현`

1. 여러 DTO에 공통된 필드들이 있는데 한번에 Validation 처리를 하는 방법이 있을까요? 어떤 키워드로 찾아봐야될지 잘 모르겠습니다..! 예를
   들어서 [연차/당직 Request DTO](https://github.com/seonghye0n/miniproject/blob/33b38bec1f4b52ba97dc76585a7768d4269c9ace/src/main/java/com/example/miniproject/domain/annual/dto/AnnualRequestDto.java#L28)
   에 SaveDto, UpdateDto 두 Dto에 공통된 필드들이 있는데 message를 일일이 입력하는게 번거롭다는 생각이 들었습니다!
2. 코드 관련은 아니지만 궁금한게 있어서 질문드립니다. 첫 멘토링 때, 현업에서도 개인정보를 암호화한 값을 DB에 저장한다고 하셨는데 혹시 직접 쿼리로 조회하실 때는 암호화된 값을 어떻게 복호화하나요?? 예를
   들어, 현재 이름이 암호화한 값으로 DB에 저장되고 있는데, 관리자가 급하게 특정 이름으로 회원목록을 뽑아달라고 요청하면 어떻게 조회할 수 있는지 궁금합니다.

`서용현`

1. **SecurityConfig filter**
    - 정신없이 기능구현에만 집중하다가 보니 필터 하나의 너무 많은 기능을 추가한 것 같아서 현재 부분의 cookie 검증 부분이나, jwt 생성 부분을 다른 필터로 나누는 것은 어떨까 하는 생각입니다.

2. **로그인 시 응답값 부분**
    - 프론트 요구사항으로는 body 에 accessToken, role(회원의 권한) 을 담아서 보내주는 것이라서 다음과 같이 작성하였는데 role 부분도 jwt 에 subject 에 넣는 것이 더 좋은지
      궁금합니다.

3. **MemberRequestDto, GlobalExceptionHandler**
    - validation 에러를 handler 처리를 하려고 작성하였는데 굳이 이거 하나 때문에 handler 처리하기에는 이상하다고 생각이 듭니다. 이런 상황에서는 handler 처리 하는 방법을 알고
      싶습니다.

4. **로그인/로그아웃 Controller, SecurityConfig**
    - login, logout 을 security 에 filter로 구현하지 않고 MemberController 에 메서드 형태로 작성하였는데 이런 식의 코드 작성이 좋은지 filter 에 적용하는 것이 좋은지
      궁금합니다.