package pl.lodz.p.pstrachota.auctions_spring_boot_project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testAuctionRequestCorrectParamsBidding;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testAuctionRequestIncorrectEndTime;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testAuctionRequestIncorrectEndTime2;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testAuctionRequestIncorrectPrice;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectDateException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectPriceException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.NotFoundException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.AuctionRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.BidRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.impl.AuctionServiceImpl;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.AuctionService;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper.AuctionDtoMapper;

@ExtendWith(MockitoExtension.class)
class AuctionServiceImplTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Mock
    AuctionRepository mockAuctionRepository;
    @Mock
    BidRepository mockBidRepository;
    AuctionService auctionService;

    @BeforeEach
    void setUp() {
        auctionService = new AuctionServiceImpl(mockAuctionRepository, mockBidRepository);
    }

    @Test
    void should_create_new_auction_when_parameters_are_correct() {

        //given
        Auction expectedAuction = AuctionDtoMapper.mapAuctionRequestToAuction(
                testAuctionRequestCorrectParamsBidding);
        //when
        when(mockAuctionRepository.save(any(Auction.class))).thenReturn(expectedAuction);
        Auction actualAuction = auctionService.createAuction(testAuctionRequestCorrectParamsBidding);
        //then
        assertThat(expectedAuction).isEqualTo(actualAuction);

    }

    @Test
    void createAuction_when_auction_end_date_is_too_early() {
        assertThatThrownBy(() -> auctionService.createAuction(testAuctionRequestIncorrectEndTime))
                .isInstanceOf(IncorrectDateException.class)
                .hasMessageContaining("Auction end time cannot be in the past");

        assertThatThrownBy(() -> auctionService.createAuction(testAuctionRequestIncorrectEndTime2))
                .isInstanceOf(IncorrectDateException.class)
                .hasMessageContaining("Auction end time must be at least one day after start time");
    }

    @Test
    void createAuction_when_auction_price_is_negative() {
        assertThatThrownBy(() -> auctionService.createAuction(testAuctionRequestIncorrectPrice))
                .isInstanceOf(IncorrectPriceException.class)
                .hasMessageContaining("Min price cannot be negative");

    }
}