package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.mapper;

import java.time.LocalDateTime;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.BidRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Bid;

public class BidDtoMapper {

    private BidDtoMapper() {
    }

    public static BidRequest mapToBidRequest(Bid bid) {
        return BidRequest.builder()
                .bidPrice(bid.getBidPrice())
                .build();
    }

    public static Bid mapToBid(BidRequest bidRequest) {

        return Bid.builder()
                .bidPrice(bidRequest.getBidPrice())
                .bidTime(LocalDateTime.now())
                .build();
    }


}
