package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemStatus;

@Converter
public class ItemStatusConverter implements AttributeConverter<ItemStatus, String> {
    @Override
    public String convertToDatabaseColumn(ItemStatus itemStatus) {
        if (itemStatus == null) {
            return null;
        }

        String itemStatusString = null;

        switch (itemStatus) {
            case NEW -> itemStatusString = "NEW";
            case USED -> itemStatusString = "USED";
        }
        return itemStatusString;
    }

    @Override
    public ItemStatus convertToEntityAttribute(String s) {
        if (s == null) {
            return null;
        }

        ItemStatus itemStatus = null;

        switch (s) {
            case "NEW" -> itemStatus = ItemStatus.NEW;
            case "USED" -> itemStatus = ItemStatus.USED;
        }

        return itemStatus;


    }
}
