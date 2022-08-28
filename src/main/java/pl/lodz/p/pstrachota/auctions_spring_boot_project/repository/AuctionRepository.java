package pl.lodz.p.pstrachota.auctions_spring_boot_project.repository;

import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

}
