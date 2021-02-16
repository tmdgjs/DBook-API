# DBook API

### 1. User

로그인 / 회원가입 API



#### 1.1 로그인

##### Request

```java
POST http://localhost:8080/users/logins
```

###### Request Body (application/json)

| Field        | Description                   |
| ------------ | ----------------------------- |
| **email**    | 유저 이메일 ( 사용할 아이디 ) |
| **password** | 비밀번호                      |

##### Response

| Field       | Description |
| ----------- | ----------- |
| **code**    | 응답 코드   |
| **message** | 응답 메시지 |
| **data**    | 응답 데이터 |

```java
{
  "code": 200,
  "message": "",
  "object": {
  	"token" : {TOKEN},
  	"email" : {EMAIL}
  },
}
```



#### 1.2 회원가입

##### Request

```java
POST http://localhost:8080/users/signups
```

###### Request Body (application/json)

| Field         | Description                   |
| ------------- | ----------------------------- |
| **email**     | 유저 이메일 ( 사용할 아이디 ) |
| **password**  | 비밀번호                      |
| **name**      | 이름                          |
| profileFileNo | 프로필 이미지 번호            |
| libraryName   | 내 서재 이름                  |

##### Response

| Field       | Description |
| ----------- | ----------- |
| **code**    | 응답 코드   |
| **message** | 응답 메시지 |
| **data**    | 응답 데이터 |

```java
{
  "code": 200,
  "message": "",
  "object": {RequestBody}
}
```



#### 1.3 프로필 사진 업로드

##### Request

```java
POST http://localhost:8080/users/uploads/images
```

###### RequestPart (formData)

| Field    | Description      |
| -------- | ---------------- |
| **file** | 업로드 할 이미지 |

##### Response

| Field       | Description |
| ----------- | ----------- |
| **code**    | 응답 코드   |
| **message** | 응답 메시지 |
| **data**    | 응답 데이터 |

```java
{
  "code": 200,
  "message": "",
  "object": {FILE_IMAGE_NO},
}
```



#### 1.4 프로필 사진 (실제 이미지 파일) 가져오기

##### Request

```java
GET http://localhost:8080/users/images?imageNo={IMAGE_NO}
```

###### RequestParam (query)

| Field       | Description |
| ----------- | ----------- |
| **imageNo** | 이미지 번호 |

##### Response

| Field       | Description |
| ----------- | ----------- |
| **code**    | 응답 코드   |
| **message** | 응답 메시지 |
| **data**    | 응답 데이터 |

```java
{
  "code": 200,
  "message": "",
  "object": {FILE_IMAGE_NO},
}
```



### 2. EBook

책 리스트 / 책 파일 검색 API



#### 2.1 카테고리 별 책 리스트

##### Request

```java
GET http://localhost:8080/ebooks/categorys?category={CATEGORY_NO}
```

###### RequestParam (query)

| Field        | Description   |
| ------------ | ------------- |
| **category** | 카테고리 번호 |

##### Response

| Field       | Description                          |
| ----------- | ------------------------------------ |
| **code**    | 응답 코드                            |
| **message** | 응답 메시지                          |
| **data**    | 응답 데이터 ( 해당 카테고리 리스트 ) |

```java
{
  "code": 200,
  "message": "",
  "object": {BOOK_LIST},
}
```



#### 2.2 공유된 책 리스트

##### Request

```java
GET http://localhost:8080/ebooks/shares
```

###### RequestHeader (Header)

| Field             | Description |
| ----------------- | ----------- |
| **Authorization** | 토큰        |

##### Response

| Field       | Description |
| ----------- | ----------- |
| **code**    | 응답 코드   |
| **message** | 응답 메시지 |
| **data**    | 응답 데이터 |

```java
{
  "code": 200,
  "message": "",
  "object": {BOOK_LIST},
}
```



#### 2.3 책 파일

##### Request

```java
GET http://localhost:8080/ebooks/files/?ebookNo={EBOOK_NO}
```

###### RequestParam (query)

| Field       | Description |
| ----------- | ----------- |
| **ebookNo** | 책 번호     |

##### Response

| Field       | Description |
| ----------- | ----------- |
| **code**    | 응답 코드   |
| **message** | 응답 메시지 |
| **data**    | 응답 데이터 |

```java
{
  "code": 200,
  "message": "",
  "object": {
  	"ebookNo" : {EBOOK_NO},
    "file"    : {FILE}
  },
}
```



### 3. Library

내 서재 정보 API



#### 3.1 내 서재 책 리스트

##### Request

```java
GET http://localhost:8080/librarys
```

###### RequestHeader (Header)

| Field             | Description |
| ----------------- | ----------- |
| **Authorization** | 토큰        |

##### Response

| Field       | Description |
| ----------- | ----------- |
| **code**    | 응답 코드   |
| **message** | 응답 메시지 |
| **data**    | 응답 데이터 |

```java
{
  "code": 200,
  "message": "",
  "object": {BOOK_LIST},
}
```



#### 3.2 내 서재 이름

##### Request

```java
GET http://localhost:8080/librarys/names
```

###### RequestHeader (Header)

| Field             | Description |
| ----------------- | ----------- |
| **Authorization** | 토큰        |

##### Response

| Field       | Description |
| ----------- | ----------- |
| **code**    | 응답 코드   |
| **message** | 응답 메시지 |
| **data**    | 응답 데이터 |

```java
{
  "code": 200,
  "message": "",
  "object": {LIBRARY_NAME}
}
```