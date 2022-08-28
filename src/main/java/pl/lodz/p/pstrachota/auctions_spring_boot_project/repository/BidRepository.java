package pl.lodz.p.pstrachota.auctions_spring_boot_project.repository;

import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BidRepository extends JpaRepository<Bid, Long> {

}
