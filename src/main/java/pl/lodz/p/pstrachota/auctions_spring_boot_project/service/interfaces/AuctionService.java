package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.Auction;

public interface AuctionService {

    Auction createAuction(AuctionRequest auctionRequest, UserDetails userDetails);

    List<Auction> getAllAuctions(Specification<Auction> spec, int pageNo, String sortBy, String sortDir);

    void deleteAuction(Long id, UserDetails userDetails);

    Auction updateAuction(Long id, AuctionUpdate auctionUpdate, UserDetails userDetails);

    Auction getAuctionById(Long id);
}

