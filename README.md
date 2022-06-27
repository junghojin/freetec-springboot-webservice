
# 스프링 부트와 AWS로 혼자 구현하는 웹 서비스
> 출판사: 프리렉 / 이동욱 지음
> 22.06.23 ~ 22.07.06 학습
> 공부를 하다 궁금한 점이나 정리할 내용을 블로그에 정리하였습니다.
> [의문사항 기록](https://edlin.tistory.com)
## 블로그 정리 내용
<strong>1. Gradle vs. Maven [link](https://edlin.tistory.com/entry/Gradle-vs-Maven)</strong>
<strong>2. SpringBoot - build.gradle 의 이해 [link](https://edlin.tistory.com/entry/buildgradle의이해?category=934238)</strong>

## 22.06.23. 목 - 01. 인텔리제이로 스프링 부트 시작하기
- build.gradle에 수동으로 repositories, dependencies 설정
- gradle 에러 :
  - /gradle/wrapper/gradle-wrapper.properties 에서 버전 변경
  ```script 
  distributionUrl=https\://services.gradle.org/distributions/gradle-4.10.2-bin.zip
  ```
- Git upload
  - command + shift + A : share project on github 입력하여 선택
  - 깃 저장소 생성 : 로그인, directory 명, description 등 작성
    - .idea 디렉토리는 커밋하지 않는다
    - 왜? 프로젝트 실행시 자동으로 생성되는 파일
      - .gitignore 자동 생성 - 자동생성되지 않을 경우 plugins 설치 필요 (인텔리제이 다시 시작 필요)
      ```script
      .gradle
      .idea
      ```
  - command + K : 커밋할 파일 설정 및 커밋메시지 작성
  - command + shift + K : 푸쉬

## 22.06.26. 일 - 02. Hello Controller 테스트 코드 작성하기
- ```src.main.java``` 에 ```com.jojoldu.book.springboot` 패키지를 만든 후, ```Application.java``` 를 생성한다.</strong>
  - ```@SpringBootApplication``` 
    - Application의 최상단에 위치
    - 스프링 부트 자동 설정
    - Bean 읽기와 생성 모두 자동 설정

- ```web``` : Controller
  - @RestController: JSON을 반환하는 컨트롤러 
  - @GetMapping: HTTP method - Get


#### Test 코드 작성하기
> 실행 시 오류가 발생하면 Preferences에서 IntelliJ로 테스트 환경을 바꿔볼 것!

- 테스트 코드 작성 시, test라는 폴더에 ```com.jojoldu.book.springboot``` 패키지 똑같이 생성
  - 테스트 코드는 보통 테스트할 클래스 이름에 Test를 붙임
  - (```HelloControllerTest``` : ```HelloController```를 테스트하고자한다)

- ```@RunWith (SpringRunner.class)```
  - 단위 테스트를 진행하되, 스프링부트 자체 테스트와의 연결을 위해서 ```SpringRunner``` 라는 실행자를 사용하겠다.

- ```@WebMvcTest```
  - ```@Controller```, ```@ControllerAdvice``` 등을 사용할 수 있다.
  - **주의** 단, ```@Service```, ```@Component```, ```@Repository``` 등은 사용할 수 없다.

- ```MockMvc``` : 웹 API 테스트 시 사용하는 클래스, HTTP 메소드에 대한 테스트 진행 가능
  - ```mvc.perform(get("/hello")).andExpect(status().isOK()).andExpect(content().string("hello")))```
    - mockMvc을 통해 get이라는 메소드를 실행하겠다.
    - 상태메시지가 ```status().isOk()``` 로 주어지는지 확인
    - return 되는 값이 "hello"인지 확인
    
#### Lombok
> 프로젝트마다 compiler 설정 에 들어가, Enable annotation processing을 체크해줘야 한다. 

## 22.06.27. 월 - 03. JPA로 데이터 다루기
- <strong> JPA: 자바 표준 ORM - 객체 매핑 </strong>
- <strong> MyBatis: SQL Mapper - 쿼리 매핑</strong>

**⁇ 객체 매핑과 쿼리 매핑의 다른 점은 뭘까? 패러다임 불일치**
- *Java* 기반 Spring 프레임워크는 *객체지향적 프로그램 설계*를 지향한다.
- *SQL* 기반 MySQL DBMS(데이테베이스 관리 시스템)은 *쿼리 매핑을 목적*으로 한다.
  - CRUD 기능 구현 시, 각 테이블마다 기본적인 SQL문 매 번 생성이 필요하다.
  - 즉, 단순 반복 작업을 수백 번 해야한다.
- 상속, 1:N등 다양한 객체 모델링을 데이터베이스로 구현할 수 없다.
→ 즉, JPA는 객체지향적으로 프로그래밍을 하고, SQL에 종속적인 개발을 하지 않도록 패러다임을 일치시켜주기 위한 기술

#### Spring Data JPA
- JPA: **Interface**, 자바 표준 명세 → 구현체 (Eclipse Link, Hibernate 등)
- 단, Spring에서는 구현체를 직접 다루지 않는다.
- 대신, Spring Data JPA라는 모듈을 이용하여 JPA 기술을 다룬다.
  - JPA ◀︎ Hibernate ◀ Spring Data JPA
  - ︎ 왜 한단계를 더 거치는거지?
    - 구현체 교체 용이 = Hibernate 외 다른 구현체로 쉽게 교체
    - 저장소 교체 용이 = 관계형 데이터베이스 외 다른 저장소로 쉽게 교체 (Spring Data JPA 교체 ▻ MongoDB)