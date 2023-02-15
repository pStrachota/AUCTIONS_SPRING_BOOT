package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.AuctionType;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemStatus;

public interface AuctionService {

    Auction createAuction(AuctionRequest auctionRequest);

    List<Auction> getAllAuctions(Specification<Auction> spec, int pageNo, String sortBy, String sortDir);

    Auction deleteAuction(Long id);

    Auction updateAuction(Long id, AuctionUpdate auctionUpdate);

    Auction getAuctionById(Long id);
}

