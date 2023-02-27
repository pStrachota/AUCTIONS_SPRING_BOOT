package pl.lodz.p.pstrachota.auctions_spring_boot_project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Bid;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByBiddingAuctionId(Long biddingId);
}
