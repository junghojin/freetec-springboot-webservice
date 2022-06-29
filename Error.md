## 01. 인텔리제이로 스프링 부트 시작하기 정리
- build.gradle에 수동으로 repositories, dependencies 설정
- gradle 에러 :
    - /gradle/wrapper/gradle-wrapper.properties 에서 버전 변경
  ```script 
  distributionUrl=https\://services.gradle.org/distributions/gradle-4.10.2-bin.zip
  ```
  
## 04. 머스테치로 화면 구성하기
- ```h2-console``` 연결이 되지 않을 경우 ```application.properties```에서 다음 적어줄 것!
``` text
spring.h2.console.enabled=true
spring.session.store-type=jdbc
spring.datasource.url=jdbc:h2:mem:testdb
``` 

- ```...Service.java```에서 ```@Transactional(readOnly=true)``` error
- import 시 주의할 사항!
  1. ```org.springframework.transaction.annotation.Transactional``` → 옵션을 허용(readOnly=true | false) 가능
  2. ```javax.transaction.Transactional``` → 옵션을 허용하지 않음
