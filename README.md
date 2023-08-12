#  🚀 연차/당직 관리 프로젝트

## 목차

- [프로젝트 개요](#-프로젝트-개요)
- [프로젝트 기간](#-프로젝트-기간)
- [팀원 소개](#-팀원-소개)
- [기술 스택](#-기술-스택)
- [기술 아키텍처](#-기술-아키텍처)
- [프로젝트 세부 사항](#-프로젝트-세부-사항)
- [설치 및 실행 방법](#-설치-및-실행-방법)
- [DB 설계](#-DB-설계)
- [API 문서](#-API-문서)
- [기술 설명](#-기술-설명)
- 
## 🌟 프로젝트 개요
"연차/당직 을 관리하는데 좀 더 효율적이고 편리하게 사용하게끔 만들어졌다."

## 📅 프로젝트 기간
### 2023년 7월 25일 - 2023년 8 월 11일

## 👥 팀원 소개 
<table>
  <tbody>
    <tr>
      <td align="center">
        <a href="https://github.com/seonghye0n"><img src="https://avatars.githubusercontent.com/u/53041717?v=4" width="100px;" alt=""/><br/>
        <sub><b>BE 팀장 : 박성현</b></sub></a><br/>
        <ol>
          <b>API</b>
          <li>메인 페이지 조회</li>
          <li>연차 등록, 수정, 삭제</li>
          <li>마이 페이지 조회</li>
          <li>관리자 페이지 조회</li>
          <li>결재 승인</li>
        </ol>
        <ol>
          <b>DB</b>        
          <li>RDS 구성</li>
        </ol>
        <ol>
          <b>서버/배포</b>
          <li>테스트 서버 구성 (EC2)</li>
          <li>SSL 적용</li>
          <li>운영 서버 구성 (Elastic Beanstalk)</li>
          <li>GitHub Action 이용하여 배포 자동화</li>
        </ol>
      </td>
      <td align="center"><a href="https://github.com/zjdtm"><img src="https://avatars.githubusercontent.com/u/35757620?v=4" width="100px;" alt=""/><br /><sub><b>BE 팀원 : 서용현</b></sub></a><br />
      <ol>
        <b>API</b>
        <li>로그인, 회원가입</li>
        <li>개인정보(비밀번호) 수정</li>
      </ol>
      <ol>
        <b>보안 / 인증</b>        
        <li>JWT Token 구현 (AccessToken, RefreshToken)</li>
        <li>Spring Security 설정</li>
        <li>AES 암호화 구현</li>
      </ol>
      <ol>
        <b>DB</b>      
        <li>Redis 구성 (Refresh Token 관리)</li>
      </ol>
      </td>
  </tbody>
</table>

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

## 📝 프로젝트 세부 사항

- ### 📅 연차 및 당직 관리 시스템 개발
    ![연차, 당직 관리 시스템](https://github.com/seonghye0n/miniproject/assets/35757620/4417377f-cc9c-4d24-afd8-f100e2242c9e)
- ### 💼 사용자 친화적인 UI/UX 디자인
    ![연차, 당직 메인 페이지](https://github.com/seonghye0n/miniproject/assets/35757620/7043bdf6-c4ce-45eb-ae0b-fc4a56a9f8af)
- ### 🛠️ 백엔드와 프론트엔드 협업을 통한 프로젝트
    프론트엔드 프로젝트 
    링크 : https://github.com/FastCampusGroupFE9/MiniProject_KDT9
  
## ⚙️ 설치 및 실행 방법

프로젝트 링크 : https://hmteresting.netlify.app/

## 📑 DB 설계

### Member
```sql
create table member (
        annual_remain integer not null,
        annual_used integer not null,
        annual_amount_id bigint,
        created_at datetime(6),
        id bigint not null auto_increment,
        joined_at datetime(6) not null,
        modified_at datetime(6),
        email varchar(255),
        name varchar(255),
        password varchar(255),
        position varchar(255),
        role enum ('ADMIN','USER'),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```
### req
```sql
create table reg (
        lasted_at date not null,
        started_at date not null,
        created_at datetime(6) not null,
        id bigint not null auto_increment,
        member_id bigint,
        modified_at datetime(6),
        category enum ('ANNUAL','DUTY') not null,
        reason enum ('ANNUAL','CONDOLENCE','MATERNITY','OTHER','SICK'),
        status enum ('COMPLETE','READY') not null,
        title varchar(255),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### Annual
```sql
create table annual (
        annual_amount integer not null,
        years integer not null,
        id bigint not null auto_increment,
        hist_year varchar(255),
        position varchar(255),
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### login_log
```sql
create table login_log (
        id bigint not null auto_increment,
        member_id bigint,
        success_login_date datetime(6),
        client_ip varchar(255) not null,
        user_agent varchar(255) not null,
        primary key (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

## 📄 API 문서

| 엔드포인트             | 메소드 | 요청 본문 (Request Body)           | 요청 헤더 (Request Headers) | 응답 본문 (Response Body)          |
|-----------------------|--------|-----------------------------------|-------------------------|-----------------------------------|
| `/api/register`     | POST   | ``` {email: asd@asd.com, password: 1234567, name: “아무개”, join: YYYY-MM-DD } ``` | -                 | `상태값 200 ok,json body 에 “회원가입에 성공하였습니다”. 메시지` |
| `/api/login`           | POST    | -                                 | `{ email: 이메일password: 패스워드}` | `상태값 200 ok, accessToken 은 json body, refreshToken 은 cookie 값에 저장` |
| `/api/token`          | POST   | `{"startDate": "2023-08-15", "endDate": "2023-08-20"}` | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200 ok, accessToken 은 json body, refreshToken 은 cookie 값에 저장` |
| `/api/logout`          | POST  | -  | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200 ok, 메시지`|
| `/api/main`          | GET  | `                           | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | ![image](https://github.com/seonghye0n/miniproject/assets/35757620/a4400c07-dc53-4f54-9823-f469c7b32f6c) | 
| `/api/annual`          | POST  | `{“title” : “연차 신청합니당~~”, “category” : “연차”, “startDate” : “2023-08-02”, “endDate” : “2023-08-07”,  “reason” : “병가” }` | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200 ok, 메시지` | 
| `/api/user`     | GET   | -  | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | ![image](https://github.com/seonghye0n/miniproject/assets/35757620/2f97ee06-c667-4ee8-8a8e-f04df02e47ff) |
| `/api/user`     | POST   | `{“newPassword” : “새로운 비밀번호” }`  | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200 ok, 메시지` |
| `/api/annual/cancel`     | POST   | {“id” : annul 번호} | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200. 메시지` |
| `/api/annual/update`     | POST   | {“id” : 1, “title” : “연차 수정합니다~~”, “startDate” : “2023-08-01”, “endDate” : “2023-08-04”,“reason” : “병가”} | `Authorization: Bearer your_access_token`, `Cookie: refreshToken | `상태값 200 ok 메시지` |
| `/api/admin`     | GET   | -  | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | ![image](https://github.com/seonghye0n/miniproject/assets/35757620/69352c1e-6c42-48f1-b413-01909345a239) |
| `/api/admin/apply`     | POST   | { “id” : 1 } | `Authorization: Bearer your_access_token`, `Cookie: refreshToken` | `상태값 200 ok, 메시지` |

## 🛠️ 기술 설명

### Java 및 Spring Boot
프로젝트는 Java 언어와 Spring Boot 프레임워크를 기반으로 개발되었습니다. Spring Boot는 간결하고 효율적인 코드 작성을 지원하며, 프로젝트의 핵심 로직을 구현하는데 활용되었습니다.

### Amazon EC2 및 RDS
프로젝트는 Amazon EC2 인스턴스를 활용하여 테스트 서버와 운영 서버를 구성하였습니다. 데이터베이스는 Amazon RDS를 이용하여 관리되며, 안정적인 데이터 저장 및 관리가 가능합니다.

### Redis 캐싱
Redis를 사용하여 캐싱 기능을 구현하였습니다. 캐시를 활용하여 데이터의 빠른 조회와 처리를 가능케 하였습니다.

### Spring Security 및 JWT 인증
Spring Security를 통해 강력한 보안 기능을 구현하였으며, JWT(JSON Web Token) 기반의 인증 방식을 사용하여 안전한 사용자 인증을 보장합니다.

### GitHub Actions를 통한 자동 배포
프로젝트의 소스 코드는 GitHub Actions를 활용하여 자동으로 테스트 및 배포되며, 개발자들의 작업 흐름을 자동화하고 효율성을 높였습니다.
