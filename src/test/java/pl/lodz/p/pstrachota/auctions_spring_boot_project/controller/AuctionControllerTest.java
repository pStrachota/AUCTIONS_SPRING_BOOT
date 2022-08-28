package pl.lodz.p.pstrachota.auctions_spring_boot_project.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testAuctionRequestCorrectParamsBidding;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.url;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectDateException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectPriceException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.AuctionService;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper.AuctionDtoMapper;


@WebMvcTest(AuctionController.class)
class AuctionControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;


    @MockBean
    AuctionService mockAuctionServiceImpl;


    @Test
    void createOffer_when_parameters_are_correct() throws Exception {

        when(mockAuctionServiceImpl.createAuction(any(AuctionRequest.class)))
                .thenReturn(AuctionDtoMapper.mapAuctionRequestToAuction(
                        testAuctionRequestCorrectParamsBidding));

        mockMvc.perform(
                post(url)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(
                                testAuctionRequestCorrectParamsBidding))
        ).andExpect(status().isCreated())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.currentPrice").value(10.0))
                .andExpect(jsonPath("$.description").value("Sample description"));
    }

    @Test
    void createOffer_when_minPrice_is_less_than_zero() throws Exception {

        when(mockAuctionServiceImpl.createAuction(any(AuctionRequest.class)))
                .thenThrow(new IncorrectPriceException("Min price cannot be negative"));

        mockMvc.perform(
                post(url)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(
                                testAuctionRequestCorrectParamsBidding))
        ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.exceptionMessage").value("Min price cannot be negative"));

    }

    @Test
    void createOffer_when_aucitonEndTime_is_earlier_than_now() throws Exception {

        when(mockAuctionServiceImpl.createAuction(any(AuctionRequest.class)))
                .thenThrow(new IncorrectDateException("Auction end time cannot be in the past"));


        mockMvc.perform(
                post(url)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(
                                testAuctionRequestCorrectParamsBidding))
        ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.httpStatus").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.exceptionMessage")
                        .value("Auction end time cannot be in the past"));

    }
}