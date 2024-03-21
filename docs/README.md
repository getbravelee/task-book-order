# 기능 요구 사항
- 도서 목록 API -> 도서에 대한 정보 조회
    - 모든 도서 항목에 대해 도서 정보, 할인 금액 등을 포함한 응답을 반환
      - 응답에 반드시 적용된 할인 내용이 포함되어 있어야 함
    - DB는 인메모리로 추상화해서 관리


- 도서 구매 API
  - 결제 방식과 상품ID를 필수 입력으로 받기
    - 한 번에 1 종류의 상품만 구입한다고 가정
    - 상품 재고는 무한하다 가정
  - 결제 결과를 반환

# 도메인 요구 사항
- 카드 | 현금 2가지 결재 방법
- 할인 정책
  - 매주 금요일은 개발 category 10% 추가 할인
  - 교양 category 항상 1500원 할인

# 프로그래밍 요구 사항
- java/kotlin 익숙한 개발 언어를 선택하여 개발해주세요.
- 각 기능 및 제약사항에 대한 단위테스트를 반드시 작성합니다.
- application을 배포하기 위한 Dockerfile이 반드시 작성되어야합니다.
- DB는 인메모리로 추상화하여 구현해주세요
- 결재와 같이 모호한 부분은 스펙만 정의하고, 인터페이스를 통해서 Mocking 해주세요.
- 그 외 필요하다고 판단되는 정책은 스스로 정의하셔도 좋습니다.

# 기능 수행
- Domain : 비즈니스 도메인 객체, 예)회원, 주문, 쿠폰 등 주로 DB에 저장하고 관리
  - Order
    - Long orderId
    - List<Book> books (도서 목록)
    - Payment payment (결제 정보)
  - Book
    - Long bookId
    - String name
    - String category
    - double price
  - Payment
    - Long paymentId
    - String method (카드 또는 현금)
    - double amount (결제 금액)
  - Discount
    - Long discountId
    - String category
    - double amount

  

- Repository : 각 도메인 클래스에 대한 CRUD, 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
  - OrderRepository
  - BookRepository
  - PaymentRepository
  - DiscountRepository
  

- Service : 핵심 비지니스 로직 구현
  - 주문 OrderService
  - 조회 BookService
  - 결제 PaymentService
  - 할인 DiscountService
  

- Controller : 클라이언트가 호출
  - BookController



