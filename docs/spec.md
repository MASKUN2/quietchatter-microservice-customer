# microservice-customer 구현 스펙

## 1. 서비스 역할

서비스 사용자로부터 고객 문의 메시지를 접수하고 저장합니다.
인증 없이 누구나 문의를 보낼 수 있습니다.

## 2. 도메인 모델

### CustomerMessage (고객 문의)

| 필드 | 타입 | 설명 |
|---|---|---|
| id | UUID | 기본 키 |
| content | String | 문의 내용 |
| createdAt | LocalDateTime | 접수 일시 |

## 3. API 명세

### POST /v1/customer/messages
고객 문의 메시지를 접수합니다. 인증이 필요하지 않습니다.

요청 바디:
```json
{
  "content": "문의 내용을 입력해 주세요"
}
```

처리 규칙:
* content는 1자 이상 500자 이하이어야 합니다.
* 유효성 검사 실패 시 400 Bad Request 응답.
* 저장 성공 시 202 Accepted 응답 (비동기 처리를 나타냄).

응답: 202 Accepted (응답 바디 없음)

## 4. 유효성 검사 규칙

| 필드 | 규칙 |
|---|---|
| content | 필수, 최소 1자, 최대 500자 |

유효성 검사 실패 시 응답:
```json
{
  "code": "VALIDATION_ERROR",
  "message": "문의 내용은 1자 이상 500자 이하로 입력해 주세요."
}
```

## 5. 향후 확장 고려 사항

현재 스펙에서는 구현하지 않지만, 아래 기능이 추가될 수 있습니다.

* 관리자용 문의 목록 조회 API (`GET /internal/customer/messages`)
* 답변 처리 상태 관리 (PENDING, ANSWERED)
* 이메일 알림 발송

인터페이스(Port)를 통해 구현체를 분리하여 이러한 확장에 유연하게 대응할 수 있도록 설계하십시오.

## 6. 구현 우선순위

1. CustomerMessage 도메인 및 JPA 설정
2. 문의 접수 API
3. 유효성 검사 및 에러 핸들링
