package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

import java.math.BigDecimal;
import java.util.List;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemCategory;

public interface AuctionService {

    Auction createAuction(AuctionRequest auctionRequest);

    List<Auction> getAllAuctions(int pageNo, String sortBy, String sortDir);

    Auction deleteAuction(Long id);

    Auction updateAuction(Long id, AuctionUpdate auctionUpdate);

    List<Auction> findByDescriptionContains(String description, int page);

    List<Auction> findByItemCategory(ItemCategory itemCategory, int page);

    List<Auction> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, int page, String sortDir);
}

