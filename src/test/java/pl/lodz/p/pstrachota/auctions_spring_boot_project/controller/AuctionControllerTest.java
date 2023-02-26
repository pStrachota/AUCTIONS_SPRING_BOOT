package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testAuctionRequestIncorrectEndTime;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testAuctionUpdateCorrectParams;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testAuctionUpdateIncorrectParams;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testBiddingDtoCorrectParamsBidding;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testBiddingDtoIncorrectPrice;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testBuyNowDtoCorrectParamsBuyNow;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;


class AuctionControllerTest extends BaseTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @BeforeEach
    void setup() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
    }


    @Test
    @WithUserDetails
    void should_create_bidding_when_parameters_are_correct() {

        given().body(testBiddingDtoCorrectParamsBidding)
                .contentType("application/json")
                .when()
                .post("/auctions/bidding")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("currentPrice", equalTo(10F))
                .body("limited", equalTo(true))
                .body("bids", empty())
                .body("startingPrice", equalTo(10F))
                .body("description", equalTo("Sample description"))
                .body("itemStatus", equalTo("NEW"))
                .body("itemCategory", equalTo("OTHER"));
    }

    @Test
    @WithUserDetails
    void should_create_buy_now_when_parameters_are_correct() {

        given().body(testBuyNowDtoCorrectParamsBuyNow)
                .contentType("application/json")
                .when()
                .post("/auctions/buy-now")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("startingPrice", equalTo(10F))
                .body("description", equalTo("Sample description"))
                .body("itemStatus", equalTo("NEW"))
                .body("premium", equalTo(true))
                .body("itemCategory", equalTo("OTHER"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void should_update_auction_when_updated_parameters_are_correct() {


        given().body(testAuctionUpdateCorrectParams)
                .contentType("application/json")
                .when()
                .put("/auctions/{auctionId}", 1L)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("currentPrice", equalTo(40.0F))
                .body("startingPrice", equalTo(40.0F))
                .body("description", equalTo("Changed description"))
                .body("itemStatus", equalTo("USED"))
                .body("limited", equalTo(false))
                .body("itemCategory", equalTo("BOOK"));

    }


    @Test
    void should_not_create_auction_when_user_is_unauthorized() {

        given().body(testBiddingDtoCorrectParamsBidding)
                .contentType("application/json")
                .when()
                .post("/auctions/bidding")
                .then()
                .statusCode(403);

    }

    @Test
    void should_not_update_auction_when_user_is_unauthorized() {

        given().body(testAuctionUpdateCorrectParams)
                .contentType("application/json")
                .when()
                .put("/auctions/{auctionId}", 2L)
                .then()
                .statusCode(403);

    }

    @Test
    @WithUserDetails
    void should_not_update_auction_when_parameters_are_incorrect() {


        given().body(testAuctionUpdateIncorrectParams)
                .contentType("application/json")
                .when()
                .put("/auctions/{auctionId}", 2L)
                .then()
                .statusCode(400)
                .contentType("application/json")
                .body("errors[0]", equalTo("description: Description must be provided"));

    }

    @Test
    @WithUserDetails
    void should_not_create_auction_when_minPrice_is_less_than_zero() {

        given().body(testBiddingDtoIncorrectPrice)
                .contentType("application/json")
                .when()
                .post("/auctions/bidding")
                .then()
                .statusCode(400)
                .contentType("application/json")
                .body("httpStatus", equalTo("BAD_REQUEST"))
                .body("errors[0]",
                        equalTo("startingPrice: Price must be greater than 0 and less than 1000000"));

    }

    @Test
    @WithUserDetails
    void should_not_create_auction_when_auctionEndTime_is_earlier_than_now() {

        given().body(testAuctionRequestIncorrectEndTime)
                .contentType("application/json")
                .when()
                .post("/auctions/bidding")
                .then()
                .statusCode(400)
                .contentType("application/json")
                .body("httpStatus", equalTo("BAD_REQUEST"))
                .body("errors[0]", equalTo("daysToEndTime: Auction end time must be provided"));

    }

    @Test
    @WithUserDetails
    void should_not_delete_auction_with_bids() {

        given().contentType("application/json")
                .when()
                .delete("/auctions/{auctionId}", 1L)
                .then()
                .statusCode(400)
                .contentType("application/json")
                .body("exceptionMessage", equalTo("Cannot delete auction with bids"));

    }

    @Test
    @WithUserDetails
    void should_delete_auction_when_user_is_authorized_and_owns_auction() {

        given().contentType("application/json")
                .when()
                .delete("/auctions/{auctionId}", 3L)
                .then()
                .statusCode(200);
    }

    @Test
    @WithMockUser(username = "user2", roles = "USER")
    void should_not_delete_auction_when_user_is_authorized_but_does_not_own_auction() {

        given().contentType("application/json")
                .when()
                .delete("/auctions/{auctionId}", 1L)
                .then()
                .statusCode(403)
                .contentType("application/json")
                .body("httpStatus", equalTo("FORBIDDEN"))
                .body("exceptionMessage", equalTo("Cannot delete auction that is not yours"));
    }
}