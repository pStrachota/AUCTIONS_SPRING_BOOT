package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper;

import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import java.time.LocalDateTime;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.BiddingRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.BuyNowRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Bidding;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.BuyNow;

public class AuctionDtoMapper {

    private AuctionDtoMapper() {
    }

    public static Auction mapAuctionRequestToAuction(AuctionRequest auctionRequest) {
        if (auctionRequest instanceof BuyNowRequest) {
            BuyNowRequest buyNowRequest = (BuyNowRequest) auctionRequest;
            return BuyNow.builder()
                    .auctionEndTime(LocalDateTime.now().plusDays(buyNowRequest.getDaysToEndTime()))
                    .description(buyNowRequest.getDescription())
                    .itemCategory(buyNowRequest.getItemCategory())
                    .itemStatus(buyNowRequest.getItemStatus())
                    .startingPrice(buyNowRequest.getStartingPrice())
                    .startingPrice(buyNowRequest.getStartingPrice())
                    .isPremium(buyNowRequest.isPremium())
                    .auctionStartTime(LocalDateTime.now())
                    .build();
        } else {
            BiddingRequest biddingRequest = (BiddingRequest) auctionRequest;
            return Bidding.builder()
                    .auctionEndTime(LocalDateTime.now().plusDays(biddingRequest.getDaysToEndTime()))
                    .description(biddingRequest.getDescription())
                    .itemCategory(biddingRequest.getItemCategory())
                    .currentPrice(biddingRequest.getStartingPrice())
                    .itemStatus(biddingRequest.getItemStatus())
                    .startingPrice(biddingRequest.getStartingPrice())
                    .isLimited(biddingRequest.isLimited())
                    .auctionStartTime(LocalDateTime.now())
                    .build();
        }
    }

}
