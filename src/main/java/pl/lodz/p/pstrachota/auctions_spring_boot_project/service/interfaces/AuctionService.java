package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

import java.util.List;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionUpdate;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;

public interface AuctionService {

    Auction createAuction(AuctionRequest auctionRequest);

    List<Auction> getAllAuctions();

    Auction deleteAuction(Long id);

    Auction updateAuction(Long id, AuctionUpdate auctionUpdate);
}

