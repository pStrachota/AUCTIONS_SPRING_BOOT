package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper;

import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import java.time.LocalDateTime;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;

public class AuctionDtoMapper {

    private AuctionDtoMapper() {
    }

    public static Auction mapAuctionRequestToAuction(AuctionRequest auctionRequest) {
        return Auction.builder()
                .email(auctionRequest.getEmail())
                .auctionEndTime(LocalDateTime.now().plusDays(auctionRequest.getDaysToEndTime()))
                .description(auctionRequest.getDescription())
                .itemCategory(auctionRequest.getItemCategory())
                .itemStatus(auctionRequest.getItemStatus())
                .auctionType(auctionRequest.getAuctionType())
                .startingPrice(auctionRequest.getStartingPrice())
                .currentPrice(auctionRequest.getStartingPrice())
                .auctionStartTime(LocalDateTime.now())
                .build();
    }

}
