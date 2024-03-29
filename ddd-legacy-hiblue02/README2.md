# 키친포스

## 퀵 스타트

```sh
cd docker
docker compose -p kitchenpos up -d
```
## 요구사항
### 메뉴그룹
  - [x] 메뉴 생성시 이름은 필수로 값이 있어야되고 메뉴 그룹을 생성한다.
### 메뉴
- 생성
  - [x] 메뉴를 생성하려면 메뉴 그룹과 상품이 미리 등록되어 있어야 합니다.
  - [x] 메뉴 생성할 때는 메뉴의 가격은 반드시 값을 입력해야 하며, 메뉴의 가격은 0 이상이어야 합니다.
  - [x] 메뉴를 생성 할 경우 등록된 메뉴그룹의 id가 있어야된다.
  - [x] 메뉴에 대한 메뉴구성상품 목록 데이터 값이 무조건 있어야 된다.
  - [x] 메뉴에 대한 메뉴구성상품 목록과 상품의 목록의 개수와 같아야된다. 
  - [x] 메뉴에 있는 상품의 개수가 0 보다 크거나 같아야 된다.
  - [x] 메뉴에 있는 상품이 있어야된다. ( 없으면 NoSuchElementException 오류)
  - [x] 메뉴의 가격이 상품의 수량과 가격을 곱하고 상품들을 합친 값보다 작거나 같아야된다.  
  - [x] 메뉴 생성할때 메뉴이름은 필수 값이고 욕설이 들어가면 안된다.
  - [x] 메뉴의 필수 값들을 넣어주고 저장한다.
- 변경
  - [x] 가격이 필수로 있어야 되고 , 0보다 커야됩니다.
  - [x] 메뉴테이블에 등록된 메뉴id가 있어야됩니다.
  - [x] 메뉴 가격은 상품 가격과 상품 개수를 곱한 값 보다  작거나 같아야 된다.
  - [x] 메뉴의 가격을 바꾼다.
- 화면에서 메뉴 보이기
  - [x] 메뉴 테이블에 메뉴 id 가 등록 되어 있어야 됩니다.
  - [x] 메뉴 가격이 상품 개수와 상품 가격을 곱한 값 보다 작거나 같아야 됩니다.
  - [x] 메뉴 활성화
- 화면에서 메뉴 없애기
  - [x] 메뉴테이블에 메뉴가 있어야 합니다.
### 상품
- 생성
- [x] 상품을 생성할 때는 반드시 값을 입력해야 하며, 상품 가격은 0 이상이어야 합니다.
- [x] 상품을 생성할 때는 반드시 이름 값을 입력해야 하며, 상품의 이름에는 욕설 들어가면 안된다.
- 변경
- [x] 상품 변경 할때 상품의 가격은 반드시 있어야 되고, 0 이상이어야 합니다.
- [x] 상품 변경할 때는 상품의 ID 값이 미리 등록 되어 있어야 된다.
- [x] 메뉴 가격이 메뉴에 등록된 상품의 가격과 상품 개수를 곱한 가격보다 크면 메뉴를 화면에서 뺀다.

### 주문
- 생성
  - 공통
    - [x] 주문 생성할 때 배달 주문인지 , 매장 주문인지, 포장 주문인지 3개 중에 한개는 필수 값으로 입력해야 한다.
    - [x] 주문 생성할 때 메뉴 목록을 필수 값으로 입력해야 한다.
    - [x] 주문 생성할 때 메뉴 목록과 등록되어 있는 메뉴의 개수가 같아야 된다.
    - [x] 메뉴에 등록이 된 메뉴를 주문해야 된다.
    - [x] 전시가 되어 있는 메뉴를 주문 해야된다.
    - [x] 등록된 메뉴의 가격과 주문한 메뉴의 가격이 같아야 된다.
  - 배달 주문
    - [x] 주문 생성 시 배달이나 포장일 때 주문한 메뉴 개수가  0 이상이어야 한다.
    - [x] 배달 주문을 생성할 때는 반드시 배달지 주소가 필요합니다.
  - 매장 주문
    - [x] 매장에서 주문할 때 등록된 주문테이블 id가 반드시 있어야 한다. 
    - [x] 매장에서 주문할 때 주문테이블이 사용중이여야 한다.
  - 포장 주문
    - [x] 주문 생성 시 배달이나 포장일 때 주문한 메뉴 개수가  0 이상이어야 한다.
- 주문 수락한 경우
  - 공통 ,매장 , 포장
    - [x] 주문 수락할 때 주문 번호(id)가 있어야 한다.
    - [x] 주문 상태가 대기중(WAITING) 이어야 한다.
    - [x] 주문 상태를 수락 상태로 변경한다.
  - 배달 주문
    - [x] 주문 수락할 때 주문 번호(id)가 있어야 한다.
    - [x] 주문 상태가 대기중(WAITING) 이어야 한다.
    - [x] 주문 타입이 배달일 경우 배달 라이더한테 주문번호, 총 금액, 배달지 주소를 전달한다.
    - [x] 주문 상태를 수락 상태로 변경한다.
- 배달 시작할 때
  - 배달 주문
    - [x] 배달을 시작할 때 주문번호가 있어야 한다.
    - [x] 배달을 시작할 때 주문타입이 배달 타입이어야 한다.
    - [x] 배달을 시작할 때 주문상태가 음식이 배달기사한테 제공됨 상태여야 한다.
    - [x] 배달을 시작하면 주문상태를 배달중으로 변경한다.
- 배달 완료
  - 배달 주문
    - [x] 배달이 완료됐을 때 주문번호가 있어야 한다.
    - [x] 주문상태가 배달중이어야 한다.
    - [x] 주문상태가 배달 완료로 변경한다.
- 주문 처리 완료할 경우
    - [x] 주문 처리 완료할 경우 주문번호가 있어야 한다.
    - [x] 주문상태를 완료로 변경한다.
  - 배달 주문일 때
    - [x] 주문처리 완료할 경우 주문타입이 배달일 때 주문상태가 배달 완료여야 한다.
  - 포장 주문일 경우
    - [x] 주문처리 완료할 경우 주문타입이 포장이거나 매장에서 식사할 때 주문의 음식 상태가 제공됨이어야 한다.
  - 매장 주문일 경우 
    - [x] 주문처리 완료할 경우 주문타입이 포장이거나 매장에서 식사할 때 주문의 음식 상태가 제공됨이어야 한다.
    - [x] 주문처리 완료하는 경우 주문타입이 매장에서 주문일 때 주문상태가 완료이고 주문테이블이 사용중이 아니면, 주문테이블 인원수를 0변경하고 사용중이 아닌것으로 변경한다
    
### 주문테이블
- 주문테이블 생성
- [x] 주문테이블 생성 할때 반드시 이름이 있어야 한다.
- 주문테이블 앉을 때
- [x] 주문테이블에 앉을 때 주문테이블 id 가 등록 되어 있어야 한다.
- 주문테이블 치울 때
- [x] 주문테이블을 치울 때 주문테이블 id 가 등록 되어 있어야 한다.
- 주문테이블 인원수 변경
- [x] 주문테이블 인원수 변경 할때 주문테이블에 손님 수가 0 이상이어야 한다.
- [x] 주문테이블이 사용 중이어야 변경이 가능하다.

## 용어 사전

| 한글       | 영문명       | 설명                |
|----------|-----------|-------------------|
| 메뉴       | menu      |                   |
| 메뉴그룹     | menugroup |                   |
| 상품       | product   |                   |
| 메뉴 구성 상품 | menuProduct | 메뉴를 구성하는 상품       | 
| 주문       | orders    |                   |
| 주문테이블    | orderTable | 주문이 일어난 테이블       |
| 주문된메뉴    | orderLineItem | 주문된 메뉴            |
| 대기 중     | WAITING   | 주문 대기중 상태         |
| 수락됨      | ACCEPTED   | 주문 수락된 상태         |
| 제공됨      |  SERVED   | 주문의 음식이 제공된 상태    |
| 배달 중     | DELIVERING  | 주문의 음식이 배달 중인 상태  |
| 배달 완료    | DELIVERED  | 주문의 음식이 배달 완료인 상태 |
| 완료됨      |  COMPLETED  | 주문이 완료 되고 끝난 상태   |




## 모델링






### 주문
- 생성
- [x] 주문 생성할 때 배달 주문인지 , 매장 주문인지, 포장 주문인지 3개 중에 한개는 필수 값으로 입력해야 한다.
- [x] 주문 생성할 때 메뉴의 가격이 필수로 입력이 되어야 하고, 0 이상이어야한다.
- [x] 주문 생성할 때 메뉴 목록과 등록되어 있는 메뉴의 개수가 같아야 된다.
- [x] 주문 생성 시 배달이나 포장일 때 주문한 메뉴 개수가  0 이상이어야 한다.
- [x] 메뉴에 등록이 된 메뉴를 주문해야 된다.
- [x] 전시가 되어 있는 메뉴를 주문 해야된다.
- [x] 등록된 메뉴의 가격과 주문한 메뉴의 가격이 같아야 된다.
- [x] 배달 주문을 생성할 때는 반드시 배달지 주소가 필요합니다.
- [x] 매장에서 주문할 때 등록된 주문테이블 id가 반드시 있어야 한다.
- [x] 매장에서 주문할 때 주문테이블이 사용중이여야 한다.
- 주문 받을 경우
- [x] 주문 받을 때 주문 id가 있어야 한다.
- [x] 주문 상태가 WAITING 값이어야 한다. 다른 값(ACCEPTED, SERVED, DELIVERING, DELIVERED, COMPLETED)
- [x] 배달 라이더한테 주문번호, 총 금액, 배달지 주소를 전달한다.
- [x] 주문 상태를 ACCEPTED(수락) 상태로 변경한다.
- 음식을 제공할 경우
- [x] 제공할 때 주문 id가 있어야 한다.
- [x] 주문 상태가 ACCEPTED(수락) 이면 주문 상태를 SERVED(제공됨) 로 변경한다.
- 배달 시작할 때
- [x] 배달 시작할 때 주문번호가 있어야 한다.
- [x] 배달 시작할 때 주문타입이 배달 타입이어야 한다.
- [x] 배달 시작할 때 주문상태가 제공됨 상태여야 한다.
- [x] 배달 시작할 때 주문상태를 배달중으로 변경한다.
- 배달이 완료됐을 경우
- [x] 배달이 완료됐을 때 주문번호가 있어야 한다.
- [x] 주문상태가 배달중이어야 한다.
- [x] 주문상태가 배달 완료로 변경한다.
- 주문을 완료 됐을 때
- [x] 주문이 완료됐을 때 주문번호가 있어야 한다.
- [x] 주문타입이 배달일 때 주문상태가 배달 완료여야 한다.
- [x] 주문타입이 포장이거나 매장에서 식사할 때 주문 상태가 서빙 완료여야 한다.
- [x] 주문상태를 완료로 변경한다.
- [x] 주문타입이 매장에서 식사일 때 주문상태가 완료이고 주문테이블이 사용중이 아니면, 주문테이블 인원수를 0변경하고 사용중이 아닌것으로 변경한다.
