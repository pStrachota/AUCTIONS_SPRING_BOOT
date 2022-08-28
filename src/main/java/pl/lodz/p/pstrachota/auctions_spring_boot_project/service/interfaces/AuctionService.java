package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

import pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.AuctionRequest;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.Auction;

public interface AuctionService {

    Auction createAuction(AuctionRequest auctionRequest);
}

