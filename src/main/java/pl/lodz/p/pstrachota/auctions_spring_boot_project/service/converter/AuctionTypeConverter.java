package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.AuctionType;

@Converter
public class AuctionTypeConverter implements AttributeConverter<AuctionType, String> {
    @Override
    public String convertToDatabaseColumn(AuctionType auctionType) {
        if (auctionType == null) {
            return null;
        }

        String auctionTypeString = null;

        switch (auctionType) {
            case BUY_NOW -> auctionTypeString = "BUY_NOW";
            case BIDDING -> auctionTypeString = "BIDDING";

        }
        return auctionTypeString;
    }

    @Override
    public AuctionType convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }

        AuctionType auctionType = null;

        switch (s) {
            case "BUY_NOW" -> auctionType = AuctionType.BUY_NOW;
            case "BIDDING" -> auctionType = AuctionType.BIDDING;
        }

        return auctionType;
    }
}
