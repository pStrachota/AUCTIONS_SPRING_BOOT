package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.BidRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Bid;

public interface BidService {

    Bid createBid(BidRequest bidRequest, Long auctionId);

    Bid deleteBid(Long auctionId, Long bidId);

}
