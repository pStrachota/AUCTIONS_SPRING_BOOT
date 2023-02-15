package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemStatus;

@Data
@NoArgsConstructor
public class BuyNowRequest extends AuctionRequest {

    @NotNull
    private boolean isPremium;

    @Builder
    public BuyNowRequest(
            String email, String description, BigDecimal startingPrice, ItemStatus itemStatus,
            ItemCategory itemCategory, int daysToEndTime, boolean isPremium) {
        super(email, description, startingPrice, itemStatus, itemCategory, daysToEndTime);
        this.isPremium = isPremium;
    }
}
