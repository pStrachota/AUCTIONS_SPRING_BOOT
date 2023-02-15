package pl.lodz.p.pstrachota.auctions_spring_boot_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Auction;


public interface AuctionRepository extends PagingAndSortingRepository<Auction, Long>,
        JpaSpecificationExecutor<Auction>, JpaRepository<Auction, Long> {

}
