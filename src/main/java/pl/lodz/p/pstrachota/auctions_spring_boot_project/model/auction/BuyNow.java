package pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction;


import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Schema
@DiscriminatorValue("buy_now")
public class BuyNow extends Auction {

    private boolean isPremium;

    @Builder
    public BuyNow(long id, BigDecimal startingPrice, String description,
                  ItemStatus itemStatus,
                  ItemCategory itemCategory, LocalDateTime auctionEndTime,
                  LocalDateTime auctionStartTime, boolean isPremium) {
        super(id, "buy_now", description, startingPrice, itemStatus, itemCategory,
                auctionEndTime,
                auctionStartTime);
        this.isPremium = isPremium;
    }
}
