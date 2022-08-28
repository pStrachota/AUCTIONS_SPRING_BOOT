package pl.lodz.p.pstrachota.auctions_spring_boot_project.dataForTests;

import java.math.BigDecimal;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.BidRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.AuctionType;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemStatus;

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
            .daysToEndTime(7)
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

}
