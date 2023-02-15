package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testBidRequestCorrectParams;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.controller.auction.BidController;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.BidRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectDateException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectPriceException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.impl.BidServiceImpl;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper.BidDtoMapper;

@WebMvcTest(BidController.class)
class BidControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BidServiceImpl bidServiceImpl;

    private long relatedOfferId = 1L;

    @Test
    void createBid_when_parameters_are_correct() throws Exception {

        when(bidServiceImpl.createBid(any(BidRequest.class), anyLong()))
                .thenReturn(BidDtoMapper.mapToBid(testBidRequestCorrectParams, relatedOfferId));

        mockMvc.perform(
                post("/auctions/{id}/bids", relatedOfferId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testBidRequestCorrectParams))
        ).andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(0L))
                .andExpect(jsonPath("$.relatedOfferId").value(relatedOfferId))
                .andExpect(jsonPath("$.bidPrice").value(20.0));
    }

    @Test
    void createBid_when_bidPrice_is_less_than_zero() throws Exception {

        when(bidServiceImpl.createBid(any(BidRequest.class), anyLong()))
                .thenThrow(new IncorrectPriceException("Min price cannot be negative"));

        mockMvc.perform(
                post("/auctions/{id}/bids", relatedOfferId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testBidRequestCorrectParams))
        ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.exceptionMessage").value("Min price cannot be negative"));

    }

    @Test
    void createBid_when_auction_has_ended() throws Exception {

        when(bidServiceImpl.createBid(any(BidRequest.class), anyLong()))
                .thenThrow(new IncorrectDateException("Auction has ended"));


        mockMvc.perform(
                post("/auctions/{id}/bids", relatedOfferId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(testBidRequestCorrectParams))
        ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.exceptionMessage")
                        .value("Auction has ended"));

    }
}