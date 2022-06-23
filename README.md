
# 스프링 부트와 AWS로 혼자 구현하는 웹 서비스
> 출판사: 프리렉 / 이동욱 지음
> 22.06.23 ~ 22.07.06 학습
> 공부를 하다 궁금한 점이나 정리할 내용을 블로그에 정리하였습니다.
> [의문사항 기록](https://edlin.tistory.com)
## 블로그 정리 내용
1. Gradle vs. Maven [link](https://edlin.tistory.com/entry/Gradle-vs-Maven)
2. SpringBoot - build.gradle 의 이해 [link](https://edlin.tistory.com/entry/buildgradle의이해?category=934238)

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