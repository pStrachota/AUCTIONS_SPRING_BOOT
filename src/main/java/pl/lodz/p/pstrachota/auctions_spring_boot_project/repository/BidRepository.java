package pl.lodz.p.pstrachota.auctions_spring_boot_project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Bid;

public interface BidRepository extends JpaRepository<Bid, Long> {

    List<Bid> findByBiddingAuctionId(Long biddingId);
}
