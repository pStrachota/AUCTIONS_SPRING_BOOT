package pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions;

public class WrongAuctionOwnerException extends RuntimeException {

    public WrongAuctionOwnerException(String message) {
        super(message);
    }

}
