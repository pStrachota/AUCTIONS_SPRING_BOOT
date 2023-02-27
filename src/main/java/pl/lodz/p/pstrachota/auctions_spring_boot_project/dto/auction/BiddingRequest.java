package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemStatus;

@Data
@NoArgsConstructor
public class BiddingRequest extends AuctionRequest {

    @NotNull
    private boolean isLimited;

    @Builder
    public BiddingRequest(String description, BigDecimal startingPrice, ItemStatus itemStatus,
                          ItemCategory itemCategory, int daysToEndTime, boolean isLimited) {
        super(description, startingPrice, itemStatus, itemCategory, daysToEndTime);
        this.isLimited = isLimited;
    }
}
