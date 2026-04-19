# CLAUDE.md - microservice-customer

이 문서는 Claude Code가 microservice-customer 프로젝트를 이해하고 개발을 돕기 위한 지침입니다.

루트 프로젝트의 CLAUDE.md에 정의된 공통 원칙(헥사고날 아키텍처, Kotlin 규칙, EDA 설계, 문서 작성 규칙 등)을 먼저 확인하십시오.

## 1. 서비스 개요

- 역할: 고객 문의 메시지 접수 및 저장
- 담당 레거시 패키지: customer
- 포트: 8080

## 2. 기술 스택

- 언어: Kotlin 1.9.x
- 프레임워크: Spring Boot 3.5.13
- 데이터베이스: PostgreSQL (JPA)
- 의존성: spring-boot-starter-web, data-jpa, consul-discovery, consul-config

## 3. 아키텍처

헥사고날 아키텍처(Ports and Adapters)를 사용합니다.

패키지 구조 예시:

```
com.quietchatter.customer/
  domain/          CustomerMessage.kt
  application/
    in/            CustomerMessageCreatable.kt
    out/           CustomerMessageRepository.kt
  adaptor/
    in/            CustomerMessageController.kt
    out/           CustomerMessageJpaRepository.kt
```

## 4. 작업 지침

모든 작업 시작 전 및 작업 중에 superpowers 스킬 목록을 항상 확인하고 상황에 맞는 스킬을 활성화하여 사용하십시오.

### A. 코드 포팅 규칙

- 레거시 Java 코드를 idiomatic Kotlin 코드로 변환하십시오.
- 레거시의 customer/ 패키지 전체를 참고하여 포팅하십시오.
- 새로운 코드를 작성하거나 수정할 때마다 반드시 단위 테스트(Unit Test)를 함께 작성하고 통과를 확인하십시오.

### B. 메시징 및 이벤트 처리

- 모든 외부 이벤트 발행은 트랜잭셔널 아웃박스(Transactional Outbox) 패턴을 따릅니다.
- 이벤트 직렬화 포맷은 Apache Avro를 사용하며, Redpanda Schema Registry와 연동됩니다.
- 스키마 정의는 src/main/avro/ 경로에 .avsc 파일로 관리합니다.
- 스키마 변경 시 ./gradlew generateAvroJava 명령을 실행하여 최신 도메인 객체를 생성해야 합니다.

### C. 확장 고려사항

- 현재는 단순 메시지 저장이지만, 향후 관리자 조회 기능이 추가될 수 있습니다.
- 인터페이스(포트)를 통해 구현체를 분리하여 확장에 유연하게 설계하십시오.

## 5. 구현 스펙 참조

docs/spec.md 를 반드시 읽고 작업을 시작하십시오.
