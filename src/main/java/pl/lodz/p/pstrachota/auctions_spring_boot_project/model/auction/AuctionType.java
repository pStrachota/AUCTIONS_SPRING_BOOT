package pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Auction type")
public enum AuctionType {

    BUY_NOW,
    BIDDING
}
