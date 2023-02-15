package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.BidRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Bid;

public interface BidService {

    Bid createBid(BidRequest bidRequest, Long auctionId, UserDetails userDetails);

    Bid deleteBid(Long auctionId, Long bidId, UserDetails userDetails);

    List<Bid> getBidsForAuction(Long auctionId);
}
