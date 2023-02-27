package pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests;

import java.math.BigDecimal;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.BidRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.BiddingRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.BuyNowRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemStatus;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.security.UserDetailsImpl;

public class DataForTests {

    public static String url = "/auctions";

    public static UserDetailsImpl testUserDetails = UserDetailsImpl.builder()
            .id(1L)
            .email("sample@mail.com")
            .password("samplePassword")
            .build();

    public static BiddingRequest testBiddingDtoCorrectParams = BiddingRequest.builder()
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .daysToEndTime(7)
            .itemStatus(ItemStatus.NEW)
            .isLimited(true)
            .startingPrice(BigDecimal.valueOf(10.0))
            .build();


    public static BiddingRequest testBiddingDtoCorrectParamsBidding = BiddingRequest.builder()
            .daysToEndTime(7)
            .isLimited(true)
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .itemStatus(ItemStatus.NEW)
            .startingPrice(BigDecimal.valueOf(10.0))
            .build();

    public static AuctionUpdate testAuctionUpdateCorrectParams = AuctionUpdate.builder()
            .description("Changed description")
            .build();

    public static AuctionUpdate testAuctionUpdateIncorrectParams = AuctionUpdate.builder()
            .description("")
            .build();

    public static BuyNowRequest testBuyNowDtoCorrectParamsBuyNow = BuyNowRequest.builder()
            .daysToEndTime(7)
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .isPremium(true)
            .itemStatus(ItemStatus.NEW)
            .startingPrice(BigDecimal.valueOf(10.0))
            .build();
    public static BiddingRequest testAuctionRequestIncorrectEndTime = BiddingRequest.builder()
            .daysToEndTime(-1)
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .itemStatus(ItemStatus.NEW)
            .startingPrice(BigDecimal.valueOf(10.0))
            .build();
    public static BiddingRequest testBiddingDtoIncorrectEndTime2 = BiddingRequest.builder()
            .daysToEndTime(0)
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .itemStatus(ItemStatus.NEW)
            .startingPrice(BigDecimal.valueOf(10.0))
            .build();
    public static BiddingRequest testBiddingDtoIncorrectPrice = BiddingRequest.builder()
            .daysToEndTime(5)
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .itemStatus(ItemStatus.NEW)
            .startingPrice(BigDecimal.valueOf(-10.0))
            .build();

    public static Long relatedOfferId = 1L;

    public static BidRequest testBidRequestCorrectParams = BidRequest.builder()
            .bidPrice(BigDecimal.valueOf(100.0))
            .build();

    public static BidRequest testBidRequestIncorrectPrice = BidRequest.builder()
            .bidPrice(BigDecimal.valueOf(-5.0))
            .build();
}
