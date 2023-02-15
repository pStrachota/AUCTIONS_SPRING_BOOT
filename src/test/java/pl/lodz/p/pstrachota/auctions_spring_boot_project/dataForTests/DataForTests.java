package pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests;

import java.math.BigDecimal;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.BidRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.AuctionType;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemStatus;

public class DataForTests {

    public static String url = "/auctions";

    public static AuctionRequest testAuctionRequestCorrectParamsBidding = AuctionRequest.builder()
            .email("sample@mail.com")
            .daysToEndTime(7)
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .itemStatus(ItemStatus.NEW)
            .auctionType(AuctionType.BIDDING)
            .startingPrice(BigDecimal.valueOf(10.0))
            .build();

    public static AuctionRequest testAuctionRequestCorrectParamsBuyNow = AuctionRequest.builder()
            .email("sample@mail.com")
            .daysToEndTime(7)
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .itemStatus(ItemStatus.NEW)
            .auctionType(AuctionType.BUY_NOW)
            .startingPrice(BigDecimal.valueOf(10.0))
            .build();

    public static AuctionRequest testAuctionRequestIncorrectEndTime = AuctionRequest.builder()
            .email("sample@mail.com")
            .daysToEndTime(-1)
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .itemStatus(ItemStatus.NEW)
            .auctionType(AuctionType.BIDDING)
            .startingPrice(BigDecimal.valueOf(10.0))
            .build();

    public static AuctionRequest testAuctionRequestIncorrectEndTime2 = AuctionRequest.builder()
            .email("sample@mail.com")
            .daysToEndTime(0)
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .itemStatus(ItemStatus.NEW)
            .auctionType(AuctionType.BIDDING)
            .startingPrice(BigDecimal.valueOf(10.0))
            .build();

    public static AuctionRequest testAuctionRequestIncorrectPrice = AuctionRequest.builder()
            .email("sample@mail.com")
            .daysToEndTime(5)
            .description("Sample description")
            .itemCategory(ItemCategory.OTHER)
            .itemStatus(ItemStatus.NEW)
            .auctionType(AuctionType.BIDDING)
            .startingPrice(BigDecimal.valueOf(-10.0))
            .build();

    public static BidRequest testBidRequestCorrectParams = BidRequest.builder()
            .email("sample@mail.com")
            .bidPrice(BigDecimal.valueOf(20.0))
            .build();

    public static BidRequest testBidRequestIncorrectPrice = BidRequest.builder()
            .email("sample@mail.com")
            .bidPrice(BigDecimal.valueOf(-5.0))
            .build();
}
