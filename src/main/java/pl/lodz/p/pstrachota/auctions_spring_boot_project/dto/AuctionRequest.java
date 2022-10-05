package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto;

import static pl.lodz.p.pstrachota.auctions_spring_boot_project.service.properties.AppConstants.maxAuctionLenghtInDays;
import static pl.lodz.p.pstrachota.auctions_spring_boot_project.service.properties.AppConstants.maxDescriptionLenght;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.AuctionType;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemCategory;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.ItemStatus;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.validators.PriceConstraint;

@Data
@Builder
public class AuctionRequest {

    @Schema(example = "sample@mail.com")
    @Email(regexp = "[^@]+@[^@]+\\.[^@.]+", message = "Email is not valid")
    private String email;

    @Schema(example = "sample description")
    @Size(min = 1, max = maxDescriptionLenght, message = "Description must be provided")
    private String description;

    private AuctionType auctionType;

    @PriceConstraint
    @Schema(example = "1.00")
    private BigDecimal startingPrice;

    private ItemStatus itemStatus;

    private ItemCategory itemCategory;

    @Min(value = 1, message = "Auction end time must be provided")
    @Max(value = maxAuctionLenghtInDays, message = "Auction end time cannot be longer that " + maxAuctionLenghtInDays)
    private int daysToEndTime;

}
