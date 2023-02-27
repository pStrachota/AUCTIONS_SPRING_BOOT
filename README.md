<div id="top"></div>

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]

<!-- PROJECT LOGO -->
<br />
<div align="center">


<h3 align="center">AUCTIONS_SPRING_BOOT_PROJECT</h3>

  <p align="center">
    Auction restfull app, created using spring boot in order to consolidate knowledge.
   
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About the project</a></li>
     <li><a href="#built-with">Built With</a></li>
    <li><a href="#features">Features</a></li>
    <li><a href="#business-rules">Business rules</a></li>
    <li><a href="#endpoints">Endpoints</a></li>
    <li><a href="#license">License</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
 Obviously, one can only learn given topic when they use it practically, that's why,
    after spending some time studying spring boot, rest api and overall creating web apps,
    I decided to make my own project that includes mentioned topics. 
    This app mock auction websites, it enable authenticated user to create two type of auctions: buy-now and bidding and for latter  option it allow other authenticated users to add bids for offer. Authentication and authorization are built with JWT. Redis is used for cache and mySQL is primary database. Add SSL with with RSA signature.
    More details are given below.

<p align="right">(<a href="#top">back to top</a>)</p>

## Built With

* [JDK 17](https://docs.microsoft.com/en-us/dotnet/csharp/)
* [Spring boot 3.0.0](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Lombok](https://www.projectlombok.org/features/all)
* [Hibernate](https://hibernate.org/orm/documentation/)
* [Docker](https://docs.docker.com/)
* [MSSQL Server](https://learn.microsoft.com/en-us/sql/)
* [Redis](https://redis.io/docs/)
* [Testcontainers](https://www.testcontainers.org/test_framework_integration/manual_lifecycle_control/)
* [Rest Assured](https://rest-assured.io/)
* [Swagger UI](https://swagger.io/docs/)
* [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
* [Java mail API](https://javaee.github.io/javamail/docs/api/)
* [Spec-arg-resolver](https://github.com/tkaczmarzyk/specification-arg-resolver)

<p align="right">(<a href="#top">back to top</a>)</p>


## Roles and permissions
### User can
- register an account (need to provide email, password and login)
- login to account
- delete own account
- change password
- refresh access token
- create auction that can be either *bidding* or *buy now*
(in the first case other users can add bids to raise the price,
and in the latter price is constant)
- delete and update own auction or bid
- get paginated and sorted results, for example
```
https://localhost/auctions?itemCategory=BOOK&priceFrom=0&priceTo=100&auctionType=BIDDING&itemStatus=USED&pageNo=0&sortBy=startingPrice&sortDir=asc
```
### Admin (besides all user permissions) can:
- make crud operations on all auctions
- delete user account
- get all bids for given bidding


## Detailed feature description
- When creating auction, user choose adequate category (e.g. sport, book) and item status (new, used)
- User can also specify auction end date (in days)
- When new bid is created, all users that earlier took part in the bidding
will be sent an email with new highest price
- Also when current highest bid is deleted, again all users that took part in licitation
will be sent an email with new highest price 


## Business rules
- User cannot create bid when auction is *buy now* type
- User cannot delete auction (of licitation type) when there are already bids for this auction
- Max auction time is 30 days
- User can only delete highest price bid for given licitation
- User can only delete his / her bid (it is checked via email address)
- And of course, wide range of validation (cannot apply negative price or auction end time cannot be in past etc.)

## Endpoints 
Swagger documentation available at https://localhost/swagger-ui/index.html#/, nonetheless detailed description
of most important endpoints is also below
![image](https://user-images.githubusercontent.com/81098347/221445354-a496f696-6b93-44a4-bcd8-45640c5208f6.png)
![image](https://user-images.githubusercontent.com/81098347/221445365-1fba9315-67d7-4dd2-900e-0eb5543dbfca.png)
![image](https://user-images.githubusercontent.com/81098347/221445898-246f3b78-76d7-47e1-842e-5b856a66aac0.png)


### Signup

```
POST https://localhost:443/api/auth/signup
Content-Type: application/json

{
  "email": "sample@mail.com",
  "username": "testUser",
  "password": "testPassword"
}
RESPONSE: HTTP 201 (Created)
{
  User created successfully!
}

```

### Login

```
POST https://localhost:443/api/auth/login
Content-Type: application/json

{
  "username": "testUser",
  "password": "testPassword"
}
RESPONSE: HTTP 200
{
  "token": "eyJhbGciOiJIUzUxMiJ9... rest of JWT,
  "refreshToken": "7f56b079-d0fe-4a0f-9ec3-073c37d7376c"
}
```

### Create buy now auction

```
POST https://localhost:443/api/app/auctions/buy-now
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9... rest of JWT

{
  "description": "creatine 400g orange flavour",
  "startingPrice": 40,
  "itemStatus": "NEW",
  "itemCategory": "SPORT",
  "daysToEndTime": 10,
  "premium": false
}

RESPONSE: HTTP 201 (Created)
{
    "auctionId": 7,
    "auctionType": "buy_now",
    "description": "creatine 400g orange flavour",
    "startingPrice": 40,
    "itemStatus": "NEW",
    "itemCategory": "SPORT",
    "auctionEndTime": "2023-03-09T01:45:51.134745",
    "auctionStartTime": "2023-02-27T01:45:51.1358006",
    "premium": false
}
```

### Create bidding auction

```
POST https://localhost:443/auctions/bidding
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9... rest of JWT

{
  "description": "black hat",
  "startingPrice": 40,
  "itemStatus": "USED",
  "itemCategory": "CLOTHES",
  "daysToEndTime": 5,
  "limited": true
}
RESPONSE: HTTP 201 (Created)
{
    "auctionId": 12,
    "auctionType": "bidding",
    "description": "black hat",
    "startingPrice": 40,
    "itemStatus": "USED",
    "itemCategory": "CLOTHES",
    "auctionEndTime": "2023-03-04T02:07:33.1395604",
    "auctionStartTime": "2023-02-27T02:07:33.1395604",
    "currentPrice": 40,
    "bids": [],
    "limited": true
}
```

### Retrieve a paginated and sorted auction list

```
https://localhost/auctions?priceFrom=0&priceTo=500&auctionType=BUY_NOW&itemStatus=NEW&pageNo=0&sortBy=startingPrice&sortDir=asc
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9... rest of JWT

Response: HTTP 200
[
  {
    "auctionId": 7,
    "auctionType": "buy_now",
    "description": "creatine 400g orange flavour",
    "startingPrice": 40,
    "itemStatus": "NEW",
    "itemCategory": "SPORT",
    "auctionEndTime": "2023-03-09T01:45:51.134745",
    "auctionStartTime": "2023-02-27T01:45:51.135801",
    "premium": false
  },
  {
    "auctionId": 2,
    "auctionType": "buy_now",
    "description": "Mens jeans size L",
    "startingPrice": 59.99,
    "itemStatus": "NEW",
    "itemCategory": "CLOTHES",
    "auctionEndTime": "2023-03-09T00:14:10.263333",
    "auctionStartTime": "2023-02-27T00:14:10.263333",
    "premium": true
  },
  {
    "auctionId": 5,
    "auctionType": "buy_now",
    "description": "20kg dumbells",
    "startingPrice": 79.99,
    "itemStatus": "NEW",
    "itemCategory": "SPORT",
    "auctionEndTime": "2023-03-09T00:14:10.263333",
    "auctionStartTime": "2023-02-27T00:14:10.263333",
    "premium": false
  }
]
```

### Create new bid for bidding with id 1

```
POST https://localhost:443/auctions/1/bids
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9... rest of JWT

{
  "bidPrice": 100
}

RESPONSE: HTTP 201 (Created)
{
    "bidId": 6,
    "bidPrice": 100,
    "bidTime": "2023-02-27T01:50:07.9405061"
}
```

### Examples of exceptions handling of unauthorized/unauthenticated/incorrect data

### User can delete only own bid

```
DELETE https://localhost:443/auctions/1/bids/1
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9... rest of JWT

RESPONSE: HTTP 400 (Bad Request)
{
    "exceptionMessage": "You can only delete bid with highest price",
    "httpStatus": "BAD_REQUEST",
    "errors": [
        "uri=/auctions/1/bids/1"
    ],
    "timestamp": "2023-02-27T01:55:36.1992742"
}
```

### Missing price field when creating new buy now type of auction

### Create buy now auction

```
POST https://localhost:443/api/app/auctions/buy-now
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9... rest of JWT
{
  "description": "iPhone 4s",
  "itemStatus": "USED",
  "itemCategory": "ELECTRONICS",
  "daysToEndTime": 15
}

RESPONSE: HTTP 400 (Bad Request)
{
    "exceptionMessage": "INVALID ARGUMENT PASSED",
    "httpStatus": "BAD_REQUEST",
    "errors": [
        "startingPrice: Price must be greater than 0 and less than 1000000"
    ],
    "timestamp": "2023-02-27T01:59:15.4280781"
}
```

### User with incorrect JWT tries to add new auction

```
POST https://localhost:443/api/app/auctions/buy-now
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9... rest of incorrect JWT

{
  "description": "barbell",
  "startingPrice": 80,
  "itemStatus": "NEW",
  "itemCategory": "SPORT",
  "daysToEndTime": 10,
  "premium": true
}

RESPONSE: HTTP 401 (Unauthorized)
{
    "exceptionMessage": "Full authentication is required to access this resource",
    "httpStatus": "UNAUTHORIZED",
    "errors": [
        "uri=/error"
    ],
    "timestamp": "2023-02-27T02:08:48.1763675"
}
```

<p align="right">(<a href="#top">back to top</a>)</p>







<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#top">back to top</a>)</p>







<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/pStrachota/AUCTIONS_SPRING_BOOT_PROJECT.svg?style=for-the-badge
[contributors-url]: https://github.com/pStrachota/AUCTIONS_SPRING_BOOT_PROJECT/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/pStrachota/AUCTIONS_SPRING_BOOT_PROJECT.svg?style=for-the-badge
[forks-url]: https://github.com/pStrachota/AUCTIONS_SPRING_BOOT_PROJECT/network/members
[stars-shield]: https://img.shields.io/github/stars/pStrachota/AUCTIONS_SPRING_BOOT_PROJECT.svg?style=for-the-badge
[stars-url]: https://github.com/pStrachota/AUCTIONS_SPRING_BOOT_PROJECT/stargazers
[issues-shield]: https://img.shields.io/github/issues/pStrachota/AUCTIONS_SPRING_BOOT_PROJECT.svg?style=for-the-badge
[issues-url]: https://github.com/pStrachota/AUCTIONS_SPRING_BOOT_PROJECT/issues
[license-shield]: https://img.shields.io/github/license/pStrachota/AUCTIONS_SPRING_BOOT_PROJECT.svg?style=for-the-badge
[license-url]: https://github.com/pStrachota/AUCTIONS_SPRING_BOOT_PROJECT/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]: https://linkedin.com/in/linkedin_username
[product-screenshot]: images/screenshot.png


