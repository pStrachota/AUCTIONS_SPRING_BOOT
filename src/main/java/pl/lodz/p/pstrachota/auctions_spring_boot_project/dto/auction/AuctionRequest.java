package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto.auction;

import static pl.lodz.p.pstrachota.auctions_spring_boot_project.service.properties.AppConstants.MAX_AUCTION_LENGTH_IN_DAYS;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.service.properties.AppConstants.MAX_DESCRIPTION_LENGTH;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction.ItemStatus;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.validators.PriceConstraint;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuctionRequest {

    @Schema(example = "sample description")
    @Size(min = 1, max = MAX_DESCRIPTION_LENGTH, message = "Description must be provided")
    private String description;

    @PriceConstraint
    @Schema(example = "1.00")
    private BigDecimal startingPrice;

    private ItemStatus itemStatus;

    private ItemCategory itemCategory;

    @Min(value = 1, message = "Auction end time must be provided")
    @Max(value = MAX_AUCTION_LENGTH_IN_DAYS, message = "Auction end time cannot be longer that " +
            MAX_AUCTION_LENGTH_IN_DAYS)
    private int daysToEndTime;

}
