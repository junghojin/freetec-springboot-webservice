
# 스프링 부트와 AWS로 혼자 구현하는 웹 서비스
> 출판사: 프리렉 / 이동욱 지음
> 22.06.23 ~ 22.07.06 학습
> 공부를 하다 궁금한 점이나 정리할 내용을 블로그에 정리하였습니다.
> [의문사항 기록](https://edlin.tistory.com)
## 블로그 정리 내용
<strong>1. Gradle vs. Maven [link](https://edlin.tistory.com/entry/Gradle-vs-Maven)</strong> <br/>
<strong>2. SpringBoot - build.gradle 의 이해 [link](https://edlin.tistory.com/entry/buildgradle의이해?category=934238)</strong>

<br/>

## 22.06.23. 목 - 01. 인텔리제이로 스프링 부트 시작하기 정리
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

<br/>

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

<br/>

## 22.06.27. 월 - 03. JPA로 데이터 다루기 정리
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
  - JPA ← Hibernate ← Spring Data JPA
  - ︎ 왜 한단계를 더 거치는거지?
    - 구현체 교체 용이 = Hibernate 외 다른 구현체로 쉽게 교체
    - 저장소 교체 용이 = 관계형 데이터베이스 외 다른 저장소로 쉽게 교체 (Spring Data JPA 교체 ▻ MongoDB)

#### ```build.gradle```
- ```dependencies {...}``` - ```spring-boot-starter-data-jpa```: Spring Data JPA *추상화 라이브러리*
- ```com.h2database.h2```: 인메모리(데이터 스토리지 메인메모리에 설치되어 운영되는 방식) 관계형 데이터베이스
  - 메인메모리에 적재되기 때문에 애플리케이션 재시작 시 초기화 됨 → 테스트용 
  - 인메모리 관계형 데이터베이스 vs. 디스크 관계형 데이터베이스

#### Domain class - Entity class + Repository class
- Entity 클래스에서는 **절대 @Setter를 만들지 않는다.**
- 대신, 값 변경이 필요하면 명확히 목적과 의도를 나타낼 수 있는 메소드 추가
- 생성자를 통해 최종값을 채워 DB 삽입 or ```@Builder``` 를 통해 제공되는 빌더 클래스 : 생성 시점 값을 채워주는 것은 똑같다.
  - 빌더를 사용하는 것은 파라미터의 순서를 지키지 않아도 필드에 값을 채울 수 있기 때문이다.
```java
  // 생성자
    public Example(String a, String b) {
    this.a = a;
    this.b = b;
    }
    
    // Builder
    Example.builder().a(a).b(b).build();
```

#### Repository = DAO
- ```JpaRepository<Entity Class, PK type>```를 통해 기본적 CRUD 자동 생성
- ```@Repository``` 라고 선언 불필요
- Entity Class와 같은 곳에 위치
```java
public interface PostsRepository extends JpaRepository<Entity Class, PK type> {}
```

#### Junit으로 Test 하기
- ```resources/application.properties``` 파일을 생성하여 JPA와 관련한 설정을 작성할 수 있다.
- ```spring.jpa.show_sql = true``` 는 h2의 쿼리 문법으로 어떤 쿼리문이 실행되는지를 보여준다.


## 22.06.28. 화 - 03. 등록/수정/조회 API 만들기 정리
- API란? 주문하면 필요한 정보를 제공하는 웨이터 = 사용자들이 직접 구현하지 않아도 필요한 데이터나 모듈을 가져다 쓸 수 있는 것
- 웨이터라는 interface를 만들기 위해서는 3 가지가 필요하다: Dto, Controller, Service
  - Dto: Request 데이터를 받는다
  - Controller: API 요청을 받는다 = 요청을 받아 실제 일을 수행한다.
  - Service: 트랜잭션. 도메인 기능 간 순서를 보장한다. = 요청을 수행할 Controller를 매칭해준다.

#### Spring 웹 계층 
1. Web Layer: ```@Controller```와 같이 외부 요청과 응답에 대한 영역, 뷰 템플릿 영역
2. Service Layer: ```@Service```/```@Transactional```, Controller와 Dao의 중간 역할 - 순서 보장
3. Repository Layer: Database에 접근하는 영역 = Data Access Object
4. DTOs = Data Transfer Object, 계층간 데이터 교환을 위한 객체
5. Domain Model: Entities, domain services 담당, VO와 같은 값 객체영역들도 이곳에 해당
**서비스 계층이 비즈니스 로직을 담당한다고 알려져 있으니, 실제 비즈니스 로직을 처리하는 것은 Domain Model이다.**
**서비스 계층은 비즈니스 로직의 순서를 보장한다**

#### DTO와 VO의 비교
- DTO는 계층 간 데이터 전달을 위한 목적으로 생성 
  - 필드값이 같아도 같은 객체가 XXX
  - Setter 존재 유무에 따라 값이 변할 수도 값이 변하지 않을 수 있음

- VO는 값 자체를 표현하기 위해 생성
  - 값 자체를 표현??? 필드값이 같으면 같은 객체
  - Setter 존재 X, 다른 로직(메소드)이 있을 수 있음

#### 게시글 등록, 수정, 삭제 기능
- ```domain```에 ```Posts```라는 Entity가 있는데도 불구하고, Dto를 만드는 이유:
  - Dto는 View의 영역, domain은 DB의 영역
  - Entity클래스는 테이블 자체이기 때문에 조작을 많이 하면 안된다.
  - Dto를 만들어주어 Entity 자체 조작을 줄인다. 

- **Junit Test에서 ```@WebMvcTest```대신 ```@SpringBootTest```를 사용하는 이유**
  - ```@WebMvcTest```의 경우, JPA 기능 작동하지 않기 때문
    - Controller와 ControllerAdvice와 같은 외부 연동과 관련된 부분만 활성화

- Controller에서 Service와 연결 
  - Service에서 JPARepository와 연결할 때, 출력되는 결과에 따라 Dto 생성
  - 왜? Dto는 계층 간 데이터 전달을 위해 만들어지기 떄문
  - 단, Entity는 한개

✔︎- 더티체킹??????????????????????????????????

#### JPA Auditing으로 생성시간/수정시간 자동화
- Java8부터 LocalDate와 LocalDateTime 등장
  - Java8 이전: Date / Calendar class
    - 불변객체가 아니므로 멀티 스레드 환경에서 객체의 값이 변하여 오류가 발생할 수 있다. 
    - Month 값 설계가 잘못되었다 = Calendar.OCTOBER(= 9)
    - LocalDate를 통해 해결
- Hibernate 5.2.10 버전에서 데이터베이스 매핑 문제 해결

✔︎- BaseTimeEntity?????????????????????????? 

## 22.06.27. 월 - 04. 머스테치로 화면 구성하기 정리

#### 서버 템플릿 엔진과 클라이언트 템플릿 엔진의 차이 - 브라우저 위에서 실행되느냐 아니냐로 구분
- 서버 탬플릿 엔진 = Server Side Rendering (SSR)
- 클라이언트 템플릿 엔진 = Client Side Rendering (CSR)
- Server Side Rendering: 서버에서 페이지 내용을 다 그려서 브라우저로 던져준다.
  - 서버에서 Java 코드로 문자열을 만들어 HTML로 변환하여 브라우저 전달 
    - 클라이언트 단에서 HTML을 읽어 결과를 보여준다.
    - 서버에서 전체 웹 사이트를 다시 받아와야 하기 때문에 Blinking Issue 가 있다.
    - 서버 과부하가 걸리기 쉽다. (사용자가 많을수록 서버에 데이터를 요청하는 횟수가 많아진다.)
    - HTML → JavaScript Download → Browser executes React → Page is interactive'
    - Time To View와 Time To Interactive의 공백시간이 꽤 길다.
  - CSR의 느린 페이지 로딩과 좋지않은 SEO 성능을 해결하도록 하였다.
  - 자바스크립트 코드는 서버에서는 단순한 문자열일 뿐
  - 서버에서는 Json 혹은 XML 형식의 데이터를 받아 해석할 수 있다.

---

- Client Side Rendering: 데이터만 주고 받으며 페이지 내용을 브라우저에서 그린다.
  - 자바스크립트는 클라이언트 단인 브라우저에서 실행된다.
  - 사용자가 요청할 때마다 리소스를 서버에서 제공받아 클라이언트가 해석하고 렌더링 한다.
    - Time To View와 Time To Interactive의 공백시간이 없다. (링크되어있는 모든 로직을 처리하는 자바스크립트만 다운받아오면 된다.)
    - SPA: Single Page Application: 최초 한 번 페이지 전체를 로딩 후, 데이터만 변경하여 사용하는 애플리케이션
    - 사용자가 첫 화면을 보기까지의 시간이 오래걸릴 수 있다. 
    - 구글과 같은 **Search Engine Optimization**은 서버에 등록된 웹 사이트를 돌아다니면서, 웹 사이트의 HTML을 분석하여 검색을 하도록 돕는다.
    - 단, 클라이언트 사이드 렌더링은 <body>가 텅텅 비어있기 때문에 HTML 문서를 분석하는데 어려움이 있다. 
  - 리액트나 뷰는 자바스크립트 프레임워크로 서버 사이드 렌더링이 가능하도록 도울 수 있고 필수사항은 아니다.
    - 리액트의 경우 Gatsby, Next.js 라이브러리를 통해 서버사이드 렌더링을 지원한다. 
    
##### 서버 템플릿 엔진 단점으로 인한 Mustache 선택
1. JSP, Velocity: 스프링 부트에서 권장하지 X
2. Freemarker: 템플릿 엔진이나 너무 많은 기능 지원, 높은 자유도 - 숙련도가 높다 (비즈니스 로직이 추가될 확률이 높다)
3. Thymeleaf: HTML 태그 속성으로 템플릿 기능을 사용하기 때문에 문법이 어렵다
4. Mustache: 문법이 심플하고 로직 코드를 사용할 수 없다, 다양한 언어를 지원하기 때문에 하나의 문법으로 클라이언트/서버 템플릿 모두 사용 가능하다.
- JSP, Thymeleaf는 인텔리제이 유료버전에서 지원, Mustache는 무료 버전에서도 지원한다.

#### 머스테치란
- 수 많은 언어를 지원하는 심플한 템플릿 엔진
- JSP와 같이 HTML을 만들어주는 템플릿 엔진
  - JSP (=Java Server Pages)
    - HTML 코드에 Java 코드를 넣어 동적으로 페이지를 생성하는 웹 애플리케이션 도구
    - JSP가 실행되면 자바 서블릿으로 변환하여 웹 애플리케이션 서버에서 동작한다.
    - 서블릿에서 HTML 코드를 넣을 경우 복잡해진다는 단점을 해결하기 위해, HTML 코드에 자바 코드를 넣는 방식으로 작업의 효율성을 높였다.
    - 서버에서 요청을 받으면 Servlet(.java) 파일 생성 → .class 파일로 컴파일 → 실행을 통해 HTML 파일 생성하여 JSP 컨테이너 전달 → 클라이언트에 HTML 파일 전달

#### 레이아웃 파일이란?
- 공통 영역을 별도의 파일로 분리하여 필요한 곳에 가져다 쓰는 방식

#### SpringDataJpa에서 제공하지 않는 메소드는 ```@Query```로 
- Entity 데이터는 ```select```를 통해 조회가능하지만, FK의 조인, 복잡한 조건 등으로 인해 Entity 클래스만으로 처리하기 어려운 경우 조회용 프레임워크를 사용한다.
- ```querydsl, jooq, MyBatis``` 등으로 조회 기능을 사용한다.
- 그 외 등록, 수정, 삭제 등은 기존 SpringDataJpa를 사용한다.
```java
@Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
```

## 22.06.28. 화 - 05. 스프링 시큐리티와 OAuth 2.0으로 로그인 기능 구현하기


