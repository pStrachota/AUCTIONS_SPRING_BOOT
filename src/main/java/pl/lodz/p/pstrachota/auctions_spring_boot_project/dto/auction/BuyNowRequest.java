package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyNowRequest extends AuctionRequest {

    @NotNull
    private boolean isPremium;

    @Builder
    public BuyNowRequest(String description, BigDecimal startingPrice, ItemStatus itemStatus,
                         ItemCategory itemCategory, int daysToEndTime, boolean isPremium) {
        super(description, startingPrice, itemStatus, itemCategory, daysToEndTime);
        this.isPremium = isPremium;
    }
}
