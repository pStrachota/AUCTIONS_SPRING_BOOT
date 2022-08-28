package pl.lodz.p.pstrachota.auctions_spring_boot_project.repository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Pageable;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemCategory;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findByItemCategory(ItemCategory itemCategory, Pageable pageable);
    List<Auction> findByDescriptionContains(String description, Pageable pageable);
    List<Auction> findAllByCurrentPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);
}
