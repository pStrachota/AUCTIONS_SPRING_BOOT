package pl.lodz.p.pstrachota.auctions_spring_boot_project.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testAuctionRequestIncorrectEndTime;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testBidRequestCorrectParams;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testBidRequestIncorrectPrice;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testBiddingDtoCorrectParams;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testBuyNowDtoCorrectParamsBuyNow;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests.DataForTests.testUserDetails;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.events.MailSenderPublisher;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectDateException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions.IncorrectPriceException;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Bid;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.AuctionRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.BidRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.repository.UserRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.impl.BidServiceImpl;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.BidService;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper.AuctionDtoMapper;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper.BidDtoMapper;

@ExtendWith(MockitoExtension.class)
class BidServiceImplTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Mock
    BidRepository mockBidRepository;
    @Mock
    UserRepository mockUserRepository;
    @Mock
    AuctionRepository mockAuctionRepository;
    @Mock
    MailSenderPublisher mockMailSenderPublisher;
    BidService bidService;


    @BeforeEach
    void setUp() {
        bidService = new BidServiceImpl(mockBidRepository, mockAuctionRepository,
                mockMailSenderPublisher, mockUserRepository);
    }

    @Test
    void should_throw_incorrect_price_ex_when_bid_price_is_lower_than_current_auction_price() {

        when(mockAuctionRepository.findById(1L)).thenReturn(
                Optional.of(AuctionDtoMapper.mapAuctionRequestToAuction(
                        testBiddingDtoCorrectParams)));
        when(mockUserRepository.findByUsername(testUserDetails.getUsername())).thenReturn(
                Optional.of(testUserDetails.getUser()));

        assertThatThrownBy(
                () -> bidService.createBid(testBidRequestIncorrectPrice, 1L, testUserDetails))
                .isInstanceOf(IncorrectPriceException.class)
                .hasMessage("Bid price must be greater than offer price");

    }

    @Test
    void should_throw_incorrect_date_ex_when_bid_time_is_later_than_auction_end_time() {

        when(mockAuctionRepository.findById(1L)).thenReturn(
                Optional.of(AuctionDtoMapper.mapAuctionRequestToAuction(
                        testAuctionRequestIncorrectEndTime)));


        when(mockUserRepository.findByUsername(testUserDetails.getUsername())).thenReturn(
                Optional.of(testUserDetails.getUser()));

        assertThatThrownBy(
                () -> bidService.createBid(testBidRequestCorrectParams, 1L, testUserDetails))
                .isInstanceOf(IncorrectDateException.class)
                .hasMessage("Auction has ended");

    }

    @Test
    void should_create_bid_when_params_are_correct() {

        Bid expectedBid = BidDtoMapper.mapToBid(testBidRequestCorrectParams);

        when(mockAuctionRepository.findById(1L)).thenReturn(
                Optional.of(AuctionDtoMapper.mapAuctionRequestToAuction(
                        testBuyNowDtoCorrectParamsBuyNow)));
        when(mockBidRepository.save(any(Bid.class))).thenReturn(expectedBid);
        Bid actualBid = bidService.createBid(testBidRequestCorrectParams, 1L, testUserDetails);

        assertThat(expectedBid).isEqualTo(actualBid);


    }
}