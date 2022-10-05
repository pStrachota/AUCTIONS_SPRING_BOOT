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
     <li><a href="#how-to-run">How to run</a></li>
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
    This app mock auction websites, it enable user to create auctions and when auction type is licitation
    it also allow other users to take part in product bidding. More details are given below.

<p align="right">(<a href="#top">back to top</a>)</p>

## Built With

* [JDK 17](https://docs.microsoft.com/en-us/dotnet/csharp/)
* [Spring boot 2.7.3](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [Lombok](https://www.projectlombok.org/features/all)
* [H2 database](https://www.h2database.com/)
* [Swagger UI](https://swagger.io/docs/)
* [Hibernate](https://hibernate.org/orm/documentation/)
* [Mockito](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)
* [Java mail API](https://javaee.github.io/javamail/docs/api/)
* [Spec-arg-resolver](https://github.com/tkaczmarzyk/specification-arg-resolver)

<p align="right">(<a href="#top">back to top</a>)</p>

## How to run
- Clone this repository
- Open project base folder in terminal
- Run following command:
``` 
mvn spring-boot:run
```

## Features
- User can create auction that can be either *licitation* or *buy now*
(in the first case other users can add bids to raise the price,
and in the latter price is constant)
- When creating auction, user choose adequate category (e.g. sport, book) and item status (new, used)
- User can also specify auction end date (in days)
- When new bid is created, all users that earlier took part in the licitation
will be sent an email with new highest price
- Also when current highest bid is deleted, again all users that took part in licitation
will be sent an email with new highest price 
- User can get paginated and sorted results, for example
```
http://localhost:8080/auctions?itemCategory=sport&pageNo=0&sortBy=currentPrice&sortDir=asc
```

## Business rules
- User cannot create bid when auction is *buy now* type
- User cannot delete auction (of licitation type) when there are already bids for this auction
- Max auction time is 30 days
- User can only delete highest price bid for given licitation
- User can only delete his / her bid (it is checked via email address)
- And of course, wide range of validation (cannot apply negative price or auction end time cannot be in past etc.)

## Endpoints

### Create buy now auction

```
POST /auctions
Content-Type: application/json

{
  "email": "sample@mail.com",
  "description": "creatine 400g orange flavour",
  "auctionType": "BUY_NOW",
  "startingPrice": 40,
  "itemStatus": "NEW",
  "itemCategory": "SPORT",
  "daysToEndTime": 10
}

RESPONSE: HTTP 201 (Created)
{
  "id": 7,
  "email": "sample@mail.com",
  "description": "creatine 400g orange flavour",
  "currentPrice": 40,
  "startingPrice": 40,
  "auctionType": "BUY_NOW",
  "itemStatus": "NEW",
  "itemCategory": "SPORT",
  "auctionEndTime": "2022-09-08T21:07:44.4526542",
  "auctionStartTime": "2022-08-29T21:07:44.4536646"
}
```

### Retrieve a paginated and sorted auction list

```
http://localhost:8080/auctions?pageNo=0&sortBy=currentPrice&sortDir=desc

Response: HTTP 200
[
  {
    "id": 3,
    "email": "third@mail.com",
    "description": "iPhone 5s",
    "currentPrice": 140,
    "startingPrice": 140,
    "auctionType": "BIDDING",
    "itemStatus": "USED",
    "itemCategory": "ELECTRONICS",
    "auctionEndTime": "2022-09-11T21:05:48.916433",
    "auctionStartTime": "2022-08-29T21:05:48.916433"
  },
  {
    "id": 2,
    "email": "second@mail.com",
    "description": "Mens jeans size L",
    "currentPrice": 40.5,
    "startingPrice": 40.5,
    "auctionType": "BUY_NOW",
    "itemStatus": "USED",
    "itemCategory": "CLOTHES",
    "auctionEndTime": "2022-09-05T21:05:48.916433",
    "auctionStartTime": "2022-08-29T21:05:48.916433"
  },
  {
    "id": 7,
    "email": "sample@mail.com",
    "description": "creatine 400g orange flavour",
    "currentPrice": 40,
    "startingPrice": 40,
    "auctionType": "BUY_NOW",
    "itemStatus": "NEW",
    "itemCategory": "SPORT",
    "auctionEndTime": "2022-09-08T21:07:44.452654",
    "auctionStartTime": "2022-08-29T21:07:44.453665"
  },
  other results...
```

### Create new bid for licitation with id 1

```
POST http://localhost:8080/auctions/1/bids
Content-Type: application/json

{
  "email": "bidSample@mail.com",
  "bidPrice": 50
}

RESPONSE: HTTP 201 (Created)
{
  "id": 1,
  "relatedOfferId": 1,
  "email": "bidSample@mail.com",
  "bidPrice": 50,
  "bidTime": "2022-08-29T21:11:06.0843201"
}
```
Other important endpoints:
| URL                                | Description                              |
| ---------------------------------- | ---------------------------------------- |
| `PUT /auctions/{id}`                   | Update auction.      |
| `DELETE /auctions/{id}`   | Delete auction. |
| `DELETE /auctions/{id}/bids/{bidId}`        | Delete bid. |

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


