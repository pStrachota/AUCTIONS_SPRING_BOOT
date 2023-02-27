package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.relatedOfferId;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testBidRequestCorrectParams;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testBidRequestIncorrectPrice;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

class BidControllerTest extends BaseTest {

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
    void createBid_when_parameters_are_correct() {

        given().body(testBidRequestCorrectParams)
                .contentType("application/json")
                .when()
                .post("/auctions/{id}/bids", relatedOfferId)
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("bidId", equalTo(1))
                .body("bidPrice", equalTo(100.0F));
    }


    @Test
    @WithUserDetails("user3")
    void should_delete_user_bid() {

        given()
                .when()
                .delete("/auctions/{id}/bids/{bidId}", relatedOfferId, 2L)
                .then()
                .statusCode(200);
    }

    @Test
    @WithUserDetails("adminUser")
    void should_return_all_bid_when_user_id_admin() {

        given()
                .when()
                .get("/auctions/{id}/bids", relatedOfferId)
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("size()", equalTo(2))
                .body("[0].bidId", equalTo(1))
                .body("[0].bidPrice", equalTo(50.0F))
                .body("[1].bidId", equalTo(2))
                .body("[1].bidPrice", equalTo(60.0F));

    }

    @Test
    @WithUserDetails()
    void should_not_delete_bid_that_user_not_created() {

        given()
                .when()
                .delete("/auctions/{id}/bids/{bidId}", relatedOfferId, 2L)
                .then()
                .statusCode(403)
                .contentType("application/json")
                .body("exceptionMessage", equalTo("You can only delete your own bid"));

    }

    @Test
    void should_not_create_bid_when_user_is_not_authenticated() {

        given().body(testBidRequestCorrectParams)
                .contentType("application/json")
                .when()
                .post("/auctions/{id}/bids", relatedOfferId)
                .then()
                .statusCode(403);
    }

    @Test
    @WithUserDetails
    void should_not_create_bid_when_bidPrice_is_less_than_zero() {


        given().body(testBidRequestIncorrectPrice)
                .contentType("application/json")
                .when()
                .post("/auctions/{auctionId}/bids", relatedOfferId)
                .then()
                .statusCode(400)
                .contentType("application/json")
                .body("httpStatus", equalTo("BAD_REQUEST"))
                .body("errors[0]",
                        equalTo("bidPrice: Price must be greater than 0 and less than 1000000"));

    }
}